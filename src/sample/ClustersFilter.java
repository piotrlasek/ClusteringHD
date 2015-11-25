package sample;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 */
public class ClustersFilter {

    ArrayList<TripleHashMap<String, String, HueWeight>> data;

    public ClustersFilter(ArrayList<TripleHashMap<String, String, HueWeight>> data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> filterSameValues() {
        ArrayList<String> filteredAttributes = new ArrayList<>();
        TripleHashMap<String, String, Float> thmTmp = new TripleHashMap<>();
        Set<String> attributes = data.get(0).keySet();

        // iterate through all allAttributes
        for(String attribute : attributes) {
            boolean attributeHasSameValues = true;
            for(TripleHashMap<String, String, HueWeight> thmCluster : data) {
                Set<String> valuesKeys = thmCluster.subKeySet(attribute);

                if (!thmTmp.keySet().contains(attribute)) {
                    // initializing thmTmp with the currently processed attribute
                    for (String vk : valuesKeys) {
                        HueWeight hw = thmCluster.get(attribute, vk);
                        thmTmp.put(attribute, vk, hw.getWeight());
                    }
                } else {
                    // Checking if "next" cluster has same values on the same attribute
                    for(String vk : valuesKeys) {
                        HueWeight hw = thmCluster.get(attribute, vk);
                        Float v1 = hw.getWeight();
                        Float v2 = thmTmp.get(attribute, vk);

                        if (v1 == null || !v1.equals(v2)) {
                            attributeHasSameValues = false;
                            break;
                        }
                    }
                }
            }

            if (attributeHasSameValues) {
               filteredAttributes.add(attribute);
            }
        }

        return filteredAttributes;
    }

}