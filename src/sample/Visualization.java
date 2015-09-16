package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Piotr Lasek on 5/5/2015.
 */
public class Visualization {

    Connection connection;

    String tableName = "data5";
    String tableSegments = "segment";

    /**
     *
     */
    public Visualization(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @param attributes
     * @param ids
     * @return
     * @throws SQLException
     */
    public ArrayList<HashMap<String, Double>> getClusterAvarages(
           ArrayList<String> attributes, ArrayList<String> ids) throws SQLException {
        ArrayList<HashMap<String, Double>> result = new ArrayList<HashMap<String, Double>>();
        Statement statement = connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iIds = ids.iterator();

        int clusterId = 1;
        while(iIds.hasNext()) {
            stringBuilder.append("\nselect \n\t" + clusterId + " as CLUSTER_ID, ");
            clusterId++;

            Iterator<String> iAttributes = attributes.iterator();
            while (iAttributes.hasNext()) {
                stringBuilder.append("\n\tavg(");
                String attributeName = iAttributes.next();
                stringBuilder.append(attributeName);
                stringBuilder.append(") as " + attributeName);
                if (iAttributes.hasNext()) {
                    stringBuilder.append(", ");
                }
            }

            stringBuilder.append("\nfrom \n\t" + tableName + "\nwhere\n\rid in (" + iIds.next() + ")");
            if (iIds.hasNext()) {
                stringBuilder.append("\nunion all ");
            }
        }

        //System.out.println(stringBuilder.toString());
        statement.execute(stringBuilder.toString());

        ResultSet rsAverages = statement.getResultSet();
        while(rsAverages.next()) {
            HashMap<String, Double> subgroupAverage = new HashMap<String, Double>();
            subgroupAverage.put("CLUSTER_ID", rsAverages.getDouble("CLUSTER_ID"));
            for (String attribute : attributes) {
                String subgroupName = attribute.substring(0, 3);
                Double value = rsAverages.getDouble(attribute);
                subgroupAverage.put(subgroupName, value);
            }
            result.add(subgroupAverage);
        }

        return result;
    }

    /**
     *
     * @param attributes
     * @return
     */
    private String selectAttributesAverages(ArrayList<String> attributes) {
        Iterator<String> iAttributes = attributes.iterator();

        if (true) {

        }

        return null;
    }

    /**
     *
     * @param subgroupName
     * @return
        ArrayList<String> subgroup = new ArrayList<String>();

        for (String attribute : attributes) {
            if (attribute.startsWith(subgroupName)) {
                subgroup.add(attribute);
            }
        }

        return subgroup;
    }

    /**
     *
     * @param exceptAttributes
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getAttributes(ArrayList<String> exceptAttributes) throws SQLException {
        ArrayList<String> attributes = new ArrayList<String>();

        Statement statement = connection.createStatement();
        String query = selectAttributes(exceptAttributes);
        //System.out.println(query);
        statement.execute(query);

        ResultSet rsAttributes = statement.getResultSet();
        while (rsAttributes.next()) {
            attributes.add(rsAttributes.getString("name").trim().toLowerCase());
        }

        return attributes;
    }

    /**
     *
     * @param exceptAttributes
     * @return
     */
    private String selectAttributes(ArrayList<String> exceptAttributes) {

        StringBuilder sbAttributes = new StringBuilder();
        sbAttributes.append("select name from attributes ");

        if (exceptAttributes.size() > 0) {
            sbAttributes.append("where ");
            Iterator<String> itExceptAttributes = exceptAttributes.iterator();

            while (true) {
                sbAttributes.append("name not like '");
                sbAttributes.append(itExceptAttributes.next().trim());
                sbAttributes.append("%'");
                if (itExceptAttributes.hasNext()) {
                    sbAttributes.append(" AND ");
                } else {
                    break;
                }
            }
        }

        return sbAttributes.toString();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getAttributesCategories() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();

        Statement statement = connection.createStatement();

        statement.execute(
                "select substring(name from 0 for 4) as sub " +
                "from attributes group by sub");

        ResultSet categories = statement.getResultSet();

        while(categories.next()) {
            result.add(categories.getString("sub"));
        }

        return result;
    }


}
