package sample;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class DbScanVec {

    private ArrayList<NominalNumericalObject> dataset;
    private ArrayList<ClusterVect> clusters;
    private ArrayList<NominalNumericalObject> noise;
    private double Eps;
    private int MinPts;
    private DistanceMatrix distanceMatrix;

    final static Logger log = Logger.getLogger(DbScanVec.class);

    /**
     * @param dataset
     * @param distanceMatrix
     */
    public DbScanVec(ArrayList<NominalNumericalObject> dataset, DistanceMatrix distanceMatrix) {
        log.info("DbScanVec START");
        setDistanceMatrix(distanceMatrix);
        setDataset(dataset);
        setClusters(new ArrayList<ClusterVect>());
        setNoise(new ArrayList<NominalNumericalObject>());
        log.info("DbScanVec STOP");
    }

    /**
     *
     */
    public void run() throws FileNotFoundException {
        log.info("run START");
        int clusterId = 1;
        PrintWriter pw = new PrintWriter(Main.filePrefix + "-clusters.txt");
        for(NominalNumericalObject p : getDataset()) {
            if (p.clusterId == -1) { // UNCLASSIFIED
               ArrayList<NominalNumericalObject> clusterPoints = expandCluster(getDataset(), p, clusterId);
               if (clusterPoints.size() > 0) {
                   log.info("   cluster " + clusterId + " created.");
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
        log.info("run END");
    }

   /**
     *
     * @param set
     * @param point
     * @param clusterId
     * @return
     */
    public ArrayList<NominalNumericalObject> expandCluster(ArrayList<NominalNumericalObject> set, NominalNumericalObject point, int clusterId) {
        ArrayList<NominalNumericalObject> clusterPoints = new ArrayList<NominalNumericalObject>();
        //ArrayList<NominalNumericalObject> seeds = point.getNeighbours(getDataset(), getEps());
        ArrayList<NominalNumericalObject> seeds = getNeighbours(point, getEps());

        if (seeds.size() < getMinPts()) {
            point.clusterId = 0; // NOISE
            getNoise().add(point);
        } else {
            point.clusterId = clusterId;
            clusterPoints.add(point);

            //clusterPoints.addAll(point.getNeighbours(getDataset(), getEps()));
            clusterPoints.addAll(getNeighbours(point, getEps()));

            //while(seeds.size() > 0) {
            while(true) {
                NominalNumericalObject currentP = seeds.remove(0);
                //ArrayList<NominalNumericalObject> result = currentP.getNeighbours(set, getEps());
                ArrayList<NominalNumericalObject> result = getNeighbours(currentP, getEps());
                if (result.size() >= getMinPts()) {
                    //for (NominalNumericalObject resultP : result) {  // masakra!
                    for(int i = 0; i < result.size(); i++) {
                        NominalNumericalObject resultP = result.get(i);
                        if (resultP.clusterId == -1 || resultP.clusterId == 0) {
                            if (resultP.clusterId == -1) seeds.add(resultP);
                            resultP.clusterId = clusterId;
                            if (!clusterPoints.contains(resultP)) {
                                clusterPoints.add(resultP);
                            }
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

    /**
     *
     * @param o
     * @param Eps
     * @return
     */
    ArrayList<NominalNumericalObject> getNeighbours(NominalNumericalObject o, double Eps) {
        ArrayList<NominalNumericalObject> neighbours = new ArrayList<>();

        int id = o.getId();

        MyInteger size = new MyInteger();
        Integer[ ] allNeighbours = distanceMatrix.getNeighbours(id, Eps, size);

        for(int i = 0; i < size.getValue(); i++) { //Integer nId : allNeighbours) {
            NominalNumericalObject nno = dataset.get(i);
            neighbours.add(nno);
        }

        return neighbours;
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

    /**
     *
     * @param distanceMatrix
     */
    public void setDistanceMatrix(DistanceMatrix distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}
