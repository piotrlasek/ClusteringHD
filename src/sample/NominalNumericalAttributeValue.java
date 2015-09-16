package sample;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalAttributeValue {

    float numericalValue;
    String nominalValue;

    boolean type; // 1 numerical, 0 nominal

    public float getFloat() {
        return numericalValue;
    }

    public String getString() {
        return nominalValue;
    }

    public boolean isNumerical() {
        return type;
    }

}
