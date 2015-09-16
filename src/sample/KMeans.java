package sample;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.mahout.clustering.classify.WeightedPropertyVectorWritable;
import org.apache.mahout.clustering.kmeans.KMeansDriver;
import org.apache.mahout.clustering.kmeans.RandomSeedGenerator;
import org.apache.mahout.common.HadoopUtil;
import org.apache.mahout.common.distance.DistanceMeasure;
import org.apache.mahout.common.distance.EuclideanDistanceMeasure;
import org.apache.mahout.math.NamedVector;
import org.apache.mahout.math.VectorWritable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 7/16/2015.
 */
public class KMeans extends DisplayClustering {

    ArrayList<MyVector> dataset;
    int k = 7;
    private ArrayList<ClusterVect> clusters;

    /**
     *
     */
    KMeans(ArrayList<MyVector> dataset, int k) {
        initialize();
        this.dataset = dataset;
        this.k = k;
        clusters = new ArrayList<ClusterVect>();
    }

    /**
     *
     */
    protected void prepareData() {
        for(MyVector mv : dataset) {
            VectorWritable vw = new VectorWritable(mv.getVector());
            System.out.println(vw.toString());
            SAMPLE_DATA.add(vw);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<ClusterVect> getClusters() {
       return clusters;
    }

    /**
     *
     * @throws IOException
     */
    public void run() throws Exception {
//        DistanceMeasure measure = new ManhattanDistanceMeasure();
        DistanceMeasure measure = new EuclideanDistanceMeasure();

        Path samples = new Path("samples");
        Path output = new Path("output");
        Configuration conf = new Configuration();
        HadoopUtil.delete(conf, samples);
        HadoopUtil.delete(conf, output);

        double convergenceDelta = 0.05;
        int numClusters = k;
        int maxIterations = 10;

        // read data ...
        // RandomUtils.useTestSeed();
        // generateSamples();

        prepareData();
        writeSampleData(samples);

        // run clustering ...
        try {
            runSequentialKMeansClusterer(
                    conf,
                    samples,
                    output,
                    measure,
                    numClusters,
                    maxIterations,
                    convergenceDelta
            );

            readResult(conf);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param conf
     * @throws IOException
     */
    private void readResult(Configuration conf) throws Exception {
        FileSystem fs = FileSystem.get(conf);
        String path = "output/" + org.apache.mahout.clustering.Cluster.CLUSTERED_POINTS_DIR + "/part-m-0";
        SequenceFile.Reader reader = new SequenceFile.Reader(fs,new Path(path), conf);
        IntWritable key = new IntWritable();
        WeightedPropertyVectorWritable value = new WeightedPropertyVectorWritable();

        ArrayList<String> keys = new ArrayList<String>();

        int ind = 0;
        while (reader.next(key, value)) {
            System.out.println();
            if (!keys.contains(key.toString())) {
                keys.add(ind, key.toString());
                ind++;
            }

            if (value == null) {
                System.out.println("NULL");
            } else {
                int clusterId = keys.indexOf(key.toString());
                System.out.println(value.toString() + " belongs to cluster " + clusterId);
                NamedVector v = (NamedVector) value.getVector();
                String vectorName = v.getName();
                Integer i = new Integer(vectorName.replace("sample_", ""));

                ClusterVect cv = new ClusterVect(clusterId);
                int index = clusters.indexOf(cv);
                if (index >= 0) {
                    cv = clusters.get(index);
                } else {
                    clusters.add(cv);
                }

                MyVector mv = dataset.get(i);
                mv.setClusterId(clusterId);

                cv.addPoint(mv);
            }
        }
    }

    /**
     *
     * @param conf
     * @param samples
     * @param output
     * @param measure
     * @param numClusters
     * @param maxIterations
     * @param convergenceDelta
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    private static void runSequentialKMeansClusterer(Configuration conf, Path samples, Path output,
                                                     DistanceMeasure measure, int numClusters, int maxIterations,
                                                     double convergenceDelta)
            throws IOException, InterruptedException, ClassNotFoundException {
        Path clustersIn = new Path(output, "random-seeds");
        RandomSeedGenerator.buildRandom(conf, samples, clustersIn, numClusters, measure);
        KMeansDriver.run(samples, clustersIn, output, convergenceDelta, maxIterations, true, 0.0, true);
        loadClustersWritable(output);
    }

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        plotSampleData((Graphics2D) g);
        plotClusters((Graphics2D) g);
    }

}
