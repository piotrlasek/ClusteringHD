package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalAttribute {

    private String name;
    boolean type; // true -- number, false -- text
    private Float min;
    private Float max;
    private Float[] minMax;

    /**
     *
     * @param name
     * @param type
     */
    NominalNumericalAttribute(String name, boolean type) {
        this.setName(name);
        this.type = type;
    }

    NominalNumericalAttribute(String name, boolean type, Float min, Float max) {
        this.setName(name);
        this.type = type;
        this.min = min;
        this.max = max;
    }

    /**
     *
     * @return
     */
    public boolean getType() {
        return type;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        boolean result = true;

        if (o instanceof NominalNumericalAttribute) {
            NominalNumericalAttribute nna = (NominalNumericalAttribute) o;
            if (!getName().equals(nna.getName())) {
                result = false;
            }
        } else if (o instanceof  String) {
            String s = (String) o;
            if (!o.equals(s)) {
                result = false;
            }
        }

        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public void setMinMax(Float[] minMax) {
        try {
            this.min = minMax[0];
            this.max = minMax[1];
        } catch(Exception e) {

        }
    }

    /**
     *
     * @param connection
     */
    public void updateAttribute(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();

        String query = "UPDATE attributes SET numerical = " + this.getType() + ", " +
                "min = " + this.getMin() + ", " +
                "max = " + this.getMax() + " " +
                "WHERE name LIKE '" + this.getName() + "%'";

        //System.out.println(query);
        statement.execute(query);
    }
}
