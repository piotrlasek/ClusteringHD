package sample;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalAttribute {

    String name;
    boolean type;

    /**
     *
     * @param name
     * @param type
     */
    NominalNumericalAttribute(String name, boolean type) {
        this.name = name;
        this.type = type;
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
            if (!name.equals(nna.name)) {
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

}
