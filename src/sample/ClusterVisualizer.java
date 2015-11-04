package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 10/14/2015.
 */
public class ClusterVisualizer {

    private ArrayList<NominalNumericalAttribute> attributes;
    private Database database;
    TripleHashMap<String, String, Float> attributeValueHue = null;
    String algorithm = "dbscan";

    /**
     *
     * @param database
     */
    public ClusterVisualizer(Database database, ArrayList<NominalNumericalAttribute> attributes)
            throws SQLException {
        this.database = database;
        this.attributes = attributes;
    }

    /**
     *
     */
    public void showClusters() {
        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            ArrayList<TripleHashMap<String, String, HueWeight>> data = new ArrayList< TripleHashMap<String, String, HueWeight>>();
            ArrayList<Integer> clusters = Utils.getClusters(database, algorithm);

            for (Integer cluster : clusters) {
                TripleHashMap<String, String, HueWeight> attributeValueHueWeight = TripleHashMap.readHW(getFileName("avhw_", algorithm, cluster, attributes.size()));
                data.add(attributeValueHueWeight);
            }

            System.out.println("Object deserialized.");

            MyCanvas myCanvas = new MyCanvas(data, attributes, attributeValueHue );
            ClustersFrame cf = new ClustersFrame(myCanvas);
            myCanvas.setClusterFrame(cf);
            cf.setSize(800, 1000);
            cf.pack();
            cf.setVisible(true);
          //  frame.setSize(600, 800);
          //  frame.setContentPane(new MyCanvas(attributeValueHueWeight));
          //  frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Deserialization did not work. Creating new object...");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param prefix
     * @param algorithm
     * @param clusterId
     * @param attributesCount
     */
    public String getFileName(String prefix, String algorithm, Integer clusterId, Integer attributesCount) {
        return prefix + algorithm + "_" + clusterId + "_" + attributesCount + ".ser";
    }

    /**
     *
     * @param database
     */
    public void prepareWeights(Database database) throws SQLException {
        ArrayList<Integer> clusterIds = Utils.getClusters(database, algorithm);

        for(Integer clusterId : clusterIds) {
            TripleHashMap<String, String, HueWeight> attributeValueHueWeight = null;
            String fileName = getFileName("avhw_", algorithm, clusterId, attributes.size());

            if (attributeValueHueWeight == null) {
                try {
                    attributeValueHueWeight = TripleHashMap.readHW(fileName);
                    System.out.println("Hues and weights object deserialized.");
                } catch (Exception e) {
                    System.out.println("Deserialization did not work. Creating new object...");
                }
            }

            if (attributeValueHueWeight == null || attributes.size() != attributeValueHueWeight.size()) {
                attributeValueHueWeight = new TripleHashMap<>();

                if (attributeValueHueWeight != null)
                    System.out.println("Deserialized hues and weights object sizes did not match.");

                Integer recordsCountInCluster = Utils.getRecordsCount(database.getConnection(),
                        "SELECT COUNT (id) FROM data WHERE " + algorithm + " = " + clusterId);

                System.out.print("Preparing weights for cluster " + clusterId + ": ");
                for (NominalNumericalAttribute a : attributes) {
                    String attribute = a.getName();

                    String query = "SELECT " + attribute + ", COUNT(" + attribute + ") as CNT " +
                            "FROM data " +
                            "WHERE " + algorithm + " = " + clusterId + " " +
                            "GROUP BY " + attribute + " " +
                            "ORDER BY cnt DESC";

                    System.out.print(attribute + " ");
                    Statement statement = database.getConnection().createStatement();
                    statement.execute(query);
                    ResultSet rs = statement.getResultSet();

                    while (rs.next()) {
                        String value = rs.getString(attribute);

                        Float hue = attributeValueHue.get(attribute, value);
                        Integer distinctValuesCount = rs.getInt("CNT");
                        Float weight = Float.valueOf(distinctValuesCount) / Float.valueOf(recordsCountInCluster);

                        HueWeight hueWeight = new HueWeight(hue, weight);

                        attributeValueHueWeight.put(attribute, value, hueWeight);
                    }
                }
                attributeValueHueWeight.save(fileName);
            }
        }
    }



    /**
     *
     * @param database
     */
    public void prepareColors(Database database) throws SQLException {
        String fileName = getFileName("colors", algorithm, 0, attributes.size());

        if (attributeValueHue == null) {
            try {
                attributeValueHue = TripleHashMap.read(fileName);
                System.out.println("Colors object deserialized.");
            } catch(Exception e) {
                System.out.println("Colors object deserialization did not work. Creating new object...");
            }
        }

        // sort attributes by count

        if (attributeValueHue == null || attributeValueHue.size() != attributes.size()) {
            attributeValueHue = new TripleHashMap<>();

            if (attributeValueHue != null)
                System.out.println("Deserialized colors object sizes did not match.");

            System.out.print("Preparing colors for: ");

            for (NominalNumericalAttribute a : attributes) {
                // while(attCount.hasNext()) {
                //String attribute = attCount.next().getKey();
                String attribute = a.getName();

                String query = "SELECT " + attribute + ", COUNT(" + attribute + ") as CNT " +
                        "FROM data " +
                        // "WHERE " + algorithm + " = " + clusterId + " " +
                        "GROUP BY " + attribute + " " +
                        "ORDER BY cnt DESC";

                System.out.print(attribute + " ");
                Statement statement = database.getConnection().createStatement();
                statement.execute(query);
                ResultSet rs = statement.getResultSet();

                Integer recordsCount = Utils.getRecordsCount(database.getConnection(),
                        "SELECT COUNT (DISTINCT " + attribute + ") FROM data");

                Float hueDelta = 1f / recordsCount;
                Float hue = 0f;

                /*
                if (a.getType() == true) { // attribute is numerical
                    while(rs.next()) {
                        String value = rs.getString(attribute);
                        Float valueFloat = Utils.convert(value);

                        if (valueFloat < 0) {
                            attributeValueHue.put(attribute, valueFloat, hue);
                        } else {
                            attributeValueHue.put(attribute, value, hue);
                        }

                        hue += hueDelta;
                    }
                } else { // attribute is nominal
                */

                while (rs.next()) {
                    String value = rs.getString(attribute);
                    attributeValueHue.put(attribute, value, hue);
                    hue += hueDelta;
                }
            /*}*/
            }

            System.out.println();
            attributeValueHue.save(fileName);
        }
    }
}
