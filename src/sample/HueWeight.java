package sample;

import java.io.Serializable;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
public class HueWeight implements Serializable {
    private Float hue;
    private Float weight;

    /**
     *
     * @param hue
     * @param weight
     */
    public HueWeight(Float hue, Float weight) {
        this.setHue(hue);
        this.setWeight(weight);
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getHue() {
        return hue;
    }

    public void setHue(Float hue) {
        this.hue = hue;
    }
}
