package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

/**
 * Created by Piotr Lasek on 15-04-15.
 */
public class Utils {

    /**
     *
     * @param connection
     * @throws SQLException
     * @throws IOException
     */
    public static void map(Connection connection) throws SQLException, IOException {
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/Dropbox/PROJECTS/DIABETIC/attributes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {



            }
        }

    }

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
}
