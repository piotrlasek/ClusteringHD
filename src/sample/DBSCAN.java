package sample;

import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class DBSCAN {
    private ArrayList<MyBitSet> dataset;
    private ArrayList<Cluster> clusters;
    private ArrayList<MyBitSet> noise;
    private double Eps = 0.9;
    private int MinPts = 15;

    /**
     *
     * @param dataset
     */
    public DBSCAN(ArrayList<MyBitSet> dataset) {
        this.setDataset(dataset);
        setClusters(new ArrayList<Cluster>());
        setNoise(new ArrayList<MyBitSet>());
    }

    /**
     *
     */
    public void run() {
        System.out.println("Starting DBSCAN...");
        int clusterId = 1;

        for(MyBitSet p : getDataset()) {
            if (p.clusterId == -1) { // UNCLASSIFIED
               ArrayList<MyBitSet> clusterPoints = ExpandCluster(getDataset(), p, clusterId);
               if (clusterPoints.size() > 0) {
                   System.out.println("Cluster: " + clusterId);
                   Cluster c = new Cluster(clusterId);
                   c.addAll(clusterPoints);
                   getClusters().add(c);
                   clusterId++;
               }
            }
        }
        System.out.println("Done.");
    }

    /**
     *
     * @param set
     * @param point
     * @param clusterId
     * @return
     */
    public ArrayList<MyBitSet> ExpandCluster(ArrayList<MyBitSet> set, MyBitSet point, int clusterId) {
        ArrayList<MyBitSet> clusterPoints = new ArrayList<MyBitSet>();
        ArrayList<MyBitSet> seeds = point.getNeighbours(getDataset(), getEps());

        if (seeds.size() < getMinPts()) {
            point.clusterId = 0; // NOISE
            getNoise().add(point);
        } else {
            point.setNeighboursClusterId(clusterId);
            clusterPoints.add(point);
            clusterPoints.addAll(point.getNeighbours(getDataset(), getEps()));

            while(seeds.size() > 0) {
                MyBitSet currentP = seeds.remove(0);
                ArrayList<MyBitSet> result = currentP.getNeighbours(set, getEps());
                if (result.size() >= getMinPts()) {
                    for (MyBitSet resultP : result) {
                        if (resultP.clusterId == -1 || resultP.clusterId == 0) {
                            if (resultP.clusterId == -1) seeds.add(resultP);
                            resultP.clusterId = clusterId;
                            clusterPoints.add(resultP);
                        }
                    }
                }
            } // seeds.size() > 0
        }

        return clusterPoints;
    }

    public ArrayList<MyBitSet> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<MyBitSet> dataset) {
        this.dataset = dataset;
    }

    public ArrayList<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<Cluster> clusters) {
        this.clusters = clusters;
    }

    public ArrayList<MyBitSet> getNoise() {
        return noise;
    }

    public void setNoise(ArrayList<MyBitSet> noise) {
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
