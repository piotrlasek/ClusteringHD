package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by Piotr Lasek on 15-04-17.
 */
public class MyBitSet {

    private int id;
    private int width;
    private BitSet bits;
    public int clusterId = -1;
    public ArrayList<MyBitSet> neighbours = null;

    /**
     *
     * @param rs
     * @throws SQLException
     */
    public MyBitSet(ResultSet rs) throws SQLException {

        width = 727;

        bits = new BitSet(width);

        id = rs.getInt(1);

        for(int i = 1; i <= width; i++) {

            int v = rs.getInt(i);

            if (v == 1) {
                bits.set(i - 1);
                // System.out.print(1);
            }
        }
    }

    /**
     *
     * @param set
     * @param Eps
     * @return
     */
    public ArrayList<MyBitSet> getNeighbours(ArrayList<MyBitSet> set, double Eps) {

        if (neighbours == null) {
            neighbours = new ArrayList<MyBitSet>();

            for (MyBitSet bs : set) {
                double dist = Utils.tanimoto(bs.getBits(), getBits());
                if ( dist < Eps) {
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
        for(MyBitSet bs : neighbours) {
            bs.clusterId = clusterId;
        }
    }

    @Override
    public String toString() {
        return id + ": " + bits.toString();
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

    public BitSet getBits() {
        return bits;
    }

    public void setBits(BitSet bits) {
        this.bits = bits;
    }
}
