/*
 * SpeciesInteractionsLoaderPanel.java
 *
 * Created on 2009-04-27, 16:03:06
 */
package mcv.ui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;
import mcv.main.LoadedDataHandle;
import mcv.main.PluginDataHandle;

/**
 *
 * @author misiek
 */
public class SpeciesInteractionsLoaderPanel extends javax.swing.JPanel {

    private String name;
    private String filepath;

    /** Creates new form SpeciesInteractionsLoaderPanel
     * @param name
     * @return 
     */
    public boolean checked() {
        return checkbox.isSelected();
    }

    public String tryGetFilepath() {
        return filepath;
    }

    public SpeciesInteractionsLoaderPanel(final String name) {
        initComponents();
        this.name = name;
        TitledBorder border = (TitledBorder) this.getBorder();
        border.setTitle(name);
        this.tresholdField.setText("1.0");
        setActualLoadedUIState();
    }

    public Double tryGetTreshold() {
        if (checkbox.isSelected()) {
            try {
                Double treshold = Double.valueOf(tresholdField.getText());
                return treshold;
            } catch (NumberFormatException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    public String getSpeciesName() {
        return name;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox = new javax.swing.JCheckBox();
        tresholdField = new javax.swing.JTextField();
        openButton = new javax.swing.JButton();
        filepathLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("species name"));

        checkbox.setName("checkbox"); // NOI18N
        checkbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxActionPerformed(evt);
            }
        });

        tresholdField.setEnabled(false);
        tresholdField.setName("tresholdField"); // NOI18N

        openButton.setText("Open");
        openButton.setEnabled(false);
        openButton.setName("openButton"); // NOI18N
        openButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openButtonActionPerformed(evt);
            }
        });

        filepathLabel.setText("filepath");
        filepathLabel.setName("filepathLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tresholdField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filepathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tresholdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openButton)
                        .addComponent(filepathLabel))
                    .addComponent(checkbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxActionPerformed
        if (this.checkbox.isSelected()) {
            tresholdField.setEnabled(true);
            openButton.setEnabled(true);
            filepathLabel.setEnabled(true);
        //   filepath = PluginDataHandle.getLoadingDataHandle().getSpeciesFilename(name);
        //   if (filepath != null) {
        //        filepathLabel.setText(filepath);
        //    }
        } else {
            tresholdField.setEnabled(false);
            openButton.setEnabled(false);
            filepathLabel.setEnabled(false);
        /*if (filepath != null) {
        PluginDataHandle.getLoadingDataHandle().deleteInteractionFilename(name, filepath);
        }*/
        }
    }//GEN-LAST:event_checkboxActionPerformed

    private void openButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filepath = file.getAbsolutePath();
            //      PluginDataHandle.getLoadingDataHandle().addInteractionFilename(name, filepath);
            filepathLabel.setText(filepath);
        }
    }//GEN-LAST:event_openButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkbox;
    private javax.swing.JLabel filepathLabel;
    private javax.swing.JButton openButton;
    private javax.swing.JTextField tresholdField;
    // End of variables declaration//GEN-END:variables

    private void setActualLoadedUIState() {
        LoadedDataHandle ldh = PluginDataHandle.getLoadedDataHandle();
        String filename = ldh.getSpeciesInteractionsFilename(name);
        Double treshold = ldh.getSpeciesInteractionsTreshold(name);
        if (filename == null) {
            checkbox.setSelected(false);
            tresholdField.setEnabled(false);
            openButton.setEnabled(false);
            filepathLabel.setEnabled(false);
        } else {
            checkbox.setSelected(true);
            tresholdField.setEnabled(true);
            openButton.setEnabled(true);
            filepath = filename;
            filepathLabel.setEnabled(true);
            filepathLabel.setText(filename);
            if (treshold != null) {
                tresholdField.setText(String.valueOf(treshold));
            }
        }
    }
}