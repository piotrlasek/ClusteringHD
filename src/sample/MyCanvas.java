package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
class MyCanvas extends JComponent implements MouseMotionListener {

    ArrayList<TripleHashMap<String, String, HueWeight>> data;

    float zoom = 1.0f;
    int attributeWidth = 2;

    /**
     *
     * @param data
     */
    public MyCanvas(ArrayList<TripleHashMap<String, String, HueWeight>> data) {
        this.data = data;
        this.addMouseMotionListener(this);
    }


    /**
     *
     * @param zoom
     */
    public void setZoom(float zoom) {
       this.zoom = zoom;
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g) {

        int clusterOffset = 0;

        for (TripleHashMap<String, String, HueWeight> sshw : data) {

            Set<String> attributes = sshw.keySet();

            int attributeIndex = 0;

            int xOffset = 20;
            int yOffset = 20;
            int width = attributeWidth;

            for (String attribute : attributes) {
                int valueIndex = 0;

                Set<String> values = sshw.subKeySet(attribute);

                for (String value : values) {

                    HueWeight hw = sshw.get(attribute, value);
                    Color c = Color.getHSBColor(hw.getHue(), 1, 1);

                    g.setColor(c);
                    float x = zoom * (attributeIndex + xOffset);
                    float y = zoom * (valueIndex + yOffset + clusterOffset);
                    float w = (float) Math.ceil(zoom * (width));
                    float h = zoom * (yOffset + (int) (100 * hw.getWeight()));

                   /* Rectangle r = new Rectangle();
                    r.setSize((int) w, (int) h);
                    r.setLocation((int) x, (int) y);*/
                    g.fillRect((int) x, (int) y, (int) w, (int) h);
                    valueIndex += (int) 100 * hw.getWeight();
                }
                attributeIndex += width;
            }
            clusterOffset += 150;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(5000, 5000);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + ", " + e.getY());
    }
}
