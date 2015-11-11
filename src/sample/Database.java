package sample;

import org.apache.log4j.Logger;

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
            log.info("  Selected attributes: " + attributes);

            Statement statementGetBitRecords = null;
            try {
                statementGetBitRecords = getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            log.info("  Reading data from DB... ");

            //String query = "SELECT id, " + attributes.replace("'", "") + " FROM " + tableName + "_map"
            String query = "SELECT id, " + attributes.replace("'", "") + " FROM data WHERE ccc_101 = 'YES' OR " +
                " ccc_121 = 'YES' LIMIT " + Main.limit;

            statementGetBitRecords.execute(query);

            log.info("  Done.");

            ResultSet resultSetBitRecords;
            resultSetBitRecords = statementGetBitRecords.getResultSet();

            int count = 0;

            log.info("  Constructing an array of bit vectors... ");

            int id = 0;
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
    public DistanceMatrix buildDistanceMatrix(ArrayList<NominalNumericalObject> objects) {
        log.info("buildDistanceMatrix Start.");
        int nnAttributesCount = objects.get(0).getNnAttributes().size();
        int objectsCount = objects.size();
        String fileName = "distance-matrix-" + nnAttributesCount + "-" + objectsCount + ".ser";
        DistanceMatrix dm = null;

        try {
            dm = DistanceMatrix.read(fileName);
        } catch (Exception e) {
            log.error(e);
        }

        if (dm == null) {
            log.info("New distance matrix.");
            dm = new DistanceMatrix(objects.size(), objects.size());

            for (int i = 0; i < objects.size(); i++) {
                NominalNumericalObject oi = objects.get(i);
                for (int j = i; j < objects.size(); j++) {
                    NominalNumericalObject oj = objects.get(j);
                    float dist = oi.distance(oj);
                    dm.set(i, j, dist);
                    dm.set(j, i, dist);
                }
            }

            dm.computeNeighbours();
            dm.save(fileName);
        }

        log.info("buildDistanceMatrix End.");
        return dm;
    }
}
