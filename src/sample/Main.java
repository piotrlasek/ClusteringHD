package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.mahout.math.Vector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Properties;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
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
    public static void visualize(Connection connection) throws SQLException {
        Visualization v = new Visualization(connection);


    }

    /**
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://ubuntu/diabetic";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","postgres");
        Connection conn = DriverManager.getConnection(url, props);

        Main.visualize(conn);

        /*try {
            StringBuilder sb = Utils.generateQuery(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (true) return;*/

        ArrayList<MyVector> dataset = new ArrayList<MyVector>();

        Statement statementGetBitRecords = conn.createStatement();

        System.out.println("Reading data from DB... ");
        //statementGetBitRecords.execute("SELECT * FROM data2 where id in (62021,62021,64017,66147,69691,72839,78302,78370,78836,82493,85477,98314,106592,121731,124452,124816,5682,6745,7727,14975,18295,24127,32321,32464,32616,34166,39290,42961,47515,48123,50406,51371,58292,108152,114431,123099,43710,54309,21913,32689)");
        statementGetBitRecords.execute("SELECT * FROM data5 limit 60000");
        System.out.print("Done.");

        ResultSet resultSetBitRecords = statementGetBitRecords.getResultSet();

        int count = 0;

        System.out.println("Constructing an array of bit vectors... ");

        while(resultSetBitRecords.next()) {
            MyVector mbs = new MyVector(resultSetBitRecords);
            dataset.add(mbs);
            count++;
        }
        System.out.print("Done.\n");

        // test
        /*
        MyVector v = dataset.get(0);

        for(MyVector v2 : dataset) {
            double x = Utils.tanimotoVector(v.getVector(), v2.getVector());
            if (x >= 0.68) System.out.println(x);
        }

          if (true)
            return;
        /**/

        DbScanVec dbscan = new DbScanVec(dataset);
        System.out.println("Starting DBSCAN...");
        System.out.println("MinPts: " + dbscan.getMinPts() + ", Eps: " + dbscan.getEps());
        long start = System.currentTimeMillis();
        dbscan.run();
        System.out.println("Done.");
        long end = System.currentTimeMillis();
        System.out.println("Run-time: " + ((end - start) / 1000) + " s");

        System.out.println("---------------------------------------------------");
        ArrayList<ClusterVect> clusters = dbscan.getClusters();


        // TODO: RETURN !!!
        // -----------------------------------------------------------------------------------
        if (true) return;

        ArrayList<Attribute> attributes = Utils.getAttributes(conn);

        String query =
            "SELECT " +
            "   [column_name], " +
            "   COUNT([column_name]) AS CNT " +
            "FROM " +
            "   DATA " +
            "GROUP BY " +
            "   [column_name] " +
            "ORDER BY " +
            "   CNT DESC";

        // Check possible values
        for(Attribute a : attributes) {
            String q = query.replace("[column_name]", a.getName());

            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            s.execute(q);

            ResultSet result = s.getResultSet();

            System.out.println("---------------------------------------------------");
            System.out.println(a.getName().toUpperCase() + " - " + a.getLabel());
            System.out.println("---------------------------------------------------");
            int cnt = 0;
            while (result.next()) {
                cnt++;
                if (cnt > 50) {System.out.println("--break--"); break;}
                Integer c = result.getInt("CNT");
                System.out.println("" + c + "\t\t" + result.getString(a.getName()));
            }
        }

        // TODO: RETURN!!!
        // -----------------------------------------------------------------------------------

        if (true) return;

        int length = 1207;

        BitSet bs = new BitSet(length);

        // TODO: mahout vector...
        Vector vector;

        for (int i = 0; i < length; i++) {
            bs.set(i, true);
        }

        System.out.println("Bit set:");
        System.out.println(bs);

        System.out.println("Generating list of bit sets...");

        ArrayList<BitSet> sets = new ArrayList<BitSet>();

        for (int i = 0; i < length * length; i++) {
            sets.add(new BitSet(length));
        }

        System.out.println("Done.");

        System.out.println("Generating data...");

        ArrayList<Integer[]> data = new ArrayList<Integer[]>();

        for (int i = 0; i < 100000; i++) {
            Integer[] row = new Integer[length];
            for (int j = 0; j < length; j++) {
                row[j] = 1;
            }
            data.add(row);
        }
        System.out.println("Done.");

        // launch(args);
    }
}
