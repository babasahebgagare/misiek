package mcv.ui.familycolors;

import mcv.ui.*;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.table.AbstractTableModel;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.Family;
import mcv.main.PluginDataHandle;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
class JTableButtonModel extends AbstractTableModel {

    private Object[][] __rows;

    public JTableButtonModel() {
        createModel();
        DataHandle dh = PluginDataHandle.getDataHandle();

        for (int i = 0; i < __rows.length; i++) {
            String familyID = (String) __rows[i][0];
            Family fam = dh.getFamily(familyID);
            JButton button = new JButton();
            button.setBorderPainted(false);
            button.setToolTipText(familyID);
            button.setBackground(fam.getColor());
            button.addMouseListener(new MouseListener() {

                public void mouseClicked(MouseEvent e) {
                    //   Messenger.message("aaa");
                }

                public void mousePressed(MouseEvent e) {
                    DataHandle dh = PluginDataHandle.getDataHandle();
                    JButton button = (JButton) e.getSource();
                    String familyID = button.getToolTipText();
                    System.out.println(familyID);
                    if (familyID == null) {
                        return;
                    }
                    Family family = dh.getFamily(familyID);
                    Color color = family.getColor();
                    color = JColorChooser.showDialog(null, "Choose color for selected proteins family: " + familyID, color);
                    if (color != null) {
                        family.setColor(color);
                        PluginMenusHandle.getFamiliesColorListPanel().refresh();

                        CyNetworkView netView = Cytoscape.getCurrentNetworkView();
                        if (netView != null) {
                            netView.redrawGraph(true, true);
                        }
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    //       Messenger.message("ccc");
                }

                public void mouseEntered(MouseEvent e) {
                    //     Messenger.message("ddd");
                }

                public void mouseExited(MouseEvent e) {
                    //   Messenger.message("ff");
                }
            });
            __rows[i][1] = button;
        }
    }
    private String[] __columns = {"Family name", "Color"};

    public String getColumnName(int column) {
        return __columns[column];
    }

    public int getRowCount() {
        return __rows.length;
    }

    public int getColumnCount() {
        return __columns.length;
    }

    public Object getValueAt(int row, int column) {
        return __rows[row][column];
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    private void createModel() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        __rows = new Object[dh.getFamilies().size()][2];
        int i = 0;
        for (Family fam : dh.getFamilies()) {
            __rows[i][0] = fam.getFamilyID();
            //        __rows[i][1] = fam.getColor();
            i++;
        }
    }
}

