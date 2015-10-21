package sample;

import java.util.ArrayList;

/**
 * Created by piotr on 15-04-20.
 */
public class ClusterVect {

    int clusterId = -1;

    ArrayList<NominalNumericalObject> points;

    private String pointsString = null;

    /**
     *
     * @param clusterId
     */
    public ClusterVect(int clusterId) {
        this.clusterId = clusterId;
        points = new ArrayList<NominalNumericalObject>();
    }

    /**
     *
     * @param point
     */
    public void addPoint(NominalNumericalObject point) throws Exception {
        points.add(point);
        if (point.clusterId != this.clusterId) {
            throw new Exception("Wrong cluster.");
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalObject> getPoints() {
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

    public void addAll(ArrayList<NominalNumericalObject> list) {
       points.addAll(list);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if (pointsString == null) {

            StringBuilder sb = new StringBuilder();
            ArrayList<NominalNumericalObject> points = this.getPoints();

            //sb.append("Cluter: " + this.getClusterId() + ", size: " + points.size() + "\n");
            //sb.append("Points: ");
            int cnt = 0;
            for (NominalNumericalObject p : points) {
                if (cnt > 0) sb.append(", ");
                sb.append(p.getId());
                cnt++;
            }

            pointsString = sb.toString();
        }

        return pointsString;
    }

    @Override
    public boolean equals(Object o) {

        ClusterVect cv = (ClusterVect) o;
        boolean result = false;

        if(this.getClusterId() == cv.getClusterId()) {
            result = true;
        }

        return result;
    }
}
