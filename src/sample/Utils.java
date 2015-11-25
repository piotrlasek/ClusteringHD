package sample;

import org.apache.log4j.Logger;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.DenseVector;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Piotr Lasek on 15-04-15.
 */
public class Utils {

    final static Logger log = Logger.getLogger(Utils.class);

    /**
     *
     * @param database
     * @return
     * @throws SQLException
     */
    public static HashMap<String, String> getAttributeFormat(Database database, String attributesList)
            throws SQLException {
        HashMap<String, String> result = new HashMap<String, String>();
        String query;
        if (!attributesList.equals("*")) {
            query =
                    "SELECT " +
                            "    NAME, FORMAT " +
                            "FROM " +
                            "    attributes " +
                            "WHERE name IN (" + attributesList + ")";
        } else {
            query =
                    "SELECT " +
                    "    NAME, FORMAT " +
                    "FROM " +
                    "    attributes ";
        }

        Statement statement = database.getConnection().createStatement();
        statement.execute(query);

        ResultSet rs = statement.getResultSet();

        while(rs.next()) {
            String name = rs.getString("name");
            String format = rs.getString("format");
            if (name != null && format != null) {
                result.put(name.trim(), format.trim());
            }
        }
        return result;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static ArrayList<NominalNumericalAttribute> getNominalNumericalAttributes(Connection connection) throws IOException, SQLException {

        ArrayList<NominalNumericalAttribute> attributes = new ArrayList<NominalNumericalAttribute>();

        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/Dropbox/PROJECTS/DIABETIC/attribute-values.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while(true) {
                String s = br.readLine();
                if (s == null) break;

                if (s.startsWith("-----") || (s.startsWith("--break--"))) {
                    continue;
                } else {
                    String[] ar = s.split(" ");
                    String attributeName = ar[0];
                    br.readLine(); // --------------
                    s = br.readLine();

                    ArrayList<Integer> counts = new ArrayList<Integer>();
                    ArrayList<String> values = new ArrayList<String>();

                    while(!s.startsWith("--")) {
                        String cntVal = s;
                        String[] cntValAr = cntVal.split("\t\t");
                        Integer cnt = new Integer(cntValAr[0]);
                        counts.add(cnt);
                        String val = cntValAr[1];
                        values.add(val);
                        s = br.readLine();
                    }

                    boolean attributeType = getAttributeFormat(values);

                    NominalNumericalAttribute nna = new NominalNumericalAttribute(attributeName,
                        attributeType);
                    Float[] minMax = getMinMax(connection, attributeName, attributeType);
                    nna.setMinMax(minMax);

                    System.out.println(nna.getName() + "\t:\t" + (nna.getType() ? " N " + nna.getMin() +
                            ", " + nna.getMax() : " abc "));

                    nna.updateAttribute(connection);
                }
            }
        }

        return attributes;
    }

    /**
     *
     * @param values
     * @return
     */
    private static boolean getAttributeFormat(ArrayList<String> values) {
        int numbers = 0;
        int strings = 0;
        int maxSize = (values.size() > 5 ? 5 : values.size());
        for (int i = 0; i < maxSize; i++) {
            String stringValue = values.get(i);
            Float floatValue = null;
            Integer intValue = null;

            try {
                //floatValue = new Float(stringValue);
                floatValue = Utils.convert(stringValue);
                numbers++;
            } catch (Exception e) {
                strings++;
            }
        }

        if (numbers > strings)
            return true;
        else
            return false;
    }

    /**
     *
     * @param connection
     * @param attributeName
     * @param typeNumerical
     * @return
     */
    private static Float[] getMinMax(Connection connection, String attributeName, boolean typeNumerical) {

        Float[] minMax = new Float[2];

        try {
            if (typeNumerical) {
                Statement statement = connection.createStatement();
                String queryDistinct = "SELECT DISTINCT " + attributeName + " FROM DATA " +
                        "WHERE " + attributeName + " NOT IN (" + Mapper.stopWordsAtt + ") ";

                statement.execute(queryDistinct);

                ResultSet rsDistinct = statement.getResultSet();

                ArrayList<Float> values = new ArrayList<Float>();

                while(rsDistinct.next()) {
                    String value = rsDistinct.getString(1);
                    try {
                        Float f = Utils.convert(value);
                        values.add(f);
                    } catch (Exception e) {
                        System.out.println(value + " not mapped to float!");
                    }
                }

                Float min = Collections.min(values);
                Float max = Collections.max(values);

                /*String query = "SELECT min(" + attributeName + ") AS MI, max(" + attributeName + ") as MA FROM DATA" +
                        " WHERE " + attributeName + " NOT IN (" + Mapper.stopWordsAtt + ")";

                System.out.println(query);

                statement.execute(query);

                ResultSet rs = statement.getResultSet();
                rs.next();
                minMax[0] = rs.getFloat("MI");
                minMax[1] = rs.getFloat("MA");*/
                minMax[0] = min;
                minMax[1] = max;
            }

        } catch (Exception e) {
        }

        return minMax;
    }

    /**
     *
     * @param connection
     * @throws SQLException
     */
    public static void findAttributeValues(Connection connection) throws SQLException {

        Statement statementFormats = connection.createStatement();
        String query =
                "select " +
                "   format, " +
                "   count(format) as cn " +
                "from " +
                "   attributes " +
                "where " +
                "   format is not null " +
                "group by " +
                "   format " +
                "order by " +
                "   cn asc";
        //System.out.println(query);
        statementFormats.execute(query);

        ResultSet formats = statementFormats.getResultSet();

        int formatCount = 0;
        while(formats.next()) {
            formatCount++;
            if (formatCount < 127)
                continue;

            String format = formats.getString("format").trim();

            // get allAttributes
            Statement statementAttributes = connection.createStatement();

            String attributesQuery = "select name from attributes where format like \'" + format + "%\'";
            //System.out.println(attributesQuery);
            statementAttributes.execute(
                    attributesQuery
            );

            ResultSet resultSetAttributes = statementAttributes.getResultSet();
            ArrayList<String> listAttributes = new ArrayList<String>();
            while(resultSetAttributes.next()) {
                String attribute = resultSetAttributes.getString("name").trim();
                listAttributes.add(attribute);
            }

            // generate

            StringBuilder queryAttributesValues = new StringBuilder();
            Iterator<String> iteratorAttributes = listAttributes.iterator();

            queryAttributesValues.append("SELECT values, sum(cnt) as cnt FROM (");

            while (iteratorAttributes.hasNext()) {
                String attribute = iteratorAttributes.next();

                queryAttributesValues.append(
                    "SELECT " +
                    "   " + attribute + " as values, " +
                    "   count( " + attribute + ") as cnt " +
                    "from " +
                    "   data " +
                    "group by " +
                        attribute
                );

                if (iteratorAttributes.hasNext()) {
                    queryAttributesValues.append(" UNION ALL ");
                }
            }

            queryAttributesValues.append(") as foo group by values order by cnt");

            //System.out.println(queryAttributesValues.toString());

            Statement statementValues = connection.createStatement();
            statementValues.execute(queryAttributesValues.toString());
            ResultSet resultSetValues = statementValues.getResultSet();

            System.out.println("" + (formatCount) + ". " + format);
            System.out.println("---");
            while(resultSetValues.next()) {
                String value = resultSetValues.getString("values").toLowerCase();
                Integer count = resultSetValues.getInt("cnt");
                System.out.println(count + "\t" + value);
            }
            System.out.println();
        }
    }

    /**
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    /*public static ArrayList<Attribute> getAttributes(Connection connection) throws SQLException {

        ArrayList<Attribute> allAttributes = new ArrayList<Attribute>();

        Statement s = connection.createStatement();
        boolean b = s.execute("SELECT arnum as id, lower(trim(name)) as name, type, length, format, substring(label from 0 for 100) as label FROM allAttributes");
        ResultSet rs = s.getResultSet();

        while(rs.next()) {
            allAttributes.add(new Attribute(rs));
        }

        return allAttributes;
    }*/

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public static double tanimoto(BitSet a, BitSet b) {
        BitSet ab = (BitSet) a.clone();

        ab.and(b);

        int AB = ab.cardinality();
        int A2 = a.cardinality();
        int B2 = b.cardinality();

        double t = (double) AB / (A2 + B2 - AB);

        return t;
    }

    /**
     *
     * @param rs
     * @return
     */
    public static ArrayList<BitSet> readRecords(ResultSet rs) {
        return null;
    }

    /**
     *
     * @param u
     * @param v
     * @return
     */
    public static double tanimotoVector(DenseVector u, DenseVector v) {
//        double uv = u.times(v).zSum();
//        double u2 = u.getLengthSquared();
//        double v2 = v.getLengthSquared();
//        double dist = uv / (u2 + v2 - uv);
        //TanimotoDistanceMeasure tdm = new TanimotoDistanceMeasure();
        EuclideanDistanceMeasure tdm = new EuclideanDistanceMeasure();
        double dist = tdm.distance(v, u);

        return dist;
    }

    /**
     *
     * @param clusters
     * @param database
     * @throws SQLException
     */
    public static int saveClusters(ArrayList<ClusterVect> clusters, Database database) throws SQLException {

        log.info("saveClusters Start");
        Statement s = database.getConnection().createStatement();

        database.getConnection().setAutoCommit(false);

        String newExperiment = "INSERT INTO experiment (created, description) VALUES (now(), ?) RETURNING id";
        PreparedStatement psNewExperiment = database.getConnection().prepareStatement(newExperiment);

        String description = JOptionPane.showInputDialog(null, "Description");

        psNewExperiment.setString(1, description);

        psNewExperiment.executeQuery();
        ResultSet rsNewExperimentId = psNewExperiment.getResultSet();
        rsNewExperimentId.next();
        int newExperimentId = rsNewExperimentId.getInt("id");

        String singleResult = "INSERT INTO experiment_item (ex_id, item_id, cluster_id) VALUES (?, ?, ?)";
        PreparedStatement psSingleResult = database.getConnection().prepareStatement(singleResult);

        for(ClusterVect cv : clusters) {
            ArrayList<NominalNumericalObject> clusterPoints = cv.points;
            for(NominalNumericalObject nno : clusterPoints) {
                psSingleResult.setInt(1, newExperimentId);
                psSingleResult.setInt(2, nno.getDbId());
                psSingleResult.setInt(3, nno.clusterId);
                psSingleResult.execute();
            }
            log.info("   cluster " + cv.getClusterId() + " (" + cv.points.size() + ") " + ": " + cv.toString());
        }
        database.getConnection().commit();
        database.getConnection().setAutoCommit(true);

        log.info("saveClusters End");
        return newExperimentId;
    }


    /**
     *
     * @param s
     * @return
     * @throws NumberFormatException
     */
    public static Float convert(String s) throws NumberFormatException {
        Float f = null;
        s = s.replace(">= ", "");
        s = s.replace("< ", "");
        try {
            f = new Float(s);
        } catch (Exception e) {
            String tmp[] = s.split(" ");
            String s2 = tmp[0].replace("+", "");
            try {
                f = new Float(s2);
            } catch (Exception e2) {
                //System.out.println("--------- CONVERTION DID NOT WORK FOR: " + s);
                // TODO: why e ?
                throw e;
            }
        }

        return f;
    }

    /**
     *
     * @param connection
     * @param query
     * @return
     */
    public static Integer getRecordsCount(Connection connection, String query) {
        Integer result = 0;

        try {
            Statement statement = connection.createStatement();
            statement.execute(query);

            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return result;
    }

    /**
     *
     * @param database
     * @return
     */
    public static ArrayList<Integer> getClusters(Database database, int experimentId) throws SQLException {

        String selectClusterIds = "SELECT DISTINCT cluster_id FROM experiment_item WHERE ex_id = ?";
        PreparedStatement psSelectClusters = database.getConnection().prepareStatement(selectClusterIds);
        psSelectClusters.setInt(1, experimentId);
        psSelectClusters.executeQuery();
        ResultSet rsClusterIds = psSelectClusters.getResultSet();

        ArrayList<Integer> result = new ArrayList<>();

        while (rsClusterIds.next()) {
            int clusterId = rsClusterIds.getInt("cluster_id");
            result.add(clusterId);
        }

        /* try {
            result = Utils.readClusters(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result == null || result.size() <= 0) {
            result = new ArrayList<>();
            Statement statement = database.getConnection().createStatement();
            statement.execute("SELECT DISTINCT " + algorithm + " FROM data WHERE " + algorithm + " > 0 ORDER BY " + algorithm);
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(resultSet.getInt(algorithm));
            }

            try {
                Utils.saveClusters(result, algorithm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        return result;
    }

    /**
     *
     * @param algorithm
     * @return
     * @throws Exception
     */
    public static ArrayList<Integer> readClusters(String algorithm) throws Exception {
        String fileName = algorithm + "_clusters.ser";
        ArrayList<Integer> e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (ArrayList<Integer>) in.readObject();
        in.close();
        fileIn.close();
        return e;
    }

    /**
     *
     * @param clusters
     * @param algorithm
     * @throws Exception
     */
    public static void saveClusters(ArrayList<Integer> clusters, String algorithm) throws Exception {
        String fileName = algorithm + "_clusters.ser";
        FileOutputStream fileOut =
                new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(clusters);
        out.close();
        fileOut.close();
    }

    /**
     *
     * @param fileName
     */
    public static void saveAttributes(ArrayList<NominalNumericalAttribute> attributes, String fileName) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(attributes);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static ArrayList<NominalNumericalAttribute> readAttributes(String fileName) throws Exception {
        ArrayList<NominalNumericalAttribute> e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (ArrayList<NominalNumericalAttribute>) in.readObject();
        in.close();
        fileIn.close();
        return e;
    }
}
