/*
 * DataLoadingErrorsJPanel.java
 *
 * Created on 2009-05-18, 19:10:58
 */
package ppine.ui.dataloading;

import ppine.logs.errorsloger.PPINEErrorsLogger;
import ppine.logs.errorsloger.PPINELoggedError;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author misiek
 */
public class DataLoadingErrorsJPanel extends javax.swing.JPanel {

    /** Creates new form DataLoadingErrorsJPanel */
    public DataLoadingErrorsJPanel() {
        initComponents();

        errorsTable.setAutoCreateRowSorter(true);
        initTableState();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        errorsTable = new javax.swing.JTable();
        cleanButton = new javax.swing.JButton();

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        errorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Error ID", "Date", "Error Message", "Additional Message", "Source"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        errorsTable.setName("errorsTable"); // NOI18N
        jScrollPane1.setViewportView(errorsTable);

        cleanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppine/resources/icons/clean.png"))); // NOI18N
        cleanButton.setText("Clean");
        cleanButton.setToolTipText("null");
        cleanButton.setName("cleanButton"); // NOI18N
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cleanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(cleanButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) errorsTable.getModel();
        int size = model.getRowCount();
        for(int i=0; i<size; i++) {
            model.removeRow(0);
        }
        PPINEErrorsLogger.deleteAll();
    }//GEN-LAST:event_cleanButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cleanButton;
    private javax.swing.JTable errorsTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private void initTableState() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DefaultTableModel model = (DefaultTableModel) errorsTable.getModel();
        Collection<PPINELoggedError> errors = PPINEErrorsLogger.getErrorsCollection();
        for (PPINELoggedError error : errors) {
            Integer id = error.getId();
            String message = error.getMessage();
            String errorMessage = error.getException().toString();
            String source = error.getSource();
            String dateString = dateFormat.format(error.getDate());

            Object[] row = new Object[]{id, dateString, errorMessage, message, source};
            model.addRow(row);
        }
    }
}
