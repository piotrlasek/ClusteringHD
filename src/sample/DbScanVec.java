package sample;

import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class DbScanVec {
    private ArrayList<MyVector> dataset;
    private ArrayList<ClusterVect> clusters;
    private ArrayList<MyVector> noise;
    private double Eps = 0.70;
    private int MinPts = 20;

    /**
     *
     * @param dataset
     */
    public DbScanVec(ArrayList<MyVector> dataset) {
        this.setDataset(dataset);
        setClusters(new ArrayList<ClusterVect>());
        setNoise(new ArrayList<MyVector>());
    }

    /**
     *
     */
    public void run() {
        int clusterId = 1;

        for(MyVector p : getDataset()) {
            if (p.clusterId == -1) { // UNCLASSIFIED
               ArrayList<MyVector> clusterPoints = ExpandCluster(getDataset(), p, clusterId);
               if (clusterPoints.size() > 0) {
                   ClusterVect c = new ClusterVect(clusterId);
                   c.addAll(clusterPoints);
                   getClusters().add(c);
                   printCluster(c);
                   clusterId++;
               }
            }
        }
    }

    private void printCluster(ClusterVect c) {
        ArrayList<MyVector> points = c.getPoints();
        System.out.println("Cluter: " + c.getClusterId() + ", size: " + points.size());
        System.out.print("Points: ");
        for (MyVector p : points) {
            System.out.print(p.getId() + ",");
        }
        System.out.println();
    }

    /**
     *
     * @param set
     * @param point
     * @param clusterId
     * @return
     */
    public ArrayList<MyVector> ExpandCluster(ArrayList<MyVector> set, MyVector point, int clusterId) {

        ArrayList<MyVector> clusterPoints = new ArrayList<MyVector>();

        ArrayList<MyVector> seeds = point.getNeighbours(getDataset(), getEps());

        if (seeds.size() < getMinPts()) {
            point.clusterId = 0; // NOISE
            getNoise().add(point);
        } else {
            point.setNeighboursClusterId(clusterId);

            clusterPoints.add(point);
            clusterPoints.addAll(point.getNeighbours(getDataset(), getEps()));

            while(seeds.size() > 0) {
                MyVector currentP = seeds.remove(0);
                ArrayList<MyVector> result = currentP.getNeighbours(set, getEps());
                if (result.size() >= getMinPts()) {
                    for (MyVector resultP : result) {
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

    public ArrayList<MyVector> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<MyVector> dataset) {
        this.dataset = dataset;
    }

    public ArrayList<ClusterVect> getClusters() {
        return clusters;
    }

    public void setClusters(ArrayList<ClusterVect> clusters) {
        this.clusters = clusters;
    }

    public ArrayList<MyVector> getNoise() {
        return noise;
    }

    public void setNoise(ArrayList<MyVector> noise) {
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
