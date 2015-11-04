package sample;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class SelectAttributesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private JCheckBox selectDeselectAllCheckBox;
    private JComboBox algorithms;
    private JTextField parameters;
    private JButton runButton;
    private String result;

    ArrayList<NominalNumericalAttribute> attributes = new ArrayList<NominalNumericalAttribute>();

    Database database;

    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     *
     */
    public class MyModel implements TableModel {

        @Override
        public int getRowCount() {
            return attributes.size();
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
                    o = attributes.get(rowIndex).isUsed();
                    break;
                case 1:
                    o = attributes.get(rowIndex).getName();
                    break;
                case 2: // o = "" + rowIndex + ", " + columnIndex;
                    //o = attributes.get(rowIndex).getName();
                    if (attributes.get(rowIndex).getType()) {
                        o = "N";
                    } else {
                        o = "";
                    }
                    break;
                case 3:
                    o = attributes.get(rowIndex).getDescription();
                    break;
                default: o = ".";
            }
            return o;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            NominalNumericalAttribute a = attributes.get(rowIndex);
            a.setUsed((boolean) aValue);
        }

        @Override
        public void addTableModelListener(TableModelListener l) {

        }

        @Override
        public void removeTableModelListener(TableModelListener l) {

        }
    }

    /**
     *
     */
    public SelectAttributesDialog(Database database, String exclude, String attributesList) {

        setDatabase(database);

        loadAttributes(database, exclude, attributesList);

        setSize(500, 750);
        setContentPane(contentPane);
        setModal(true);
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

        table1.setModel(new MyModel());
        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(100);
        tcm.getColumn(2).setMaxWidth(100);

        selectDeselectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectDeselectAllCheckBox.isSelected()) {
                    for (NominalNumericalAttribute a : attributes) {
                        a.setUsed(true);
                    }
                } else {
                    for (NominalNumericalAttribute a : attributes) {
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
                System.out.println("---");
            }
        });
    }


    private void loadAttributes(Database database, String exclude, String attributesList) {
        // try to deserialize
        try {
            attributes = Utils.readAttributes("attributes.ser");
            if (attributes == null || attributes.size() <= 0) {
                throw new Exception("Attributes set is empty.");
            }
        } catch (Exception eRead) {
            eRead.printStackTrace();
            attributes = database.getAttributes(exclude, attributesList);
            try {
                Utils.saveAttributes(attributes, "attributes.ser");
            } catch (Exception eSave) {
                eSave.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @return
     */
    public ArrayList<NominalNumericalAttribute> getNominalNumericalAttributes() {
        ArrayList<NominalNumericalAttribute> selected = new ArrayList<NominalNumericalAttribute>();
        for(NominalNumericalAttribute a : attributes) {
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
// add your code here
        //System.out.println("OK");
        result = "";
        int cnt = 0;
        StringBuilder sb = new StringBuilder();

        for(NominalNumericalAttribute a : attributes) {
            //System.out.println(a.getName() + ": " + a.isUsed());
            if (a.isUsed()) {
                if (cnt++ > 0) sb.append(",");
                sb.append("'");
                sb.append(a.getName());
                sb.append("'");
            }
        }

        result = sb.toString();

        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        result = null;
        dispose();
    }

//    public static void main(String[] args) {
//        SelectAttributesDialog dialog = new SelectAttributesDialog();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }

}
