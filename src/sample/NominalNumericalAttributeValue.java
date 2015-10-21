package sample;

/**
 * Created by Piotr Lasek on 9/16/2015.
 */
public class NominalNumericalAttributeValue {

    Float numericalValue;
    String nominalValue;

    boolean numerical; // 1 numerical, 0 nominal

    public NominalNumericalAttributeValue(String value, boolean numerical) {
        this.nominalValue = value;
        this.numerical = numerical;

        if (numerical) {
            Float aFloat = new Float(value);
            numericalValue = aFloat;
        }
    }

    /**
     * 
     * @param nominalValue
     * @param numericalValue
     * @param numerical
     */
    public NominalNumericalAttributeValue(String nominalValue, Float numericalValue, boolean numerical) {
        this.numericalValue = numericalValue;
        this.nominalValue = nominalValue;
        this.numerical = numerical;
    }

    public float getFloat() {
        return numericalValue;
    }

    public String getString() {
        return nominalValue;
    }

    public boolean isNumerical() {
        return numerical;
    }


}
