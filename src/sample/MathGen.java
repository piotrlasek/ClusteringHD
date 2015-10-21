package sample;

/**
 * Created by Piotr on 10/19/2015.
 */
public class MathGen {

    public void start() {

        for (int k = 0; k < 12; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 5; j++) {

                    int a = (int) (Math.random() * 20);
                    int b = (int) (Math.random() * 20);

                    System.out.print(w(3, "" + a) + " +" + w(3, "" + b) + " = ____");
                    System.out.print("   ");
                }
                System.out.println();
            }
           // System.out.println();
        }

    }

    public String w(int size, String si) {
        String s = si;
       while (s.length() < size) {
           s = " " + s;
       }
        return s;
    }


    public static void main(String[] args) {
        MathGen mg = new MathGen();
        mg.start();
    }
}
