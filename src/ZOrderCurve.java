/**
 * Created by Piotr on 9/24/2015.
 */
public class ZOrderCurve {

    long calcZOrder(int xPos, int yPos)
    {
        long MASKS[] = {0x55555555, 0x33333333, 0x0F0F0F0F, 0x00FF00FF};
        long SHIFTS[] = {1, 2, 4, 8};

        long x = xPos;  // Interleave lower 16 bits of x and y, so the bits of x
        long y = yPos;  // are in the even positions and bits from y in the odd;

        x = (x | (x << SHIFTS[3])) & MASKS[3];
        x = (x | (x << SHIFTS[2])) & MASKS[2];
        x = (x | (x << SHIFTS[1])) & MASKS[1];
        x = (x | (x << SHIFTS[0])) & MASKS[0];

        y = (y | (y << SHIFTS[3])) & MASKS[3];
        y = (y | (y << SHIFTS[2])) & MASKS[2];
        y = (y | (y << SHIFTS[1])) & MASKS[1];
        y = (y | (y << SHIFTS[0])) & MASKS[0];

        long result = x | (y << 1);
        return result;
    }


    public static void main(String[] args) {
        ZOrderCurve zo = new ZOrderCurve();


        int X = 2;
        int Y = 2;

        for(int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                long v = zo.calcZOrder(x, y);
                // System.out.print(v + "\t");

                System.out.println("\\node at (" + (x) + "," + (Y - y) + ") {" + v + "};");

            }
            System.out.println();
        }

        for (int i = 0; i <= X; i++) {
            System.out.println("\\draw (" + (-0.5 + i) + ", 0.5 ) -- (" + (-0.5 + i) + ", " + (Y + 0.5) + " );");
            System.out.println("\\draw (-0.5, " + (Y + 0.5 - i) +") -- ( " + (X - 0.5) + " , " + ( Y + 0.5 - i) + ");");
        }
    }
}
