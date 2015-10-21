package sample;

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ClustersFrame extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JScrollPane scrollPane;
    private JSlider slider1;
    private JButton button1;
    private JSlider slider2;
    private JButton clearButton;
    private JLabel infoLabel;
    private JTextArea textArea1;
    private MyCanvas myCanvas;

    public ClustersFrame(MyCanvas myCanvas) {
        this.myCanvas = myCanvas;
        setContentPane(contentPane);
        //setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //myCanvas.setZoom();
            }
        });
        slider1.addComponentListener(new ComponentAdapter() {
        });
        slider1.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                myCanvas.setZoom((float) slider1.getValue() / 100);
                myCanvas.updateUI();
                myCanvas.repaint();
                scrollPane.revalidate();
                scrollPane.repaint();
            }
        });
        slider2.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                myCanvas.setAttributeWidth((float) slider2.getValue());
                myCanvas.repaint();
                scrollPane.revalidate();
                scrollPane.repaint();
            }
        });
        clearButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }

    private void onOK() {
// add your code here
        dispose();
    }


    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        //myCanvas.setPreferredSize(new Dimension(6000, 4000));
        scrollPane = new JScrollPane(myCanvas);

    }

    public void updateAttributeInfo(String desc) {
        infoLabel.setText(desc);
    }

    public void saveAttributeInfo(String desc) {
        textArea1.setText(textArea1.getText() + "\n\r" + desc);
    }
}
