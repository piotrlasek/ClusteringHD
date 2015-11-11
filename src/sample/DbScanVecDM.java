package sample;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class DbScanVecDM {

    private ArrayList<NominalNumericalObject> dataset;
    private ArrayList<ClusterVect> clusters;
    private ArrayList<NominalNumericalObject> noise;
    public static double Eps;
    public static int MinPts;
    DistanceMatrix dm;

    /**
     *
     * @param dataset
     */
    public DbScanVecDM(ArrayList<NominalNumericalObject> dataset) {
        setDataset(dataset);
        setClusters(new ArrayList<ClusterVect>());
        setNoise(new ArrayList<NominalNumericalObject>());

        // try to restore distance matrix
        try {
            dm = DistanceMatrix.read("distance-matrix.ser");
        } catch (Exception e) {
            dm = null;
            e.printStackTrace();
        }

        if (dm == null) {
            dm = new DistanceMatrix(dataset.size(), dataset.size());
            System.out.println("Create distance matrix");
            for (NominalNumericalObject nno1 : dataset) {
                for (NominalNumericalObject nno2 : dataset) {
                    float dist = nno1.distance(nno2);
                    dm.set(nno1.getId(), nno2.getId(), dist);
                }
            }
        }
    }

    /**
     *
     */
    public void run() throws FileNotFoundException {
        int clusterId = 1;
        PrintWriter pw = new PrintWriter(Main.filePrefix + "-clusters.txt");
        for(NominalNumericalObject p : getDataset()) {
            if (p.clusterId == -1) { // UNCLASSIFIED
               ArrayList<NominalNumericalObject> clusterPoints = ExpandCluster(getDataset(), p, clusterId);
               if (clusterPoints.size() > 0) {
                   System.out.println(" > cluster " + clusterId + " created.");
                   ClusterVect c = new ClusterVect(clusterId);
                   c.addAll(clusterPoints);
                   getClusters().add(c);
                   String cString = c.toString();
                   pw.write(cString);
                   clusterId++;
               }
            }
        }
        pw.close();
    }

   /**
     *
     * @param set
     * @param point
     * @param clusterId
     * @return
     */
    public ArrayList<NominalNumericalObject> ExpandCluster(ArrayList<NominalNumericalObject> set, NominalNumericalObject point, int clusterId) {
        ArrayList<NominalNumericalObject> clusterPoints = new ArrayList<NominalNumericalObject>();

        ArrayList<NominalNumericalObject> seeds = point.getNeighbours(getDataset(), getEps());

        if (seeds.size() < getMinPts()) {
            point.clusterId = 0; // NOISE
            getNoise().add(point);
        } else {
            point.clusterId = clusterId;

            clusterPoints.add(point);
            clusterPoints.addAll(point.getNeighbours(getDataset(), getEps()));

            //while(seeds.size() > 0) {
            while(true) {
                NominalNumericalObject currentP = seeds.remove(0);
                ArrayList<NominalNumericalObject> result = currentP.getNeighbours(set, getEps());
                if (result.size() >= getMinPts()) {
                    //for (NominalNumericalObject resultP : result) {  // masakra!
                    for(int i = 0; i < result.size(); i++) {
                        NominalNumericalObject resultP = result.get(i);
                        if (resultP.clusterId == -1 || resultP.clusterId == 0) {
                            if (resultP.clusterId == -1) seeds.add(resultP);
                            resultP.clusterId = clusterId;
                            if (!clusterPoints.contains(resultP))
                                clusterPoints.add(resultP);
                        }
                    }
                }
                if (seeds.size() == 0) {
                    break;
                }
            } // seeds.size() > 0
        }

        return clusterPoints;
    }

    public ArrayList<NominalNumericalObject> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<NominalNumericalObject> dataset) {
        this.dataset = dataset;
    }

    public ArrayList<ClusterVect> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<ClusterVect> clusters) {
        this.clusters = clusters;
    }

    public ArrayList<NominalNumericalObject> getNoise() {
        return noise;
    }

    public void setNoise(ArrayList<NominalNumericalObject> noise) {
        this.noise = noise;
    }

    public double getEps() {
        return Eps;
    }

    public void setEps(double eps) {
        Eps = eps;
    }

    public int getMinPts() {
        return MinPts;
    }

    public void setMinPts(int minPts) {
        MinPts = minPts;
    }
}
