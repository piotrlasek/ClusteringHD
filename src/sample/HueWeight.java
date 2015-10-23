package sample;

import java.io.Serializable;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
public class HueWeight implements Serializable, Comparable {
    private Float hue;
    private Float weight;
    private String description;

    /**
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

    @Override
    public boolean equals(Object o) {
        HueWeight hw = (HueWeight) o;
        boolean result = false;
        if (hw.getWeight() == hw.getWeight()) {
            result = true;
        }
        return result;
    }

    @Override
    public int compareTo(Object o) {
        HueWeight hw = (HueWeight) o;

        /*
        int result = 0;

        if (this.getWeight() == hw.getWeight()) {
            result = 0;
        } else  if (this.getWeight() > hw.getWeight()) {
            result = 1;
        } else {
            result = -1;
        }
        */
        float diff = this.getWeight() - hw.getWeight();

        return (int) (10 * diff);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
