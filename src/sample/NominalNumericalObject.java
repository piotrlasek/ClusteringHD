package sample;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalObject implements Serializable {

    private int id;
    private int dbId;
    private ResultSet values;
    private ArrayList<NominalNumericalAttribute> nnAttributes;  // a reference to attributes list
    private ArrayList<NominalNumericalAttributeValue> nnValues;
    private ArrayList<NominalNumericalObject> neighbours;
    public int clusterId = -1;

    /**
     * The constructor
     */
    public NominalNumericalObject() {
        nnAttributes = new ArrayList<NominalNumericalAttribute>();
        nnValues = new ArrayList<NominalNumericalAttributeValue>();
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalAttributeValue> getValues() {
        return nnValues;
    }


    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalAttribute> getNnAttributes() {
        return nnAttributes;
    }

    /**
     *
     * @param nna
     * @return
     */
    public float distance(NominalNumericalObject nna) {
        float dist = 0;
        int attributesCount = this.nnAttributes.size();
        ArrayList<NominalNumericalAttributeValue> nnValues2 = nna.getValues();

        for(int i = 0; i < attributesCount; i++) {
            NominalNumericalAttributeValue nnav1 = nnValues.get(i);
            NominalNumericalAttributeValue nnav2 = nnValues2.get(i);
            if (nnav1.isNumerical() && nnav1.getFloat() != -1f &&
                    nnav2.isNumerical() && nnav2.getFloat() != -1f) {
                dist += Math.abs(nnav1.getFloat() - nnav2.getFloat());
            } else {
                if (!nnav1.getString().equals(nnav2.getString())) {
                    dist += 1.0;
                }
            }
        }

        dist /= (float) attributesCount;

        return dist;
    }

    /**
     *
     * @param nnAttributes
     */
    public void addAttributes(ArrayList<NominalNumericalAttribute> nnAttributes) {
        this.nnAttributes.addAll(nnAttributes);
    }

    /**
     *
     * @param attribute
     * @param value
     */
    public void addValue(String attribute, String value) {
        NominalNumericalAttribute nnaTmp = new NominalNumericalAttribute(attribute, false);
        int index = nnAttributes.indexOf(nnaTmp);
        NominalNumericalAttribute nna = nnAttributes.get(index);
        NominalNumericalAttributeValue nnav = new NominalNumericalAttributeValue(value, nna.getType());
        nnValues.add(nnav);
    }

    private void addValueNumerical(String attribute, String nominalValue, Float numericalValue) {
        NominalNumericalAttribute nnaTmp = new NominalNumericalAttribute(attribute, false);
        int index = nnAttributes.indexOf(nnaTmp);
        NominalNumericalAttribute nna = nnAttributes.get(index);
        NominalNumericalAttributeValue nnav = new NominalNumericalAttributeValue(nominalValue, numericalValue,
            nna.getType());
        nnValues.add(nnav);
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<NominalNumericalObject> dataset = new ArrayList<NominalNumericalObject>();

        String[] attributes = new String[]{"name", "age", "city"};
        boolean[] types = new boolean[]{false, true, false};

        ArrayList<NominalNumericalAttribute> nnAttributes = new ArrayList<NominalNumericalAttribute>();

        for(int i = 0; i < attributes.length; i++) {
            NominalNumericalAttribute nna  = new NominalNumericalAttribute(attributes[i], types[i]);
            nnAttributes.add(nna);
        }

        NominalNumericalObject nno = new NominalNumericalObject();

        nno.addAttributes(nnAttributes);
        nno.addValue("name", "Marek");
        nno.addValue("age", ".46");
        nno.addValue("city", "Toronto");
        dataset.add(nno);

        nno = new NominalNumericalObject();
        nno.addAttributes(nnAttributes);
        nno.addValue("name", "Piotr");
        nno.addValue("age", ".46");
        nno.addValue("city", "Toronto");
        dataset.add(nno);

        nno = new NominalNumericalObject();
        nno.addAttributes(nnAttributes);
        nno.addValue("name", "Jan");
        nno.addValue("age", ".21");
        nno.addValue("city", "Warszawa");
        dataset.add(nno);


        NominalNumericalObject n0 = dataset.get(0);
        NominalNumericalObject n1 = dataset.get(1);
        NominalNumericalObject n2 = dataset.get(2);

        float d0 = n0.distance(n1);

        System.out.println(d0);

        System.out.println("Done.");
    }

    /**
     *
     * @param rs
     * @throws SQLException
     */
    public void setValues(int id, ResultSet rs) throws SQLException {
        setId(id);
        setDbId(rs.getInt("id"));

        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        for (NominalNumericalAttribute nna : nnAttributes) {
            String value = rs.getString(nna.getName());

            if (nna.getType() == true) {
                // attribute is numerical
                Float min = nna.getMin();
                Float max = nna.getMax();

                Float v = null;
                Float numericalValue = -1.0f;

                try {
                    v = Utils.convert(value);
                    numericalValue = (v - min) / (max - min);
                } catch (NumberFormatException nfe) {
                }


                this.addValueNumerical(nna.getName(), value, numericalValue);
            } else {
                this.addValue(nna.getName(), value);
            }
        }
    }

    /**
     *
     * @param dataset
     * @param eps
     * @return
     */
    public ArrayList<NominalNumericalObject> getNeighbours(ArrayList<NominalNumericalObject> dataset,
                                                           double eps) {
        if (neighbours == null) {
            neighbours = new ArrayList<NominalNumericalObject>();
            for (NominalNumericalObject nno : dataset) {
                if (nno.getId() != this.getId()) {
                    float dist = this.distance(nno);
                    if (dist < eps) {
                        neighbours.add(nno);
                    }
                }
            }
        }

        return neighbours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }
}
