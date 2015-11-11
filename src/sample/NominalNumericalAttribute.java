package sample;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalAttribute implements Serializable {

    private String name;
    private String description;
    private boolean type; // true -- number, false -- text
    private Float min;
    private Float max;
    private Float[] minMax;

    private int id;
    private boolean used;
    private String format;

    /**
     *
     * @param name
     * @param type
     */
    NominalNumericalAttribute(String name, boolean type) {
        this.setName(name);
        this.setType(type);
    }

    NominalNumericalAttribute(int id, String name, String description, String format, Float min, Float max, boolean numerical ) {
        this.setId(id);
        this.setName(name);
        this.setType(numerical);
        this.setFormat(format);
        this.min = min;
        this.max = max;
        this.setDescription(description);
    }

    /**
     *
     * @return
     */
    public boolean getType() {
        return isType();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Float[] getMinMax() {
        return minMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
