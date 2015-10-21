package sample;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
class MyCanvas extends JComponent implements MouseMotionListener, MouseInputListener {

    private final ArrayList<NominalNumericalAttribute> attributes;
    private HashMap<String, String> attributeDescription;
    ArrayList<TripleHashMap<String, String, HueWeight>> data;
    TripleHashMap<Integer, Integer, String> descriptions;
    Dimension preferredSize;
    float zoom = 1.0f;
    float attributeWidth = 2;

    ClustersFrame clustersFrame;

    /**
     *
     * @param data
     * @param attributes
     */
    public MyCanvas(ArrayList<TripleHashMap<String, String, HueWeight>> data,
                    ArrayList<NominalNumericalAttribute> attributes) {
        this.data = data;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.attributes = attributes;
        attributeDescription = new HashMap<>();
        for(NominalNumericalAttribute nna : attributes) {
            attributeDescription.put(nna.getName(), nna.getDescription());
        }
        preferredSize = new Dimension(100, 100);
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
        float clusterOffset = 0f;
        float xOffset = 20f;
        float yOffset = 20f;
        float width = attributeWidth;
        float height = 150f;
        float valueIndex = 0f;
        float attributeIndex = 0f;
        descriptions = new TripleHashMap<>();

        for (TripleHashMap<String, String, HueWeight> sshw : data) {

            Set<String> attributes = sshw.keySet();
            attributeIndex = 0;

            for (String attribute : attributes) {
                valueIndex = 0;

                Set<String> values = sshw.subKeySet(attribute);

                ArrayList<HueWeight> hueWeights = new ArrayList();

                for(String value : values) {
                    HueWeight hw = sshw.get(attribute, value);
                    hw.setDescription(value);
                    hueWeights.add(hw);
                }

                Collections.sort(hueWeights);

                // for (String value : values) {
                for(HueWeight hw : hueWeights) {

                    // HueWeight hw = sshw.get(attribute, value);

                    float saturation = 1;
                    float brighteness = 1;

                    String value = hw.getDescription();

                    if (value.equals("N/A")) {
                        saturation = 0.2f;
                        brighteness = 1f;
                    }
                    Color c = Color.getHSBColor(hw.getHue(), saturation, brighteness);

                    g.setColor(c);
                    int x = (int) (zoom * (attributeIndex + xOffset));
                    int y = (int) (zoom * (yOffset + (height * valueIndex) + clusterOffset));
                    int w = (int) ((float) Math.ceil(zoom * (width)));
                    int h = (int) ((zoom * (yOffset + height * hw.getWeight())));

                   /* Rectangle r = new Rectangle();
                    r.setSize((int) w, (int) h);
                    r.setLocation((int) x, (int) y);*/

                    g.fillRect(x, y, w, h);

                    for (int ii = x; ii < x+w; ii++ ) {
                        for (int jj = y; jj < y+h; jj++) {
                            descriptions.put(ii, jj, hw.getDescription() + "\t" + attributeDescription.get(attribute) + "\t" + attribute);
                        }
                    }

                    valueIndex += hw.getWeight();
                }
                attributeIndex += width;
            }
            clusterOffset += height + 2*yOffset ;
        }

        System.out.println("attributeIndex: " + attributeIndex);
        preferredSize.setSize(zoom*(attributeIndex+2*width), zoom*(clusterOffset+yOffset));
    }

    @Override
    public Dimension getPreferredSize() {
        return preferredSize;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        String desc = descriptions.get(e.getX(), e.getY());
        if (desc != null) {
            clustersFrame.updateAttributeInfo(desc);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("mouseClicked");
        String desc = descriptions.get(e.getX(), e.getY());
        if (desc != null) {
            clustersFrame.saveAttributeInfo(desc);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setAttributeWidth(float attributeWidth) {
        this.attributeWidth = attributeWidth;
    }

    public void setClusterFrame(ClustersFrame clustersFrame) {
        this.clustersFrame = clustersFrame;
    }


}
