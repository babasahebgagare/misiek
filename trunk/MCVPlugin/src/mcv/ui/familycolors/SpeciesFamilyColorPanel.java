/*
 * SpeciesFamilyColorPanel.java
 *
 * Created on 2009-05-14, 02:39:49
 */
package mcv.ui.familycolors;

import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author misiek
 */
public class SpeciesFamilyColorPanel extends javax.swing.JPanel {

    /** Creates new form SpeciesFamilyColorPanel */
    public SpeciesFamilyColorPanel() {
        initComponents();
        tableButtonInit();

    }

    public void clean() {
        jTable1.setModel(new JTableButtonModel());
    }

    public void refresh() {
        TableModel model = new JTableButtonModel();
        jTable1.setModel(model);
        jTable1.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        attrPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(280, 32767));
        setPreferredSize(new java.awt.Dimension(270, 169));

        attrPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Colors of families"));
        attrPanel.setName("attrPanel"); // NOI18N
        attrPanel.setPreferredSize(new java.awt.Dimension(270, 169));

        jScrollPane4.setBorder(null);
        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Family name", "Color"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout attrPanelLayout = new javax.swing.GroupLayout(attrPanel);
        attrPanel.setLayout(attrPanelLayout);
        attrPanelLayout.setHorizontalGroup(
            attrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );
        attrPanelLayout.setVerticalGroup(
            attrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attrPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(attrPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attrPanel;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void tableButtonInit() {
        TableCellRenderer defaultRenderer;

        //jTable1.setModel(new JTableButtonModel());
        defaultRenderer = jTable1.getDefaultRenderer(JButton.class);
        jTable1.setDefaultRenderer(JButton.class,
                new JTableButtonRenderer(defaultRenderer));
        //    jTable1.setPreferredScrollableViewportSize(new Dimension(400, 200));
        jTable1.addMouseListener(new JTableButtonMouseListener(jTable1));

    }
}
