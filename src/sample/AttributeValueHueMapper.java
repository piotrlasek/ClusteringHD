package sample;

/**
 * Created by Piotr Lasek on 10/15/2015.
 */
public class AttributeValueHueMapper {

    TripleHashMap<String, String, HueWeight> attributeValueColorMap;

    /**
     *
     */
    public AttributeValueHueMapper() {
        attributeValueColorMap = new TripleHashMap<>();
    }

    /**
     *
     * @param attributeName
     * @param attributeValue
     * @param hue
     * @param weight
     */
    public void add(String attributeName, String attributeValue, Float hue, Float weight) {
        HueWeight hw = new HueWeight(hue, weight);
        HueWeight hueWeight = attributeValueColorMap.get(attributeName, attributeValue);

        if (hueWeight == null) {
            hueWeight = new HueWeight(hue, weight);
        }

        attributeValueColorMap.put(attributeName, attributeValue, hueWeight);
    }

}
