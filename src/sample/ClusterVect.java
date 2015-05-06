package sample;

import java.util.ArrayList;

/**
 * Created by piotr on 15-04-20.
 */
public class ClusterVect {

    int clusterId = -1;

    ArrayList<MyVector> points;

    /**
     *
     * @param clusterId
     */
    public ClusterVect(int clusterId) {
        this.clusterId = clusterId;
        points = new ArrayList<MyVector>();
    }

    /**
     *
     * @param point
     */
    public void addPoint(MyVector point) {
        points.add(point);
    }

    /**
     *
     * @return
     */
    public ArrayList<MyVector> getPoints() {
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

    public void addAll(ArrayList<MyVector> list) {
       points.addAll(list);
    }

}
