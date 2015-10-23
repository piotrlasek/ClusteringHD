package sample;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
public class TripleHashMap<K1, K2, V> implements Serializable {

    HashMap<K1, HashMap<K2, V>> hashMap;

    /**
     *
     */
    public TripleHashMap() {
        hashMap = new HashMap<K1, HashMap<K2, V>>();
    }

    /**
     *
     * @param key1
     * @param key2
     * @param value
     */
    public void put(K1 key1, K2 key2, V value) {
        HashMap<K2, V> internalHashMap = hashMap.get(key1);

        if (internalHashMap == null) {
            internalHashMap = new HashMap<K2, V>();
        }

        internalHashMap.put(key2, value);
        hashMap.put(key1, internalHashMap);
    }

    /**
     *
     * @param key1
     * @param key2
     * @return
     */
    public V get(K1 key1, K2 key2) {
        V result = null;
        HashMap<K2, V> internalHashMap = hashMap.get(key1);
        if (internalHashMap != null) {
            result = internalHashMap.get(key2);
        }
        return result;
    }

    /**
     *
     */
    public void save(String fileName) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static TripleHashMap<String, String, Float> read(String fileName) throws Exception {
        TripleHashMap<String, String, Float> e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (TripleHashMap<String, String, Float>) in.readObject();
        in.close();
        fileIn.close();
        return e;
    }

        /**
     *
     * @return
     * @throws Exception
     */
    public static TripleHashMap<String, String, HueWeight> readHW(String fileName) throws Exception {
        TripleHashMap<String, String, HueWeight> e = null;
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        e = (TripleHashMap<String, String, HueWeight>) in.readObject();
        in.close();
        fileIn.close();
        return e;
    }

    /**
     *
     * @return
     */
    public int size() {
        if (hashMap != null) {
            return hashMap.size();
        } else {
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public Set<K1> keySet() {
        if (hashMap != null) {
            return hashMap.keySet();
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public List<K1> keySetSorted() {
        List<K1> keys = new ArrayList<K1>();

        Iterator<Map.Entry<K1, Integer>> sortedKeySet = sortByCount(this);

        while(sortedKeySet.hasNext()) {
            keys.add(sortedKeySet.next().getKey());
        }

        return keys;
    }

    /**
     *
     * @param key
     * @return
     */
    public Set<K2> subKeySet(K1 key) {
        Set<K2> result = null;

        if (hashMap != null) {
            HashMap<K2, V> subHashMap = hashMap.get(key);
            if (subHashMap != null) {
                result = subHashMap.keySet();
            }
        }

        return result;
    }

     /**
     *
     * @param ssf
     * @return
     */
    private Iterator<Map.Entry<K1, Integer>> sortByCount(TripleHashMap<K1, K2, V> ssf) {

        HashMap<K1, Integer> attributeValuesCount = new HashMap();

        Set<K1> attributes = ssf.keySet();

            for(K1 attribute : attributes) {
                Set<K2> values = ssf.subKeySet(attribute);
                attributeValuesCount.put(attribute, values.size());
            }

            Stream<HashMap.Entry<K1, Integer>> sortedAttVal =
                attributeValuesCount.entrySet().stream().sorted(HashMap.Entry.comparingByValue());

        Iterator<HashMap.Entry<K1, Integer>> sorterAttValIt = sortedAttVal.iterator();

        return sorterAttValIt;
    }
}
