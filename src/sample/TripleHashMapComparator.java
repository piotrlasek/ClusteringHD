package sample;

import java.util.Comparator;

/**
 * Created by Piotr on 22.10.2015.
 */
public class TripleHashMapComparator {
    static final Comparator<TripleHashMap<String, String, HueWeight>> COUNT_ORDER =
        new Comparator<TripleHashMap<String, String, HueWeight>>() {
        public int compare(TripleHashMap<String, String, HueWeight> thm1,
                           TripleHashMap<String, String, HueWeight> thm2) {

            return 0;
        }
    };
}
