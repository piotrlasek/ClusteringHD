package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class Main extends Application {

    //public static int limit = 17000;
    public static int limit = 1000;

    final static Logger log = Logger.getLogger(Main.class);

    static {
   }

    public static String exclude = "'VERDATE', 'ADM_RNO', 'WTS_M'";

    public static String filePrefix = "C:\\Users\\Piotr\\Dropbox\\PROJECTS\\DIABETIC\\diabetic-D-10k-03-25-all";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Diabetic - clusters visualization ");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     *
     * @param bs
     * @return
     */
    private static String toString(BitSet bs) {
        return Long.toString(bs.toLongArray()[0], 2);
    }

    /**
     *
     * @param connection
     */
    public static void visualize(Connection connection) throws SQLException, IOException {
        Visualization v = new Visualization(connection);
        ArrayList<String> exceptAttributes = new ArrayList<String>(Arrays.asList(
                "VERDATE", "ADM_RNO", "WTS_M"
                ));
        ArrayList<String> ids = new ArrayList<String>();

        // read ids from file
        FileInputStream fstream = new FileInputStream(filePrefix + "-clusters.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        while ((strLine = br.readLine()) != null) {
            //System.out.println(strLine);
            if (strLine.startsWith("Points: ")) {
                strLine = strLine.replace("Points: ", "");
                strLine = strLine.substring(0, strLine.length() - 1);
                ids.add(strLine);
                //System.out.println(strLine);
            }
        }

        br.close();

        ArrayList<String> attributes = v.getAttributes(exceptAttributes);;
        ArrayList<HashMap<String,Double>> averages = v.getClusterAvarages(attributes, ids);

        StringBuilder sb = new StringBuilder();

        for(HashMap<String, Double> cluster : averages) {
            Set<String> subgroups = cluster.keySet();
            sb.append("CLUSTER_ID" + ";");
            for(String subgroup : subgroups) {
                if (!subgroup.equals("CLUSTER_ID")) {
                    sb.append(subgroup + ";");
                }
            }
            sb.append("\n");
            break;
        }

        for(HashMap<String, Double> cluster : averages) {
            Set<String> subgroups = cluster.keySet();
            sb.append(cluster.get("CLUSTER_ID") + ";");

            for(String subgroup : subgroups) {
                if (!subgroup.equals("CLUSTER_ID")) {
                    Double value = (Double) cluster.get(subgroup);
                    sb.append(value + ";");
                }
            }
            sb.append("\n");
        }

        //System.out.println(sb.toString());
        PrintWriter pw = new PrintWriter(Main.filePrefix + "-averages.txt");
        pw.write(sb.toString());
        pw.close();
    }

    /**
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {

        log.info("START");

        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());for (Iterator i = UIManager.getLookAndFeelDefaults().keySet().iterator(); i.hasNext();) {

        Database database = new Database();

        //Connection connection = database.getConnection();

        // -----------------------------------------------------------------------
        // Determines which allAttributes are numerical or nominal.
        // For numerical determines max and min values.
        //
        // ArrayList<NominalNumericalAttribute> nnAttributes = Utils.getNominalNumericalAttributes(connection);

        // -----------------------------------------------------------------------
        // GENERATE QUERY START

        String tableName = "segments";
        String attributes  = "'CIH_1','SFEDE1','CIH_2','SFE_504','CIH_4','DHHGAGE','GEODPMF','GEN_08','GEOGPRV'," +
                "'INCGHH','CHPGMDC','CHPG04','ACC_40','PCU_153','PACDFM','PACDEE','FVCDTOT','GENDHDI','GEN_02A2'," +
                "'GENGSWL','GEN_02B','GENDMHI','DISDCHR','GEN_09','HUPDPAD','DHHGLVG','ADLF6R','PMH_04','SPV_6'," +
                "'SPV_6B','SPSDATT','SPSDWOR','SPSDCON','GEN_10'";

        //String allAttributes = "'DHHGAGE','GENDHDI','CIH_1','HUPDPAD','CHPGMDC','PACDFM','PMH_04'";

        // Most important
        //String allAttributes = "'GENDHDI', 'DHHGAGE', 'HUPDPAD', 'GEODPMF', 'GEN_08'";
        //, 'GEN_02A2', 'GENGSWL', 'GEOGPRV', 'PACDFM', 'PACDEE'";

        String algorithm = "";

        // Creates a mapped table segments_map based on original table segments.
        /*allAttributes  = "'CIH_1','SFEDE1','CIH_2','SFE_504','CIH_4','DHHGAGE','GEODPMF','GEN_08','GEOGPRV','INCGHH'," +
                "'CHPGMDC','CHPG04','ACC_40','PCU_153','PACDFM','PACDEE','FVCDTOT','GENDHDI','GEN_02A2','GENGSWL'," +
                "'GEN_02B','GENDMHI','DISDCHR','GEN_09','HUPDPAD','DHHGLVG','ADLF6R','PMH_04','SPV_6','SPV_6B'," +
                "'SPSDATT','SPSDWOR','SPSDCON','GEN_10'";
        */
        attributes = "*";

        log.info("Attributes selected: " + attributes);
        log.info("Attributes excluded: " + exclude);

        // --------------------------------------------------------------------------
        // NOT USED
        // Map data
        if (false) {
            try {
                Mapper.mapTable(database, tableName, attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ArrayList<NominalNumericalObject> dataset = new ArrayList<NominalNumericalObject>();

        // Load data
        SelectAttributesDialog sad;
        sad = new SelectAttributesDialog(database, attributes, exclude);
        sad.setVisible(true);
        // allAttributes = sad.getResult();
        // algorithm = sad.getAlgorithm();

        // dataset = database.readData(allAttributes, sad.getNominalNumericalAttributes(), exclude);
        // DistanceMatrix distanceMatrix = database.buildDistanceMatrix(dataset);

        // -----------------------------------------------------------------------------------
        // VISUALIZE


        // -----------------------------------------------------------------------------------
        // CLUSTERING START

        /*ArrayList<ClusterVect> clusters = null;

        long start = System.currentTimeMillis();

        if ("DBSCAN".equals(algorithm)) {
            DbScanVec dbscan = new DbScanVec(dataset, distanceMatrix);
            log.info("  MinPts: " + dbscan.getMinPts() + ", Eps: " + dbscan.getEps());
            dbscan.run();
            clusters = dbscan.getClusters();
        } else if ("KMEANS".equals(algorithm)) {
            int k = 7;
            KMeans kmeans = new KMeans(dataset, k);
            System.out.println("k: " + k);
            kmeans.run();
            clusters = kmeans.getClusters();
        } else {
            throw new Exception("Unknown algorithm");
        }

        long end = System.currentTimeMillis();
        log.info("  Run-time: " + ((end - start) / 1000) + " s");

        if (true) {
            //System.exit(0);
            log.info("EXIT");
            return;
        }*/

        // Utils.saveClusters(tableName, clusters, database, algorithm);

        // CLUSTERING END
        // -----------------------------------------------------------------------------------

        // prepare visualization
       // Main.visualize(connection);
        //if (true) return; /**/

        // show visualization
       // launch(args);
       // if (true) return; /**/

        // if (true) return;

        // -----------------------------------------------------------------------------------
        // -----------------------------------------------------------------------------------

//        ArrayList<Attribute> attributesList = Utils.getAttributes(connection);
//
//        String query =
//            "SELECT " +
//            "   [column_name], " +
//            "   COUNT([column_name]) AS CNT " +
//            "FROM " +
//            "   DATA " +
//            "GROUP BY " +
//            "   [column_name] " +
//            "ORDER BY " +
//            "   CNT DESC";
//
//        // Check possible values
//        for(Attribute a : attributesList) {
//            String q = query.replace("[column_name]", a.getName());
//
//            Statement s = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            s.execute(q);
//
//            ResultSet result = s.getResultSet();
//
//            System.out.println("---------------------------------------------------");
//            System.out.println(a.getName().toUpperCase() + " - " + a.getLabel());
//            System.out.println("---------------------------------------------------");
//            int cnt = 0;
//            while (result.next()) {
//                cnt++;
//                if (cnt > 50) {System.out.println("--break--"); break;}
//                Integer c = result.getInt("CNT");
//                System.out.println("" + c + "\t\t" + result.getString(a.getName()));
//            }
//        }
        log.info("END");
    }
}