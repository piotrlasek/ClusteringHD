package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Piotr on 28.10.2015.
 */
public class Database {

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

        ArrayList<NominalNumericalObject> dataset = new ArrayList();

        if (getConnection()!= null) {
            System.out.println("Selected attributes: " + attributes);

            Statement statementGetBitRecords = null;
            try {
                statementGetBitRecords = getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Reading data from DB... ");

            //String query = "SELECT id, " + attributes.replace("'", "") + " FROM " + tableName + "_map"
            String query = "SELECT id, " + attributes.replace("'", "") + " FROM data WHERE ccc_101 = 'YES' OR " +
                " ccc_121 = 'YES' LIMIT " + Main.limit;

            statementGetBitRecords.execute(query);
            System.out.println("Done.");

            ResultSet resultSetBitRecords;
            resultSetBitRecords = statementGetBitRecords.getResultSet();

            int count = 0;

            System.out.println("Constructing an array of bit vectors... ");

            while (resultSetBitRecords.next()) {
                NominalNumericalObject nno = new NominalNumericalObject();
                nno.addAttributes(nominalNumericalAttributes);
                nno.setValues(resultSetBitRecords);

                dataset.add(nno);
                count++;
            }

            System.out.print("Done.\n");
        }

        return dataset;
    }
}
