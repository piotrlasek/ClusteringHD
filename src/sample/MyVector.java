package sample;

import org.apache.mahout.math.DenseVector;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 5/1/2015.
 */
public class MyVector {
    private int id;
    private int width;
    private DenseVector vector;
    public int clusterId = -1;
    public ArrayList<MyVector> neighbours = null;

    /**
     *
     * @param rs
     * @throws SQLException
     */
    public MyVector(ResultSet rs) throws SQLException {
        id = rs.getInt(1);

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        vector = new DenseVector(columnsNumber - 1);

        // ignore first column -> ID
        for(int i = 0; i < columnsNumber - 1; i++) {
            Integer v = null;
            //Object o = rs.getObject(i + 2);
            //if (o != null)  {
                v = rs.getInt(i + 2);
            //}

            vector.set(i, v);
        }

        vector = (DenseVector) vector.normalize();
    }

    /**
     *
     * @param set
     * @param Eps
     * @return
     */
    public ArrayList<MyVector> getNeighbours(ArrayList<MyVector> set, double Eps) {

        if (neighbours == null) {
            neighbours = new ArrayList<MyVector>();

            for (MyVector bs : set) {
                double dist = Utils.tanimotoVector(bs.getVector(), getVector());
                if (dist < Eps) {
                    neighbours.add(bs);
                }
            }
        }

        return neighbours;
    }

    /**
     *
     * @param clusterId
     */
    public void setNeighboursClusterId(int clusterId) {
        this.clusterId = clusterId;
        for(MyVector bs : neighbours) {
            bs.clusterId = clusterId;
        }
    }

    @Override
    public String toString() {
        return id + ": " + vector.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public DenseVector getVector() {
        return vector;
    }

    public void setVector(DenseVector vector) {
        this.vector = vector;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }
}
