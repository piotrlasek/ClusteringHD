package sample;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.sql.PreparedStatement;
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
    int experimentId;

    final static Logger log = Logger.getLogger(ClusterVisualizer.class);

    /**
     *
     * @param database
     */
    public ClusterVisualizer(Database database, ArrayList<NominalNumericalAttribute> attributes, int experimentId)
            throws SQLException {
        this.database = database;
        this.attributes = attributes;
        this.experimentId = experimentId;
        log.info("ClusterVisualizer");
    }

    /**
     *
     */
    public void showClusters() {
        //JFrame frame = new JFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            ArrayList<TripleHashMap<String, String, HueWeight>> data = new ArrayList< TripleHashMap<String, String, HueWeight>>();
            ArrayList<Integer> clusters = Utils.getClusters(database, experimentId);

            for (Integer cluster : clusters) {
                String fileName = getFileName("ex_" + experimentId, cluster, attributes.size(), Main.limit);
                log.info("   Reading file: " + fileName);
                TripleHashMap<String, String, HueWeight> attributeValueHueWeight = TripleHashMap.readHW(fileName);
                data.add(attributeValueHueWeight);
            }

            log.info("Object deserialized.");

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
            log.info("Deserialization did not work. Creating new object...");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param prefix
     * @param clusterId
     * @param attributesCount
     */
    public String getFileName(String prefix, Integer clusterId, Integer attributesCount, Integer itemsCount) {
        return prefix + "-" + clusterId + "_" + attributesCount + "_" + itemsCount + ".ser";
    }

    /**
     *
     */
    public void prepareWeights() throws SQLException {

        ArrayList<Integer> clusterIds = Utils.getClusters(database, experimentId );

        for(Integer clusterId : clusterIds) {
            TripleHashMap<String, String, HueWeight> attributeValueHueWeight = null;
            String fileName = getFileName("ex_" + experimentId, clusterId, attributes.size(), Main.limit);

            if (attributeValueHueWeight == null) {
                try {
                    attributeValueHueWeight = TripleHashMap.readHW(fileName);
                    log.info("Hues and weights object deserialized.");
                } catch (Exception e) {
                    log.info("Deserialization did not work. Creating new object...");
                }
            }

            if (attributeValueHueWeight == null || attributes.size() != attributeValueHueWeight.size()) {
                attributeValueHueWeight = new TripleHashMap<>();

                if (attributeValueHueWeight != null)
                    log.info("Deserialized hues and weights object sizes did not match.");

                Integer recordsCountInCluster = Utils.getRecordsCount(database.getConnection(),
                        "SELECT COUNT (id) FROM experiment_item WHERE ex_id = " + experimentId +  " AND cluster_id = " + clusterId);

                System.out.print("Preparing weights for cluster " + clusterId + ": ");
                for (NominalNumericalAttribute a : attributes) {
                    String attribute = a.getName();

                    String query =
                            "SELECT " + attribute + ", COUNT(" + attribute + ") as CNT " +
                            "FROM data JOIN experiment_item ON data.id = experiment_item.item_id " +
                            "WHERE experiment_item.cluster_id = " + clusterId + " AND experiment_item.ex_id = " + experimentId + " " +
                            "GROUP BY " + attribute + " " +
                            "ORDER BY cnt DESC";

                    // log.info("   " + query);

                    Statement sQuery = database.getConnection().createStatement();
                    sQuery.executeQuery(query);
                    ResultSet rs = sQuery.getResultSet();

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
     */
    public void prepareColors() throws SQLException {

        String fileName = getFileName("colors", experimentId, attributes.size(), Main.limit);

        if (attributeValueHue == null) {
            try {
                attributeValueHue = TripleHashMap.read(fileName);
                log.info("Colors object deserialized.");
            } catch(Exception e) {
                log.info("Colors object deserialization did not work. Creating new object...");
            }
        }

        // sort allAttributes by count
        if (attributeValueHue == null || attributeValueHue.size() != attributes.size()) {
            attributeValueHue = new TripleHashMap<>();

            if (attributeValueHue != null)
                log.info("Deserialized colors object sizes did not match.");

            log.info("Preparing colors...");

            int attributeIndex = 0;

            for (NominalNumericalAttribute a : attributes) {
                String attribute = a.getName();

                String query = "SELECT " + attribute + ", COUNT(" + attribute + ") as CNT " +
                        "FROM data JOIN experiment_item ON " +
                        "data.id = experiment_item.item_id " +
                        "WHERE experiment_item.ex_id = " + experimentId + " " +
                        "GROUP BY " + attribute + " " +
                        "ORDER BY cnt DESC ";

                if (attributeIndex % 100 == 0)
                    log.info("   " + (int)(((float) (attributeIndex++) / (float) attributes.size())*100) + "%") ;

                Statement statement = database.getConnection().createStatement();
                statement.execute(query);
                ResultSet rs = statement.getResultSet();

                Integer recordsCount = Utils.getRecordsCount(database.getConnection(),
                        "SELECT " +
                        "   COUNT (DISTINCT " + attribute + ") " +
                        "FROM data JOIN experiment_item " +
                        "   ON data.id = experiment_item.item_id " +
                        "WHERE " +
                        "   experiment_item.ex_id = " + experimentId);

                Float hueDelta = 1f / recordsCount;
                Float hue = 0f;

                while (rs.next()) {
                    String value = rs.getString(attribute);
                    attributeValueHue.put(attribute, value, hue);
                    hue += hueDelta;
                }
            }

            attributeValueHue.save(fileName);
        }
    }

}
