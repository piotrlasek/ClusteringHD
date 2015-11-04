package sample;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Piotr on 31.10.2015.
 */
public class NGram {

    private String[] a = {"a", "e", "i", "o", "u", "y"};

    public Set<String> get(String text, int width) {
        TreeSet<String> words = new TreeSet<>();

        for(int i = 0; i < text.length()-(width + 1); i++) {
            String word = text.substring(i, i+width);
            if (!words.contains(word)) {
                word = word.trim();
                word = word.replace(" ", "");
                word = word.replace(".", "");
                word = word.replace(",", "");
                word = word.toLowerCase();

                if (word.length() == width) {
                    words.add(word);
                }
            }
        }

        return words;
    }

    public static void main(String[] args) {

        String text = "Wiosną przyleciały jaskółki. Ulepiły gniazdo pod strzechą. Naskubały z własnych piersi puszku na wyściółkę. Teraz kołują w górze. Łowią muszki. Wróciły, a tu w ich domku panoszy się wróbel.";

        NGram ng = new NGram();

        for(int i = 2; i < 8; i++) {
            Set<String> subWords = ng.get(text, i);
            for(String word : subWords) {
                System.out.print(word + "\t");
            }
            System.out.println();
        }

    }
}