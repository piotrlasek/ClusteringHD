package sample;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Piotr Lasek on 10/13/2015.
 */
public class DistanceMatrix implements Serializable {

    float[][] matrix;
    int[][] neighbours;
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
         * @param dists
         */
        public DistanceComparator(Integer[] ids, float[] dists) {
           this.ids = ids;
           this.dists = dists;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            float v1 = dists[o1];
            float v2 = dists[o2];

            if (v1 > v2)
                return 1;
            else
                return -1;
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
     */
    public void computeNeighbours() {
        // array of ids
        Integer[] ids = new Integer[width];
        for (int j = 0; j < width; j++) {
            ids[j] = j;
        }

        for(int i = 0; i < height; i++) {
            System.out.println("Row: " + i);
            for(int j = 0; j < width; j++) {
                float[] dists = Arrays.copyOf(matrix[i], width);
                DistanceComparator dc = new DistanceComparator(ids, dists);
                Arrays.sort(ids, dc);
            }
        }
    }

    /**
     *
     * @param p
     * @param k
     * @return
     */
    public int[] getNeighbours(int p, int k) {
        int[] ne = this.neighbours[p];
        return Arrays.copyOfRange(ne, 0, k);
    }

    /**
     *
     * @param x
     * @param y
     * @param dist
     */
    public void set(int x, int y, float dist) {
        matrix[x][y] = dist;
        matrix[y][x] = dist;
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
     *
     */
    public void save(String fileName) {
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
        int[] neighbours = dm.getNeighbours(1, 100);
        e = System.currentTimeMillis();

        System.out.println("Searching: " + (e - s) + " ms");
    }

}
