/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.ui.dataloading;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.help.CSH;
import javax.swing.JFileChooser;
import javax.swing.tree.TreeModel;
import ppine.help.PPINEHelpBroker;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.main.LoadedDataHandle;
import ppine.main.PluginDataHandle;
import ppine.ui.UIController;
import ppine.ui.listeners.SpeciesLoadedListener;
import ppine.utils.JTreeModelSpeciesGenerator;
import org.jdesktop.swingx.error.ErrorEvent;

public class SpeciesTreeLoaderPanel extends javax.swing.JPanel {

    private String filepath;
    private SpeciesLoadedListener list;
    private DataLoaderPanel loaderPanel;

    /** Creates new form SpeciesTreeLoaderPanel
     * @param list 
     */
    public SpeciesTreeLoaderPanel(SpeciesLoadedListener list) {
        this.list = list;
        initComponents();
        initState();
    }

    public void setLoaderPanel(DataLoaderPanel loaderPanel) {
        this.loaderPanel = loaderPanel;
    }

    public void initState() {
        LoadedDataHandle dh = PluginDataHandle.getLoadedDataHandle();
        if (dh.speciesTreeLoaded()) {
            setLoadedState();
        } else {
            setUnloadedState();
        }
    }

    public void logSpeciesLoadingError(ErrorEvent errorEvent) {
        errorLabel.setText(errorEvent.getThrowable().getMessage());
    }

    private void setFilenameLabel() {
        String filename = PluginDataHandle.getLoadingDataHandle().getSpeciesFilename();
        if (filename != null) {
            filenameLabel.setText(filename);
            filepath = filename;
        } else {
            loadTreeButton.setEnabled(false);
        }
    }

    public void setLoadedState() {
        loadTreeButton.setEnabled(false);
        chooseFile.setEnabled(false);
        TreeModel treeModel = JTreeModelSpeciesGenerator.generateModel();
        speciesTree.setModel(treeModel);
        JTreeModelSpeciesGenerator.decorateJTree(speciesTree);
        if (PluginDataHandle.getLoadedDataHandle().isProteinsLoaded()) {
            cleanButton.setEnabled(false);
        } else {
            cleanButton.setEnabled(true);
        }

        setFilenameLabel();
    }

    public void setUnloadedState() {
        speciesTree.setModel(null);
        loadTreeButton.setEnabled(true);
        chooseFile.setEnabled(true);
        cleanButton.setEnabled(false);
        setFilenameLabel();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chooseFile = new javax.swing.JButton();
        filenameLabel = new javax.swing.JLabel();
        loadTreeButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        speciesTree = new javax.swing.JTree();
        helpButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        cleanButton = new javax.swing.JButton();

        chooseFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppine/resources/icons/com.png"))); // NOI18N
        chooseFile.setText("Choose file");
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ppine/ui/dataloading/Bundle"); // NOI18N
        chooseFile.setToolTipText(bundle.getString("ChooseFileFamiliesTreeButton.ToolTip_2")); // NOI18N
        chooseFile.setName("chooseFile"); // NOI18N
        chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileActionPerformed(evt);
            }
        });

        filenameLabel.setText("filename");
        filenameLabel.setName("filenameLabel"); // NOI18N

        loadTreeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppine/resources/icons/save.png"))); // NOI18N
        loadTreeButton.setText("Load tree");
        loadTreeButton.setToolTipText(bundle.getString("LoadFamiliesTreesButton.ToolTip_1")); // NOI18N
        loadTreeButton.setName("loadTreeButton"); // NOI18N
        loadTreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadTreeButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        speciesTree.setName("speciesTree"); // NOI18N
        jScrollPane1.setViewportView(speciesTree);

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppine/resources/icons/help.png"))); // NOI18N
        helpButton.setText("File format info");
        helpButton.setToolTipText(bundle.getString("FileFormatInfoSpecies.ToolTip_1")); // NOI18N
        helpButton.setName("helpButton"); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 51));
        errorLabel.setName("errorLabel"); // NOI18N

        cleanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ppine/resources/icons/clean.png"))); // NOI18N
        cleanButton.setText("Clean");
        cleanButton.setToolTipText(bundle.getString("CleanSpeciesTree.ToolTip_1")); // NOI18N
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadTreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cleanButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(filenameLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(helpButton)
                    .addComponent(cleanButton)
                    .addComponent(loadTreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filenameLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(errorLabel)
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filepath = file.getAbsolutePath();
            filenameLabel.setText(filepath);
            PluginDataHandle.getLoadingDataHandle().setSpeciesFilename(filepath);
            loadTreeButton.setEnabled(true);
        }
    }//GEN-LAST:event_chooseFileActionPerformed

    private void loadTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadTreeButtonActionPerformed
        if (filepath != null) {

            SpeciesLoadingErrorsListener errorListener = new SpeciesLoadingErrorsListener(loaderPanel);
            DefaultLoadingController.loadSpeciesTreeData(filepath, errorListener);
            setLoadedState();
            list.actionPerformed(new ActionEvent(this, 1, "Species loaded"));
        }
    }//GEN-LAST:event_loadTreeButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        CSH.DisplayHelpFromSource csh = new CSH.DisplayHelpFromSource(PPINEHelpBroker.getHelpBroker("Species file format"));
        csh.actionPerformed(new ActionEvent(this, 120, "Species file format"));
    }//GEN-LAST:event_helpButtonActionPerformed

    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        UIController.getInstance().deleteData();
        setUnloadedState();
    }//GEN-LAST:event_cleanButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseFile;
    private javax.swing.JButton cleanButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel filenameLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadTreeButton;
    private javax.swing.JTree speciesTree;
    // End of variables declaration//GEN-END:variables
}
