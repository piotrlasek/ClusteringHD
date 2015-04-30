package sample;

import java.util.ArrayList;

/**
 * Created by piotr on 15-04-20.
 */
public class Cluster {

    int clusterId = -1;

    ArrayList<MyBitSet> points;

    /**
     *
     * @param clusterId
     */
    public Cluster(int clusterId) {
        this.clusterId = clusterId;
        points = new ArrayList<MyBitSet>();
    }

    /**
     *
     * @param point
     */
    public void addPoint(MyBitSet point) {
        points.add(point);
    }

    /**
     *
     * @return
     */
    public ArrayList<MyBitSet> getPoints() {
        return points;
    }

    /**
     *
     * @param clusterId
     */
    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     *
     * @return
     */
    public int getClusterId() {
        return clusterId;
    }

    public void addAll(ArrayList<MyBitSet> list) {
       points.addAll(list);
    }

}
