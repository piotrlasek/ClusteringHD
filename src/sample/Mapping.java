package sample;

import java.util.HashMap;
import java.util.Set;

/**
 * An auxiliary class for storing mappings.
 */
public class Mapping {

    HashMap<String, String> mapping; // key -> attribute value, value -> numerical (integer) value

    /**
     *
     */
    public Mapping() {
        mapping = new HashMap<String, String>();
    }

    public void setMapping(HashMap<String, String> mapping) {
        this.mapping = mapping;
    }

    /**
     *
     * @return
     */
    public Set<String> getOldValues() {
        return mapping.keySet();
    }

    /**
     *
     * @param oldValue
     * @return
     * @throws Exception
     */
    public String getNewValue(String oldValue) throws Exception {
        String result = null;
        if (mapping.containsKey(oldValue)) {
            result = mapping.get(oldValue);
        } else {
            throw new Exception("Key " + oldValue + " does not exist.");
        }
        return result;
    }

    public void addNewMapping(String oldValue, String newValue) {
        mapping.put(oldValue, newValue);
    }
}