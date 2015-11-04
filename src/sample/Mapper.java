package sample;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Piotr on 10/6/2015.
 */
public class Mapper {

    // Will be mapped to null values.
    public static ArrayList<String> stopWords = new ArrayList<String>(Arrays.asList("don't know", "n/s",
            "n/a", "refusal", "no drinks last w", "no phy. activity", "not required"));

    public static String stopWordsAtt = "'DON''T KNOW', 'N/S', " +
            " 'N/A', 'REFUSAL', 'NO DRINKS LAST W', 'NO PHY. ACTIVITY'";

    /**
     * Creates a file containing sql code for creating a "mapped" table tableName
     *
     * @param database
     * @param tableName
     * @return
     * @throws Exception
     */
    public static StringBuilder mapTable(Database database, String tableName, String attributesList)
            throws Exception {
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> attributesFormats = Utils.getAttributeFormat(database, attributesList);
        HashMap<String, Mapping> formatMappings = Mapper.map(database.getConnection());
        Set<String> attributes = attributesFormats.keySet();
        boolean first = true;

        sb.append("SELECT\n");
        sb.append("\tID,\n");

        for(String attribute : attributes) {
            System.out.println("a: " + attribute);
            String format = attributesFormats.get(attribute);
            Mapping oldNewValues = formatMappings.get(format);
            if (first) {sb.append("\tCASE\n"); first = false;}
            else sb.append(",\n\tCASE\n");
            Set<String> oldValues = oldNewValues.getOldValues();
            for(String oldValue : oldValues) {
                //sb.append(oldValue + " -> " + oldNewValues.getNewValue(oldValue));
                String newValue = oldNewValues.getNewValue(oldValue);
                //System.out.println(oldValue + " -> " + oldNewValues.getNewValue(oldValue));
                oldValue = oldValue.replace("'", "''");
                sb.append("\t\tWHEN lower(" + attribute + ") LIKE '" + oldValue + "' THEN " + newValue + "\n");
            }

            for(String nullWord : Mapper.stopWords) {
                nullWord = nullWord.replace("'", "''");
                sb.append("\t\tWHEN lower(" + attribute + ") LIKE '" + nullWord + "' THEN null \n");
            }

            sb.append("\tEND AS " + attribute);
        }

        sb.append("\nINTO " + tableName + "_map" + "\nFROM " + tableName + " LIMIT 3");
        System.out.println(sb.toString());

        FileUtils.writeStringToFile(new File("query-gen-table-" + tableName + "_map" + ".sql"), sb.toString());

        return sb;
    }

    /**
     *
     * @param connection
     * @throws Exception
     */
    public static HashMap<String, Mapping> map(Connection connection) throws Exception {

        HashMap<String, Mapping> formatMapping = new HashMap<String, Mapping>();
        String homeDirectory = System.getProperty("user.home");
        String filePath = homeDirectory + "/Dropbox/PROJECTS/DIABETIC/attributes-2.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                int tmpX = 0;
                boolean intDef = false;
                boolean castInt = false;
                boolean castFloat = false;
                String format = br.readLine();

                if (format == null)
                    break;

                String[] f = format.split("\t");

                if (f.length == 2 && f[1].equals("INT")) {
                    intDef = false; castInt = true; castFloat = false;
                } else if (f.length == 2 && f[1].equals("INT_DEF")) {
                    intDef = true;
                    castInt = false; castFloat = false;
                } else if (f.length == 2 && f[1].equals("FLOAT")) {
                    intDef = false; castInt = false; castFloat = true;
                } else if (f.length == 2) {
                    System.out.println("ERROR: " + format);
                    break;
                }

                br.readLine(); // ---

                //HashMap<String, Integer> mapping = new HashMap<String, Integer>();
                HashMap<String, String> mapping = new HashMap<String, String>();


                int tmpInt = 0;
                for(String attribute; (attribute = br.readLine()) != null && !attribute.isEmpty() ; ) {
                    // System.out.println(attribute);
                    String[] at = attribute.split("\t");
                    // an attribute is not a "stop-word"
                    if (!stopWords.contains(at[1])) {
                        if (intDef && !castInt && !castFloat && at.length == 3)
                            mapping.put(at[1], at[2]);
                        else if (castInt && !castFloat && !intDef && at.length == 2) {
                            String n = at[1];
                            int index = n.indexOf(" ");
                            if (index != -1) {
                                n = n.substring(0, index);
                            }
                            // System.out.println("----" + n);
                            n = n.replaceAll("[^\\d.]", "");
                            //n = n.replace(" ", "");
                            Integer i = new Integer(n);
                            mapping.put(at[1], i.toString());
                        } else if (castInt == false && intDef == false && at.length == 2 &&
                                castFloat == false) {
                            // Nominal attributes...

                            // mapping.put(at[1], tmpInt++);
                            mapping.put(at[1], at[1]); // no mapping for nominal attributes

                        } else if (castFloat == true && intDef == false && castInt == false &&
                                at.length == 2) {
                            String n = at[1];
                            int index = n.indexOf(" ");
                            if (index != -1) {
                                n = n.substring(0, index - 1);
                            }
                            Integer i = (int) (Float.parseFloat(n) * 10);
                            mapping.put(at[1], i.toString());
                        } else {
                            System.out.println("BREAK > " + attribute);
                            break;
                        }
                    }
                }

                // map to 0 - 10;
                Set<String> keys = mapping.keySet();

                Integer min = null;
                Integer max = null;
                for(String key:keys) {
                    try {
                        String s = mapping.get(key);
                        Integer v = null;
                        if (s != null) {
                            v = new Integer(mapping.get(key));
                        }

                        if (min == null) min = v;
                        if (max == null) max = v;

                        if (v < min) min = v;
                        if (v > max) max = v;
                    } catch (Exception e) {

                    }
                }

/*                for(String key:keys) {

                    Integer v = mapping.get(key);
                    v = (10 * v) / (max - min);
                    mapping.put(key, v);
                }
*/
                //System.out.println("> " + format);
                Mapping m = new Mapping();
                m.setMapping(mapping);
                String format2 = format.split("\t")[0];
                int ind = format2.indexOf(" ");
                if (ind > -1) {
                    format2 = format2.substring(ind+1, format2.length());
                    //System.out.println("-" + format2);
                    formatMapping.put(format2, m);
                } else {
                    break;
                }
            }
        }

        return formatMapping;
    }



}
