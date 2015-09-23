package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalObject {

    ArrayList<NominalNumericalAttribute> nnAttributes;
    ArrayList<NominalNumericalAttributeValue> nnValues;

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
            if (nnav1.isNumerical()) {
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
        //NominalNumericalAttribute nna = new NominalNumericalAttribute(attributeName, type);
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


}
