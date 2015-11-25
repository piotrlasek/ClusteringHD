package sample;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Piotr Lasek
 *
 */
public class SelectAttributesDialog extends JFrame {
    private JPanel contentPane;
    private JTable table1;
    private JCheckBox selectDeselectAllCheckBox;
    private JComboBox algorithms;
    private JTextField parameters;
    private JButton runButton;
    private JButton showResultsButton;
    private JTextField distanceMatrixTextField;
    private JButton button1;
    private JButton generateButton;
    private JButton visualizeButton;
    private JButton saveToDBButton;
    private JButton readFromDBButton;
    private JTextField a17000TextField;
    private JButton saveToCSVButton;
    private JButton saveResultsButton;
    private JButton loadAttributesButton;
    private String result;

    ArrayList<NominalNumericalAttribute> allAttributes = new ArrayList<NominalNumericalAttribute>();
    Database database;
    final static Logger log = Logger.getLogger(SelectAttributesDialog.class);
    private ArrayList<NominalNumericalObject> dataset;
    private DistanceMatrix distanceMatrix;

    DbScanVec dbscan = null;
    /**
     *
     * @param database
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     *
     */
    public SelectAttributesDialog(Database database, String exclude, String attributesList) {

        setDatabase(database);

        loadAttributes(database, exclude, attributesList);

        setSize(700, 850);
        setContentPane(contentPane);
        //setModal(true);

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

        table1.setModel(new MyModel());
        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(100);
        tcm.getColumn(2).setMaxWidth(100);

        selectDeselectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectDeselectAllCheckBox.isSelected()) {
                    for (NominalNumericalAttribute a : allAttributes) {
                        a.setUsed(true);
                    }
                } else {
                    for (NominalNumericalAttribute a : allAttributes) {
                        a.setUsed(false);
                    }
                }

                table1.updateUI();
            }
        });

        runButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] params = parameters.getText().split(";");

                int minPts = new Integer(params[0]);
                float eps = new Float(params[1]);

                log.info("mouseClicked START");

                long start = System.currentTimeMillis();

                // DATA SET
                String datasetFile = "dataset.ser";

                // dataset = database.readDataFromFile(datasetFile);

                if (dataset == null) {
                    log.info("   Reading dataset from database.");
                    try {
                        dataset = database.readData(getResult(), getNominalNumericalAttributes(), exclude);
                    } catch (SQLException e1) {
                        log.error(e1);
                    }
                }

                // DISTANCE MATRIX
                if (distanceMatrix == null) {
                    log.info("   Building distance matrix from database.");
                    distanceMatrix = database.buildDistanceMatrix(dataset, false);
                }

                long end = System.currentTimeMillis();
                log.info("   Data read in: " + (end - start) + "ms." );

                dbscan = new DbScanVec(dataset, distanceMatrix);
                dbscan.setMinPts(minPts);
                dbscan.setEps(eps);

                log.info("   MinPts: " + dbscan.getMinPts() + ", Eps: " + dbscan.getEps());

                start = System.currentTimeMillis();
                try {
                    dbscan.run();
                } catch (FileNotFoundException e1) {
                    log.error(e1);
                }

                end = System.currentTimeMillis();
                log.info("  Clustering time: " + (end - start) + "ms." );

                JOptionPane.showMessageDialog(null, "Clustering finished!");

                log.info("mouseClicked END");
            }
        });
        /*showResultsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });*/
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                int returnVal = fileChooser.showOpenDialog(SelectAttributesDialog.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    distanceMatrixTextField.setText(file.getName());
                    //This is where a real application would open the file.
                    log.info("Opening: " + file.getName() + ".");
                    try {
                        String distanceMatrixFileName = distanceMatrixTextField.getText();
                        if (!distanceMatrixFileName.equals("")) {
                            log.info("   Reading distance matrix from: " + distanceMatrixFileName);
                            try {
                                distanceMatrix = DistanceMatrix.read(distanceMatrixFileName);
                                a17000TextField.setText("" + distanceMatrix.points.size());
                                Main.limit = new Integer(a17000TextField.getText());
                                ArrayList<NominalNumericalAttribute> attributes = distanceMatrix.getAttributes();

                                for(NominalNumericalAttribute nna : allAttributes) {
                                    if (attributes.contains(nna)) {
                                        nna.setUsed(true);
                                    } else {
                                        nna.setUsed(false);
                                    }
                                }
                            } catch (Exception e1) {
                                log.error(e1);
                                distanceMatrix = null;
                            }
                        }
                    } catch (Exception e1) {
                        log.error(e1);
                    }
                } else {
                    log.info("Open command cancelled by user.");
                }
            }
        });

        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                try {
                    Main.limit = new Integer(a17000TextField.getText());
                    log.info("   Reading database.");
                    dataset = database.readData(getResult(), getNominalNumericalAttributes(), exclude);
                    log.info("   Building distance matrix from database.");
                    distanceMatrix = database.buildDistanceMatrix(dataset, true);
                    log.info("   Saving distance matrix to database");
                    database.saveDistanceMatrixToDatabase(distanceMatrix);
                } catch (SQLException e1) {
                    log.error(e1);
                }
            }
        });
        visualizeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                log.info("mouseClicked START");
                ClusterVisualizer cv = null;
                try {
                    Main.limit = new Integer(a17000TextField.getText());

                    String experimentIdString = JOptionPane.showInputDialog(this, "Experiment id");
                    Integer experimentId = new Integer(experimentIdString);

                    cv = new ClusterVisualizer(database, getNominalNumericalAttributes(), experimentId);
                    cv.prepareColors();
                    cv.prepareWeights();
                    cv.showClusters();
                } catch (SQLException e1) {
                    log.error(e1);
                    e1.printStackTrace();
                }
                log.info("mouseClicked END");
            }
        });
        saveToDBButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                database.saveDistanceMatrixToDatabase(distanceMatrix);
            }
        });
        readFromDBButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String name = JOptionPane.showInputDialog(this, "Data matrix id:");
                Integer dmid = new Integer(name);

                DistanceMatrix dm = null;

                try {
                    dm = database.readDistanceMatrixFromDatabase(dmid);
                    a17000TextField.setText("" + dm.points.size());
                } catch (Exception e1) {
                    log.error(e1);
                }

                log.info("DM size: " + dm.getHeight() + ", " + dm.getWidth());
            }
        });
        saveToCSVButton.addMouseMotionListener(new MouseMotionAdapter() {
        });
        saveToCSVButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                 String fileName = JOptionPane.showInputDialog(this, "Data matrix id:");
                database.saveDistanceMatrixToCSV(distanceMatrix, fileName);

            }
        });
        saveResultsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    Utils.saveClusters(dbscan.getClusters(), database);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    log.error(e1);
                }
            }
        });
        loadAttributesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                // ...

                AttributesReaderForm arf = new AttributesReaderForm();
                arf.setVisible(true);
                ArrayList<String> attributes = arf.getAttributes();


                for(NominalNumericalAttribute nna : allAttributes) {
                    if (attributes.contains(nna.getName())) {
                        nna.setUsed(true);
                    } else {
                        nna.setUsed(false);
                    }
                }

            }
        });
    }

    /**
     *
     * @param database
     * @param exclude
     * @param attributesList
     */
    private void loadAttributes(Database database, String exclude, String attributesList) {
        // try to deserialize
        try {
            allAttributes = Utils.readAttributes("allAttributes.ser");
            if (allAttributes == null || allAttributes.size() <= 0) {
                throw new Exception("Attributes set is empty.");
            }
        } catch (Exception eRead) {
            eRead.printStackTrace();
            allAttributes = database.getAttributes(exclude, attributesList);
            try {
                Utils.saveAttributes(allAttributes, "allAttributes.ser");
            } catch (Exception eSave) {
                log.error(eSave);
            }
        }
    }

    /**
     *
     * @return
     */
    public String getResult() {
        result = "";
        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        for(NominalNumericalAttribute a : allAttributes) {
            //System.out.println(a.getName() + ": " + a.isUsed());
            if (a.isUsed()) {
                if (cnt++ > 0) sb.append(",");
                sb.append("'");
                sb.append(a.getName());
                sb.append("'");
            }
        }

        result = sb.toString();
        return result;
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalAttribute> getNominalNumericalAttributes() {
        ArrayList<NominalNumericalAttribute> selected = new ArrayList<NominalNumericalAttribute>();
        for(NominalNumericalAttribute a : allAttributes) {
            if (a.isUsed()) {
                selected.add(a);
            }
        }
        return selected;
    }

    public String getAlgorithm() {
        return algorithms.getSelectedItem().toString();
    }

    public String getParameters() {
        return parameters.getText();
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        result = null;
        dispose();
    }

    /**
     *
     */
    public class MyModel implements TableModel {

        @Override
        public int getRowCount() {
            return allAttributes.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int columnIndex) {
            if (columnIndex == 0) return "Use";
            else if (columnIndex == 1) return "Attribute";
            else if (columnIndex == 2) return "Format";
            else return "Description";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class c;
            switch(columnIndex) {
                case 0: c = Boolean.class;
                    break;
                case 1:
                case 2: c = String.class;
                    break;
                default:
                    c = String.class;
            }
            return c;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Object o;

            switch (columnIndex) {
                case 0: // o = (rowIndex % 2 == 1) ? false : true;
                    o = allAttributes.get(rowIndex).isUsed();
                    break;
                case 1:
                    o = allAttributes.get(rowIndex).getName();
                    break;
                case 2: // o = "" + rowIndex + ", " + columnIndex;
                    //o = allAttributes.get(rowIndex).getName();
                    if (allAttributes.get(rowIndex).getType()) {
                        o = "N";
                    } else {
                        o = "";
                    }
                    break;
                case 3:
                    o = allAttributes.get(rowIndex).getDescription();
                    break;
                default: o = ".";
            }
            return o;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            NominalNumericalAttribute a = allAttributes.get(rowIndex);
            a.setUsed((boolean) aValue);
        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }

}
