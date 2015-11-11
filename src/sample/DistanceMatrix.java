package sample;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.util.ArrayUtil;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Created by Piotr Lasek on 10/13/2015.
 */
public class DistanceMatrix implements Serializable {

    final static Logger log = Logger.getLogger(DistanceMatrix.class);
    float[][] matrix;
    Integer[][] neighbours;
    int width;
    int height;

    /**
     *
     */
    public class DistanceComparator implements Comparator<Integer> {

        Integer[] ids;
        float[] dists;

        /**
         *
         * @param ids
         */
        public DistanceComparator(Integer[] ids) {
           this.ids = ids;
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

    /**
     *
     * @param width
     * @param height
     */
    public DistanceMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new float[this.width][this.height];
    }

    /**
     *
     * @return
     */
    public long getSize() {
        return width * height;
    }

    /**
     *
     */
    public void computeNeighbours() {
        log.info("computeNeighbours BEGIN");
        neighbours = new Integer[width][];
        // array of ids
        for(int i = 0; i < height; i++) {
            int[] idsInt = IntStream.range(0, width).toArray();
            Integer[] ids = ArrayUtils.toObject(idsInt);
            DistanceComparator dc = new DistanceComparator(ids);
            dc.setDists(matrix[i]);
            Arrays.sort(ids, dc);
            //neighbours[i] = Arrays.copyOf(ids, 200);
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
        log.info("Start.");
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
        log.info("End.");
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static DistanceMatrix read(String fileName) throws Exception {
        DistanceMatrix e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (DistanceMatrix) in.readObject();
        in.close();
        fileIn.close();
        return e;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        DistanceMatrix dm = new DistanceMatrix(12000, 12000);

        long s = System.currentTimeMillis();
        for (int i = 0; i < 12000; i++) {
            for(int j =0 ; j < 12000; j++) {
                float v = (float) Math.random();
                dm.set(i, j, v);
            }
        }
        long e = System.currentTimeMillis();

        System.out.println("Matrix building time: " + (e - s) + " ms");

        s = System.currentTimeMillis();
        dm.computeNeighbours();
        e = System.currentTimeMillis();

        System.out.println("Indexing: " + (e - s) + " ms");

        s = System.currentTimeMillis();
        Integer[] neighbours = dm.getNeighbours(1, 100);
        e = System.currentTimeMillis();

        System.out.println("Searching: " + (e - s) + " ms");
    }

}
