package sample;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectAttributesDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTable table1;
    private JCheckBox selectDeselectAllCheckBox;
    private JComboBox algorithms;
    private JTextField parameters;
    private String result;

    ArrayList<Attribute> attributes = new ArrayList<Attribute>();

    /**
     *
     */
    public class Attribute {
        int id;
        private String name;
        private String description;
        private boolean used;
        String format;
        private Float min;
        private Float max;
        private boolean numerical;

        public Attribute(int id, String name, String description, String format, Float min, Float max, boolean numerical) {
            this.id = id;
            this.setName(name);
            this.setDescription(description);
            this.used = true;
            this.format = format;
            this.min = min;
            this.max = max;
            this.numerical = numerical;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFormat() {
            return format;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Float getMin() {
            return min;
        }

        public Float getMax() {
            return max;
        }

        public boolean isNumerical() {
            return numerical;
        }
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
                    if (attributes.get(rowIndex).isNumerical()) {
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
            Attribute a = attributes.get(rowIndex);
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
    public SelectAttributesDialog(String attributesList, String exclude, Connection connection) {

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


        try {
            Statement statement = connection.createStatement();
            String sql;
            if (attributesList.equals("*")) {
                sql =
                        "SELECT trim(name) as name, trim(label) as description, trim(format) as format, " +
                                "numerical, min, max " +
                                "FROM attributes " +
                                "WHERE name NOT IN ( " + exclude +" )";
            } else {
                sql =
                        "SELECT trim(name) as name, trim(label) as description, trim(format) as format " +
                                "numerical, min, max " +
                                "FROM attributes WHERE name in (" + attributesList + ") " +
                                "AND NAME NOT IN ( " + exclude + ")";
            }
            //System.out.println(sql);
            statement.execute(sql);
            ResultSet rs = statement.getResultSet();
            int id = 0;
            while(rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String format = rs.getString("format");
                Float min = rs.getFloat("min");
                Float max = rs.getFloat("max");
                boolean numerical = rs.getBoolean("numerical");
                Attribute a = new Attribute(id++, name, description, format, min, max, numerical);
                attributes.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table1.setModel(new MyModel());
        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(0).setMaxWidth(50);
        tcm.getColumn(1).setMaxWidth(100);
        tcm.getColumn(2).setMaxWidth(100);

        selectDeselectAllCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectDeselectAllCheckBox.isSelected()) {
                    for(Attribute a : attributes) {
                        a.setUsed(true);
                    }
                } else {
                    for(Attribute a : attributes) {
                        a.setUsed(false);
                    }
                }

                table1.updateUI();
            }
        });
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
    public ArrayList<sample.Attribute> getAttributes() {

        ArrayList<sample.Attribute> selected = new ArrayList<sample.Attribute>();
        for(Attribute a : attributes) {
            //System.out.println(a.getName() + ": " + a.isUsed());
            if (a.isUsed()) {
                selected.add(new sample.Attribute(null, a.getName(), null));
            }
        }
        return selected;
    }

    public ArrayList<NominalNumericalAttribute> getNominalNumericalAttributes() {
        ArrayList<NominalNumericalAttribute> selected = new ArrayList<NominalNumericalAttribute>();
        for(Attribute a : attributes) {
            //System.out.println(a.getName() + ": " + a.isUsed());
            if (a.isUsed()) {
                NominalNumericalAttribute nna = new NominalNumericalAttribute(
                        a.getName(), a.isNumerical(), a.getMin(), a.getMax());

                selected.add(nna);
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

        for(Attribute a : attributes) {
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
