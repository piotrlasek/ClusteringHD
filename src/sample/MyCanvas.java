package sample;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

/**
 * Created by Piotr Lasek on 10/16/2015.
 */
class MyCanvas extends JComponent implements MouseMotionListener, MouseInputListener {

    private final ArrayList<NominalNumericalAttribute> attributes;
    private HashMap<String, String> attributeDescription;
    ArrayList<TripleHashMap<String, String, HueWeight>> data;
    private TripleHashMap<String, String, Float> attributeValueHue;
    TripleHashMap<Integer, Integer, String> descriptions;
    Dimension preferredSize;
    float zoom = 1.0f;
    float attributeWidth = 2;
    ClustersFilter clustersFilter = null;
    ArrayList<String> filteredAttributes = null;
    ClustersFrame clustersFrame;
    private boolean removeSame;

    BufferedImage bufferedImage;


    /**
     *  @param data
     * @param attributes
     * @param attributeValueHue
     */
    public MyCanvas(ArrayList<TripleHashMap<String, String, HueWeight>> data,
                    ArrayList<NominalNumericalAttribute> attributes, TripleHashMap<String, String, Float> attributeValueHue) {
        this.data = data;
        this.attributeValueHue = attributeValueHue;
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.attributes = attributes;
        attributeDescription = new HashMap<>();
        for(NominalNumericalAttribute nna : attributes) {
            attributeDescription.put(nna.getName(), nna.getDescription());
        }
        clustersFilter = new ClustersFilter(data);
        filteredAttributes = clustersFilter.filterSameValues();
        preferredSize = new Dimension(100, 100);

        createImage();
    }

    /**
     *
     * @param zoom
     */
    public void setZoom(float zoom) {

       this.zoom = zoom;

        createImage();

        /*

        try {
            Graphics2D graphics2D = drawableImage.createGraphics();
            AffineTransform xform = AffineTransform.getScaleInstance(zoom, zoom);
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics2D.drawImage(bufferedImage, xform, null);
            //graphics2D.drawImage(bufferedImage, 0, 0, null);
            graphics2D.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }

*/
    }

    public void createImage() {

        float clusterOffset = 0f;
        float xOffset = 20f;
        float yOffset = 20f;
        float width = attributeWidth;
        float height = 150f;
        float valueIndex = 0f;
        float attributeIndex = 0f;
        descriptions = new TripleHashMap<>();

        List<String> attributesSorted = attributeValueHue.keySetSorted();

        float wi = ((width + 3) * attributesSorted.size());
        float hi = ((2*height + 5) * data.size());

        bufferedImage = new BufferedImage((int) wi, (int) hi, BufferedImage.TYPE_INT_ARGB);

        Graphics g = bufferedImage.getGraphics();

        Color white = Color.white;
        g.setColor(white);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        System.out.println("Clusters: " + data.size());

        for (TripleHashMap<String, String, HueWeight> sshw : data) {

            Set<String> attributes = sshw.keySet();

            attributeIndex = 0;

            //          Set<String> allAttributes = sshw.keySetSorted();

            //for (String attribute : allAttributes) {
            for (int xxx = 0; xxx < attributesSorted.size(); xxx++) {
                String attribute = attributesSorted.get(attributesSorted.size() - xxx - 1);

               /*if (filteredAttributes.contains(attribute)) {
                   System.out.println("Pomijam: " + attribute);
                   continue;
               }*/

                valueIndex = 0;
                Set<String> values = sshw.subKeySet(attribute);

                ArrayList<HueWeight> hueWeights = new ArrayList();

                for(String value : values) {
                    HueWeight hw = sshw.get(attribute, value);
                    hw.setDescription(value);

                    if (hw == null) {
                        System.out.println("hw = null");
                    } else if (hw.getHue() == null) {
                        System.out.println("hw.getHue = null");
                    }

                    hueWeights.add(hw);
                }

                Collections.sort(hueWeights);

                // for (String value : values) {
                for(HueWeight hw : hueWeights) {

                    // HueWeight hw = sshw.get(attribute, value);
                    float saturation = 1;
                    float brighteness = 1;
                    if (hw == null) {
                        System.out.println("null = hw");
                    } else if (hw.getHue() == null) {
                        System.out.println("hue = null");
                    }
                    float hue = hw.getHue() + 0.5f;
                    hue = hue % 1;

                    String value = hw.getDescription();

                    if (value.equals("N/A")) {
                        saturation = 0.0f;
                        brighteness = 0.9f;
                        hue = 0.0f;
                    } else if (value.equals("N/S")) {
                        saturation = 0.0f;
                        brighteness = 0.8f;
                        hue = 0.0f;
                    } else if (value.equals("NO")) {
                        saturation = 0.5f;
                        brighteness = 0.9f;
                        hue = 1.0f;
                    } else if (value.equals("YES")) {
                        saturation = 0.5f;
                        brighteness = 0.9f;
                        hue = 0.2f;
                    }


                    Color c = Color.getHSBColor(hue, saturation, brighteness);

                    g.setColor(c);
                    int x = (int) (zoom * (attributeIndex + xOffset));
                    int y = (int) (zoom * (yOffset + (height * valueIndex) + clusterOffset));
                    int w = (int) ((float) Math.ceil(zoom * (width)));
                    int h = (int) ((zoom * (yOffset + height * hw.getWeight())));

                    g.fillRect(x, y, w, h);

                    for (int ii = x; ii < x+w; ii++ ) {
                        for (int jj = y; jj < y+h; jj++) {
                            String filteredAtt = "";
                            if (filteredAttributes.contains(attribute)) filteredAtt = " --- FILTERED ";
                            descriptions.put(ii, jj, hw.getDescription() + "\t" + attributeDescription.get(attribute) + "\t" + attribute + filteredAtt);
                        }
                    }

                    valueIndex += hw.getWeight();
                }
                attributeIndex += width;
            }
            clusterOffset += height + 2*yOffset ;
        }

        preferredSize.setSize(zoom*(attributeIndex+3*width), zoom*(clusterOffset+2*yOffset));
        //preferredSize.setSize(4*(attributeIndex+3*width), 10*(clusterOffset+2*yOffset));
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, null);
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
            desc = desc.replace("\t", "   ");
            clustersFrame.updateAttributeInfo(desc);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
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


    public void removeSame(boolean b) {
        removeSame = b;
    }
}
