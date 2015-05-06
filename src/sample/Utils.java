package sample;

import org.apache.commons.io.FileUtils;
import org.apache.mahout.math.DenseVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Piotr Lasek on 15-04-15.
 */
public class Utils {

    // Will be mapped to null values.
    public static ArrayList<String> stopWords = new ArrayList<String>(Arrays.asList("don't know", "n/s",
            "n/a", "refusal", "no drinks last w", "no phy. activity", "not required"));

    /**
     *
     * @param connection
     * @return
     * @throws SQLException
     */
    public static HashMap<String, String> getAttributeFormat(Connection connection) throws SQLException {
        HashMap<String, String> result = new HashMap<String, String>();
        String query =
            "SELECT " +
            "    NAME, FORMAT " +
            "FROM " +
            "    attributes ";

        Statement statement = connection.createStatement();
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
     * @param connection
     * @return
     * @throws Exception
     */
    public static StringBuilder generateQuery(Connection connection) throws Exception {
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> attributesFormats = Utils.getAttributeFormat(connection);
        HashMap<String, Mapping> formatMappings = Utils.map(connection);

        Set<String> attributes = attributesFormats.keySet();
        boolean first = true;
        sb.append("SELECT\n");
        sb.append("\tID,\n");
        for(String attribute : attributes) {
//            System.out.println("a: " + attribute);

            String format = attributesFormats.get(attribute);

//            System.out.println("++" + format);

            Mapping oldNewValues = formatMappings.get(format);
            if (first) {sb.append("\tCASE\n"); first = false;}
            else sb.append(",\n\tCASE\n");
            Set<String> oldValues = oldNewValues.getOldValues();
            for(String oldValue : oldValues) {
                //sb.append(oldValue + " -> " + oldNewValues.getNewValue(oldValue));
                Integer newValue = oldNewValues.getNewValue(oldValue);
                //System.out.println(oldValue + " -> " + oldNewValues.getNewValue(oldValue));
                oldValue = oldValue.replace("'", "''");
                sb.append("\t\tWHEN lower(" + attribute + ") LIKE '" + oldValue + "' THEN " + newValue + "\n");
            }

            for(String nullWord : Utils.stopWords) {
                nullWord = nullWord.replace("'", "''");
                sb.append("\t\tWHEN lower(" + attribute + ") LIKE '" + nullWord + "' THEN null \n");
            }
            sb.append("\tEND AS " + attribute);

        }

        sb.append("\nINTO DATA3\nFROM data LIMIT 3");
        System.out.println(sb.toString());

        FileUtils.writeStringToFile(new File("query.sql"), sb.toString());

        return sb;
    }

    /**
     *
     * @param connection
     * @throws SQLException
     * @throws IOException
     */
    public static HashMap<String, Mapping> map(Connection connection) throws Exception {

        HashMap<String, Mapping> formatMapping = new HashMap<String, Mapping>();
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/Dropbox/PROJECTS/DIABETIC/attributes.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                int tmpX = 0;
                boolean intDef = false;
                boolean castInt = false;
                boolean castFloat = false;
                String format = br.readLine();

                if (format == null)
                    break;

                String[] f = format.split("\t");

                if (f.length == 2 && f[1].equals("INT")) {
                    intDef = false; castInt = true; castFloat = false;
                } else if (f.length == 2 && f[1].equals("INT_DEF")) {
                    intDef = true;
                    castInt = false; castFloat = false;
                } else if (f.length == 2 && f[1].equals("FLOAT")) {
                    intDef = false; castInt = false; castFloat = true;
                } else if (f.length == 2) {
                    System.out.println("ERROR: " + format);
                    break;
                }

                br.readLine(); // ---

                HashMap<String, Integer> mapping = new HashMap<String, Integer>();
                int tmpInt = 0;
                for(String attribute; (attribute = br.readLine()) != null && !attribute.isEmpty() ; ) {
                    // System.out.println(attribute);
                    String[] at = attribute.split("\t");
                    // an attribute is not a "stop-word"
                    if (!stopWords.contains(at[1])) {
                        if (intDef && !castInt && !castFloat && at.length == 3)
                            mapping.put(at[1], new Integer(at[2]));
                        else if (castInt && !castFloat && !intDef && at.length == 2) {
                            String n = at[1];
                            int index = n.indexOf(" ");
                            if (index != -1) {
                                n = n.substring(0, index);
                            }
                            // System.out.println("----" + n);
                            n = n.replaceAll("[^\\d.]", "");
                            //n = n.replace(" ", "");
                            Integer i = new Integer(n);
                            mapping.put(at[1], i);
                        } else if (castInt == false && intDef == false && at.length == 2 &&
                                castFloat == false) {
                            mapping.put(at[1], tmpInt++);
                        } else if (castFloat == true && intDef == false && castInt == false &&
                                at.length == 2) {
                            String n = at[1];
                            int index = n.indexOf(" ");
                            if (index != -1) {
                                n = n.substring(0, index - 1);
                            }
                            Integer i = (int) (Float.parseFloat(n) * 10);
                            mapping.put(at[1], i);
                        } else {
                            System.out.println("BREAK > " + attribute);
                            break;
                        }
                    }
                }

                // map to 0 - 10;
                Set<String> keys = mapping.keySet();

                Integer min = null;
                Integer max = null;
                for(String key:keys) {
                    Integer v = mapping.get(key);

                    if (min == null) min = v;
                    if (max == null) max = v;

                    if (v < min) min = v;
                    if (v > max) max = v;
                }

                for(String key:keys) {
                    Integer v = mapping.get(key);
                    v = (10 * v) / (max - min);
                    mapping.put(key, v);
                }

                //System.out.println("> " + format);
                Mapping m = new Mapping();
                m.setMapping(mapping);
                String format2 = format.split("\t")[0];
                int ind = format2.indexOf(" ");
                if (ind > -1) {
                    format2 = format2.substring(ind+1, format2.length());
                    //System.out.println("-" + format2);
                    formatMapping.put(format2, m);
                } else {
                    break;
                }
            }
        }

        return formatMapping;
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

            // get attributes
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
    public static ArrayList<Attribute> getAttributes(Connection connection) throws SQLException {

        ArrayList<Attribute> attributes = new ArrayList<Attribute>();

        Statement s = connection.createStatement();
        boolean b = s.execute("SELECT arnum as id, name, type, length, format, substring(label from 0 for 100) as label FROM attributes");
        ResultSet rs = s.getResultSet();

        while(rs.next()) {
            attributes.add(new Attribute(rs));
        }

        return attributes;
    }

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
     * @param a
     * @param b
     * @return
     */
    public static double tanimotoVector(DenseVector u, DenseVector v) {
        double uv = u.times(v).zSum();
        double u2 = u.getLengthSquared();
        double v2 = v.getLengthSquared();
        double dist = uv / (u2 + v2 - uv);
        //if (dist < 0.99) System.out.println(dist);
        return dist;
    }
}
