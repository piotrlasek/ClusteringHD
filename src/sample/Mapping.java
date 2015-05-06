package sample;

import java.util.HashMap;
import java.util.Set;

/**
     * An auxiliary class for storing mappings.
     */
    public class Mapping {

        HashMap<String, Integer> mapping; // key -> attribute value, value -> numerical (integer) value

        /**
         *
         */
        public Mapping() {
            mapping = new HashMap<String, Integer>();
        }
        
        public void setMapping(HashMap<String, Integer> mapping) {
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
        public Integer getNewValue(String oldValue) throws Exception {
            Integer result = null;
            if (mapping.containsKey(oldValue)) {
                result = mapping.get(oldValue);
            } else {
                throw new Exception("Key " + oldValue + " does not exist.");
            }
            return result;
        }

        public void addNewMapping(String oldValue, Integer newValue) {
            mapping.put(oldValue, newValue);
        }
    }