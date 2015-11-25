package sample;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Piotr on 28.10.2015.
 */
public class Database {

    final static Logger log = Logger.getLogger(Database.class);

    private Connection connection;
    private String url = "jdbc:postgresql://192.168.56.102/diabetic";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String whereConstraint = "ccc_101 = 'YES' OR ccc_121 = 'YES'" ;

    public Database() {
    }

    public Connection getConnection() {
        if (connection == null) {

            Properties properties = new Properties();
            properties.setProperty("user", "piotr");
            properties.setProperty("password", "piotr");
            try {
                connection = DriverManager.getConnection(url, properties);
            } catch (Exception e) {
                connection = null;
                e.printStackTrace();
            }
        }

        return connection;
    }

    /**
     *
     * @return
     * @param attributesList
     * @param exclude
     */
    public ArrayList<NominalNumericalAttribute> getAttributes(String attributesList, String exclude) {
        ArrayList<NominalNumericalAttribute> attributes = new ArrayList<>();

        if (getConnection() != null) {
            try {
                Statement statement = getConnection().createStatement();
                String sql;
                if (attributesList.equals("*")) {
                    sql =
                            "SELECT trim(name) as name, trim(label) as description, trim(format) as format, " +
                                    "numerical, min, max " +
                                    "FROM attributes " +
                                    "WHERE name NOT IN ( " + exclude + " )";
                } else {
                    sql =
                            "SELECT trim(name) as name, trim(label) as description, trim(format) as format, " +
                                    "numerical, min, max " +
                                    "FROM attributes WHERE name in (" + attributesList + ") " +
                                    "AND NAME NOT IN ( " + exclude + ")";
                }
                System.out.println(sql);

                statement.execute(sql);
                ResultSet rs = statement.getResultSet();
                int id = 0;
                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String format = rs.getString("format");
                    Float min = rs.getFloat("min");
                    Float max = rs.getFloat("max");
                    boolean numerical = rs.getBoolean("numerical");
                    NominalNumericalAttribute a = new NominalNumericalAttribute(id++, name, description, format, min, max, numerical);
                    attributes.add(a);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return attributes;
    }

    /**
     *
     * @param attributes
     * @param nominalNumericalAttributes
     * @param exclude
     * @return
     * @throws SQLException
     */
    public ArrayList<NominalNumericalObject> readData(String attributes,
        ArrayList<NominalNumericalAttribute> nominalNumericalAttributes,
        String exclude) throws SQLException {

        log.info("readData START");
        ArrayList<NominalNumericalObject> dataset = new ArrayList();

        if (getConnection()!= null) {
            log.info("  Selected allAttributes: " + attributes);

            Statement statementGetBitRecords = null;
            try {
                statementGetBitRecords = getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            log.info("  Reading data from DB... ");

            String query = "SELECT id, " + attributes.replace("'", "") + " FROM data WHERE " + whereConstraint + " ORDER BY ID LIMIT " + Main.limit;
            log.info("   " + query);

            statementGetBitRecords.execute(query);

            log.info("  Done.");

            ResultSet resultSetBitRecords;
            resultSetBitRecords = statementGetBitRecords.getResultSet();

            int count = 0;
            int id = 0;

            log.info("  Constructing an array of bit vectors... ");

            while (resultSetBitRecords.next()) {
                NominalNumericalObject nno = new NominalNumericalObject();
                nno.addAttributes(nominalNumericalAttributes);
                nno.setValues(id++, resultSetBitRecords);

                dataset.add(nno);
                count++;
            }

            log.info("  Done.");
        }

        log.info("readData END");
        return dataset;
    }

    /**
     *
     * @param objects
     * @return
     */
    public DistanceMatrix buildDistanceMatrix(ArrayList<NominalNumericalObject> objects, boolean overwrite) {
        log.info("buildDistanceMatrix Start.");
        int nnAttributesCount = objects.get(0).getNnAttributes().size();

        int objectsCount = objects.size();
        String fileName = "distance-matrix-" + nnAttributesCount + "-" + objectsCount + ".ser";
        DistanceMatrix dm = null;

        if (!overwrite) {
            try {
                dm = DistanceMatrix.read(fileName);
            } catch (Exception e) {
                log.error(e);
            }
        }

        if (dm == null) {
            log.info("New distance matrix.");
            dm = new DistanceMatrix(objects);

            dm.computeDistances();
            dm.computeNeighbours();

            dm.save(fileName);
        }

        log.info("buildDistanceMatrix End.");
        return dm;
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalObject> readDataFromFile(String fileName) {
        log.info("read Start.");
        ArrayList<NominalNumericalObject> dataset = null;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream(fileName);
            in = new ObjectInputStream(fileIn);
            dataset = (ArrayList<NominalNumericalObject>) in.readObject();
        } catch (FileNotFoundException e1) {
            log.error(e1);
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
        try {
            in.close();
            fileIn.close();
        } catch (Exception e) {
            log.error(e);
        }
        log.info("read End.");
        return dataset;
    }

    /**
     *
     */
    public void saveDataToFile(ArrayList<NominalNumericalObject> dataset, String fileName) {
        log.info("save Start.");
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dataset);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
        log.info("save End.");
    }


    /**
     *
     * @param dm
     */
    public void saveDistanceMatrixToDatabase(DistanceMatrix dm) {
        log.info("saveDistanceMatrixToDatabase Start");

        String newDM = "INSERT INTO distance_matrix (created, description) VALUES (now(), 'att: " +
                dm.getAttributes().size() + ", size: " + dm.getHeight() + "') RETURNING id";
        String newItem = "INSERT INTO distance_matrix_item (dm_id, p1, p2, dbid1, dbid2, dist) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        log.info(newDM);
        log.info(newItem);

        try {
            Statement s = getConnection().createStatement();
            s.execute(newDM);
            ResultSet rs = s.getResultSet();
            rs.next();
            int dmId = rs.getInt("id");

            PreparedStatement ps = getConnection().prepareStatement(newItem);
            getConnection().setAutoCommit(false);

            for(int i = 0; i < dm.getHeight(); i++) {
                for (int j = 0; j < dm.getWidth(); j++) {
                    float dist = dm.getDistance(i, j);
                    ps.setInt(1, dmId);
                    ps.setInt(2, i);
                    ps.setInt(3, j);
                    ps.setInt(4, dm.points.get(i).getDbId());
                    ps.setInt(5, dm.points.get(j).getDbId());
                    ps.setFloat(6, dist);
                    ps.execute();
                }
                getConnection().commit();
                if (i % 1000 == 0) {
                    log.info("   " + (int) (((float) i / (float) dm.getWidth()) * 100) + "%");
                }
            }
            getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        log.info("saveDistanceMatrixToDatabase End");
    }

    /**
     *
     * @return
     */
    public DistanceMatrix readDistanceMatrixFromDatabase(int dmId) throws Exception {
        log.info("readDistanceMatrixFromDatabase Start");
        ArrayList<NominalNumericalObject> points = new ArrayList<>();
        int count = 0;
        Statement statement = getConnection().createStatement();
        String countQuery = "SELECT distinct p1, dbid1 FROM distance_matrix_item WHERE dm_id = " + dmId;
        statement.execute(countQuery);
        ResultSet rs = statement.getResultSet();

        log.info("   Reading points...");
        while(rs.next()) {
            int id = rs.getInt("p1");
            int dbid1 = rs.getInt("dbid1");
            NominalNumericalObject nno = new NominalNumericalObject();
            nno.setId(id);
            nno.setDbId(dbid1);
            points.add(nno);
        }

        // ---

        DistanceMatrix dm = new DistanceMatrix(points);

        String allDists = "SELECT p1, p2, dist FROM distance_matrix_item WHERE dm_id = " + dmId;
        statement.execute(allDists);
        ResultSet rsAllObjects = statement.getResultSet();

        log.info("   Reading distances...");
        int i = 0;
        while (rsAllObjects.next()) {
            for (int j = 0; j < count; j++) {
                int p1 = rsAllObjects.getInt("p1");
                int p2 = rsAllObjects.getInt("p2");
                float dist = rsAllObjects.getFloat("dist");

                if (p1 != i || p2 != j) {
                    throw new Exception("Wrong data!");
                }

                dm.set(i, j, dist);
            }

            if (i % 1000 == 0) {
                log.info("   " + (int) (((float) i / count) * 100) + "%");
            }

            i++;
        }
        log.info("   Reading done.");
        log.info("readDistanceMatrixFromDatabase Stop");
        return dm;
    }

    /**
     *
     * @param dm
     * @param fileName
     */
    public void saveDistanceMatrixToCSV(DistanceMatrix dm, String fileName) {
        log.info("saveDistanceMatrixToCSV Start");


        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName, "UTF-8");

            for(int i = 0; i < dm.getHeight(); i++) {
                for (int j = 0; j < dm.getWidth(); j++) {
                    float dist = dm.getDistance(i, j);
                    writer.println(i + ";" + j + ";" + dist + ";");
                }
                if (i % 1000 == 0) {
                    log.info("   " + (int) (((float) i / (float) dm.getWidth()) * 100) + "%");
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.info(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.info(e);
        }

        log.info("saveDistanceMatrixToDatabase End");
    }


}
