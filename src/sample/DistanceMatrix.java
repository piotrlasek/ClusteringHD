package sample;

import org.apache.log4j.Logger;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Piotr Lasek on 10/13/2015.
 */
public class DistanceMatrix implements Serializable {

    final static Logger log = Logger.getLogger(DistanceMatrix.class);

    float[][] matrix;
    Integer[][] neighbours;

    ArrayList<NominalNumericalAttribute> attributes;
    ArrayList<NominalNumericalObject> points;
    // DistanceMeasure
    int xxxx;

    /**
     *
     */
    public void computeDistances() {
        log.info("computeDistances Start");
        for (int i = 0; i < points.size(); i++) {
            NominalNumericalObject oi = points.get(i);
            for (int j = i; j < points.size(); j++) {
                NominalNumericalObject oj = points.get(j);
                float dist = oi.distance(oj);
                set(i, j, dist);
                set(j, i, dist);
            }
            if (i % 1000 == 0) {
                log.info("   " + (int) (((float) i / points.size()) * 100) + "%");
            }
        }
        log.info("computeDistances End");
    }

    // -------------------------------------------------------------------------------------------------------------

    /**
     *
     */
    public class DistanceComparator implements Comparator<Integer> {

        //Integer[] ids;
        float[] dists;

        /**
         *
         * @param dists
         */
        public DistanceComparator(float[] dists) {
           //this.ids = ids;
            this.dists = dists;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            Float v1 = dists[o1];
            Float v2 = dists[o2];

            return v1.compareTo(v2);
        }

        public void setDists(float[] dists) {
            this.dists = dists;
        }
    }

    // -------------------------------------------------------------------------------------------------------------

    /**
     *
     * @param points
     */
    public DistanceMatrix(ArrayList<NominalNumericalObject> points) {
        this.points = points;
        this.attributes = points.get(0).getNnAttributes();
        matrix = new float[points.size()][points.size()];
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalAttribute> getAttributes() {
        return attributes;
    }

    /**
     *
     * @return
     */
    public long getSize() {
        return points.size()*points.size();
    }

    public int getWidth() {
        return points.size();
    }

    public int getHeight() {
        return points.size();
    }

    /**
     *
     */
    public void computeNeighbours() {
        log.info("computeNeighbours BEGIN");
        neighbours = new Integer[getWidth()][];

        Integer[] idsT = new Integer[getHeight()];
        for(int i = 0; i < idsT.length; i++) { idsT[i] = i; }

        // array of ids
        for(int i = 0; i < getHeight(); i++) {
            Integer[] ids = idsT.clone();
            DistanceComparator dc = new DistanceComparator(matrix[i]);
            Arrays.sort(ids, dc);
            neighbours[i] = ids;
        }
        log.info("computeNeighbours END");
    }

    /**
     *
     * @param i
     * @param k
     * @return
     */
    public Integer[] getNeighbours(int i, int k) {
        Integer[] ne = this.neighbours[i];
        return ne;
    }

    /**
     *
     * @param i
     * @param eps
     * @param size
     * @return
     */
    public Integer[] getNeighbours(int i, double eps, MyInteger size) {
        Integer[] ne = this.neighbours[i];
        float dist;

        int s = 0;
        for(Integer nId : neighbours[i]) {
            dist = getDistance(i, nId);
            if (dist > eps) {
                break;
            }
            s++;
        }
        size.setValue(s);

        return ne;
    }

    /**
     *
     * @param x
     * @param y
     * @param dist
     */
    public void set(int x, int y, float dist) {
        matrix[x][y] = dist;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public float getDistance(int x, int y) {
        return matrix[x][y];
    }

    /**
     *
     * @param fileName
     */
    public void save(String fileName) {
        log.info("save Start.");
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
        log.info("save End.");
    }

    /**
     *
     * @param connection
     */
    public void save(Connection connection) {

        StringBuilder createTableBuilder = new StringBuilder("create table dists (");

        for(int j = 0; j < getWidth(); j++) {

        }

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableBuilder.toString());
        } catch (SQLException e) {
            log.error(e);
        }



        for(int i = 0; i < getHeight(); i++) {
            for(int j = 0; j < getWidth(); j++) {




            }
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static DistanceMatrix read(String fileName) throws Exception {
        log.info("read Start.");
        DistanceMatrix e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (DistanceMatrix) in.readObject();
        in.close();
        fileIn.close();
        log.info("read End.");
        return e;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        log.info("START");

        log.info("Preparing data...");
        long s = System.currentTimeMillis();

        ArrayList<NominalNumericalAttribute> nnal = new ArrayList<>();
        for (int i = 0; i < 1300; i++) {
            NominalNumericalAttribute nna = new NominalNumericalAttribute(i, "a" + i, "", "", 0f, 1f, true);
            nnal.add(nna);
        }

        ArrayList<NominalNumericalObject> nnol = new ArrayList<>();
        for (int i = 0; i < 17000; i++) {
            NominalNumericalObject nno = new NominalNumericalObject();
            nno.addAttributes(nnal);
            nno.setId(i);
            for (int j = 0; j < 1300; j++) {
                String attribute = nnal.get(j).getName();
                nno.addValueNumerical(attribute, "R", (float) Math.random());
            }

            nnol.add(nno);
        }

        DistanceMatrix dm = new DistanceMatrix(nnol);

        long e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("Computing distances...");
        s = System.currentTimeMillis();
        //dm.computeDistances();
        for(int i = 0; i < dm.getHeight(); i++) {
            for(int j = 0; j < dm.getHeight(); j++) {
                dm.set(i, j, (float) Math.random());
            }
        }
        e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("Determining neighbours...");
        s = System.currentTimeMillis();
        dm.computeNeighbours();
        e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("Testing neighbours...");
        s = System.currentTimeMillis();
        for(int i = 0; i < dm.getHeight(); i++) {
            float prevDist = -1f;
            for (int j = 0; j < dm.getHeight(); j++) {
                int id = dm.neighbours[i][j];
                float dist = dm.getDistance(i, id);
                if (dist < prevDist) {
                    log.error("Wrong distance!");
                    break;
                }
                prevDist = dist;
            }
        }

        e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("Searching " + dm.getHeight() + " objects... ");
        s = System.currentTimeMillis();
        for(int i = 0; i < dm.getHeight(); i++) {
            Integer[] neighbours = dm.getNeighbours(i, 100);
        }
        e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("Saving to file...");
        s = System.currentTimeMillis();
        dm.save("test-matrix-file.ser");
        e = System.currentTimeMillis();
        log.info((e - s) + " ms");
        // -------------------------------------------------------------
        log.info("DONE");
    }
}

