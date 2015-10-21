package sample;

import java.util.*;

/**
 * Created by Piotr on 10/19/2015.
 */
public class KMeans2 {

   public static class Point {

       private double x;
       private double y;

       public Point(double i, double i1) {
           setX(i);
           setY(i1);
       }

       double distance(Point p ) {
           return Math.sqrt((p.x - x) * ( p.x - x) + (p.getY() - getY()) * (p.getY() - getY()) );
       }

       public double getY() {
           return y;
       }

       public void setY(double y) {
           this.y = y;
       }

       public void setX(double x) {
           this.x = x;
       }

       public double getX() {
           return x;
       }

       public void setLocation(double x, double y) {
           setX(x);
           setY(y);
       }
   }

    public ArrayList<Point> centroids;
    public ArrayList<Point> points;

    public KMeans2(Point[] centroids, Point[] points) {
        this.centroids = new ArrayList<Point>(Arrays.asList(centroids));
        this.points = new ArrayList<Point>(Arrays.asList(points));
        System.out.println("Centroids" + centroids);
    }

    public Point nearestCentroid(Point p) {

        double minDist = Double.MAX_VALUE;
        Point lastNearestCentroid = null;

        for(Point c : centroids) {
            double dist = p.distance(c);
            if (dist < minDist) {
                minDist = dist;
                lastNearestCentroid = c;
            }
        }

        return lastNearestCentroid;
    }



    public void run(int iteration) {


        System.out.println();
        System.out.println("Iteration: " + iteration);

        HashMap<Point, ArrayList<Point>> centroidPoints = new HashMap();

        for(Point p : points) {
            Point c = nearestCentroid(p);

            ArrayList<Point> cp = centroidPoints.get(c);
            if (cp == null) cp = new ArrayList<Point>();
            cp.add(p);
            centroidPoints.put(c, cp);
        }

        for (Point c : centroids) {
            ArrayList<Point> clus = centroidPoints.get(c);
            System.out.print("(" + c.getX() + ", " + c.getY() + "): ");
            for(Point p : clus) {
                System.out.print("(" + p.getX() + ", " + p.getY() + ") ");
            }

            System.out.println();
        }

        recomputeCentroids(centroidPoints, iteration);

    }


    public void recomputeCentroids(HashMap<Point, ArrayList<Point>> centroidPoints, int iteration) {

        for(Point c : centroids) {
            Point nc = new Point(0,0);

            ArrayList<Point> pc = centroidPoints.get(c);
            double x = 0;
            double y = 0;
            if (pc != null) {
                for (Point p : pc) {
                    x += p.getX();
                    y += p.getY();
                }

                double newX = x / pc.size();
                double newY = y / pc.size();

                c.setLocation(newX, newY);
            }
        }

//        System.out.println("Centroids" + centroids);

    }


    public static void main(String[] args) {

       Point[] centroids = {new Point(25, 125), new Point(44,105), new Point(29,97), new Point(35,63),
           new Point(55,63), new Point(42,57), new Point(23,40), new Point(64,37), new Point(33,22),
           new Point(55,20)};

        Point[] points = {new Point(28,145), new Point(65,140), new Point(50,130), new Point(38,115),
           new Point(55,118), new Point(50,90), new Point(63,88), new Point(43,83), new Point(50,60), new Point(50,30),
           new Point(25, 125), new Point(44,105), new Point(29,97), new Point(35,63),
           new Point(55,63), new Point(42,57), new Point(23,40), new Point(64,37), new Point(33,22),
           new Point(55,20)};

        KMeans2 kmeans = new KMeans2(centroids, points);

        kmeans.run(1);

        kmeans.run(2);

        // kmeans.run(3);



    }

}
