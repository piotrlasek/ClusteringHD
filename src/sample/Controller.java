package sample;


import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Controller {

    @FXML
    VBox vBox;

    @FXML
    BorderPane borderPane;

    /**
     *
     */
    public void initialize() {
        try {
            String filePath = Main.filePrefix + "-averages.txt";

            ArrayList<String> header = getHeader(filePath);
            ArrayList<ArrayList<Double>> valuesClusters = readClusterAverages(filePath);

            // for each cluster do:

            vBox = new VBox(10);
            borderPane.setCenter(vBox);
            vBox.setSpacing(90);
            //borderPane.setStyle("-fx-background-color: black");

            int w = 3;
            int h = 4;

            int clusterId = 0;

            for(ArrayList<Double> values : valuesClusters) {
                Pane p = new Pane();

                for (int i = 1; i < values.size(); i++) {
                    Double attribute = (Double) values.get(i);

                    //Color color = Color.hsb(i, 1, 1);

                    double yValue = attribute;

                    yValue = Math.log(yValue);
                    yValue = yValue * 10;
                    yValue = -yValue;

                    Color color = Color.hsb(150 + i * 2, 1, 1);
                    Line l = new Line();
                    l.setStroke(color);
                    l.setStartX(50 + 5 * i);
                    l.setEndX(50 + 5 * i);
                    l.setStartY(100);
                    l.setStrokeWidth(4);
                    l.setEndY(100  +yValue);

                    p.getChildren().add(l);
                }

                int c = clusterId % w;
                int r = clusterId / w;

                System.out.println("" + c + " " + r);
               // gridPane.add(p, c, r);
                vBox.getChildren().add(clusterId, p);
                clusterId++;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public ArrayList<String> getHeader(String fileName) throws IOException {
        ArrayList<String> header = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String h = br.readLine();
        br.close();
        String[] headerArray = h.split(";");
        for(String att : headerArray) {
            header.add(att);
        }
        return header;
    }

    /**
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public ArrayList<ArrayList<Double>> readClusterAverages(String fileName) throws IOException {
        ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();
        FileInputStream fstream = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        String header = br.readLine();
        while ((strLine = br.readLine()) != null) {
            System.out.println(strLine);
            ArrayList<Double> rwo = new ArrayList<Double>();
            String[] columns = strLine.split(";");
            for (String column : columns) {
                rwo.add(new Double(column));
            }

            result.add(rwo);
        }

        br.close();

        return result;

    }


}
