/*
 * LeftPanel.java
 *
 * Created on 23 wrzesień 2008, 20:25
 */
package ui;

import MCL.ClusterAlgorithm;
import MCL.ClusterSettingsDialog;
import MCL.MCLCluster;
import cytoscape.Cytoscape;
import java.awt.Color;
import java.util.Collection;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import logicmodel.controllers.DataHandle;
import viewmodel.structs.CytoProtein;
import logicmodel.structs.Family;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import utils.JTreeModelSpeciesGenerator;
import utils.Messenger;
import utils.Stats;
import viewmodel.controllers.CytoProjector;

/**
 *
 * 
 * @author  misiek
 */
public class LeftPanel extends javax.swing.JPanel {

    /** Creates new form LeftPanel */
    public LeftPanel() {
        initComponents();
        initColorList();
        initSpeciesTree();
        PluginMenusHandle.setTree(spaciesTree);
        PluginMenusHandle.setMemo(loggerTextArea);
        PluginMenusHandle.setUpdateDataButton(updateDataButton);
        PluginMenusHandle.setDoProjectionButton(projectButton);
        PluginMenusHandle.setShowNetowrkButton(showButton);
        PluginMenusHandle.setFamiliesList(familiesList);
        PluginMenusHandle.setShowLoadedInteractionsButton(showLoadedButton);
        PluginMenusHandle.setNewDataButton(newDataButton);
        UIController.getInstance().initButtonsState();
    }

    private void initColorList() {
        familiesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        familiesList.setListData(new String[0]);
        familiesList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                DataHandle dh = PluginDataHandle.getDataHandle();

                if (!e.getValueIsAdjusting()) {

                    String FamilyID = (String) familiesList.getSelectedValue();
                    if (FamilyID == null) {
                        return;
                    }
                    Family family = dh.getFamily(FamilyID);
                    Color color = family.getColor();
                    color = JColorChooser.showDialog(null, "Wybierz kolor dla rodziny białek: " + FamilyID, color);
                    family.setColor(color);
                    familiesList.clearSelection();
                }
            }
        });
    }

    private void initSpeciesTree() {

        JTreeModelSpeciesGenerator.decorateJTree(spaciesTree);
        spaciesTree.setModel(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataPanel = new javax.swing.JPanel();
        updateDataButton = new javax.swing.JButton();
        newDataButton = new javax.swing.JButton();
        attrPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loggerTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        familiesList = new javax.swing.JList();
        netsActionsPanel = new javax.swing.JPanel();
        projectButton = new javax.swing.JButton();
        showButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        spaciesTree = new javax.swing.JTree();
        intPanel = new javax.swing.JPanel();
        tresholdSpinner = new javax.swing.JSpinner();
        showLoadedButton = new javax.swing.JButton();
        mclButton = new javax.swing.JButton();
        testButton = new javax.swing.JButton();

        dataPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        updateDataButton.setText("Update data");
        updateDataButton.setToolTipText("Ładuje dane z plików .spy, .trees, .int");
        updateDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDataButtonActionPerformed(evt);
            }
        });

        newDataButton.setText("New data");
        newDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDataButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newDataButton)
                    .addComponent(updateDataButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        attrPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        loggerTextArea.setEditable(false);
        loggerTextArea.setRows(5);
        jScrollPane2.setViewportView(loggerTextArea);

        familiesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(familiesList);

        javax.swing.GroupLayout attrPanelLayout = new javax.swing.GroupLayout(attrPanel);
        attrPanel.setLayout(attrPanelLayout);
        attrPanelLayout.setHorizontalGroup(
            attrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attrPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(attrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap())
        );
        attrPanelLayout.setVerticalGroup(
            attrPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(attrPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );

        netsActionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        projectButton.setText("Casting...");
        projectButton.setToolTipText("Rzutuje na zaznaczone sieci w drzewku");
        projectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectButtonActionPerformed(evt);
            }
        });

        showButton.setText("Show network");
        showButton.setToolTipText("Wyswietla zaznaczone sieci w drzewku");
        showButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(spaciesTree);

        javax.swing.GroupLayout netsActionsPanelLayout = new javax.swing.GroupLayout(netsActionsPanel);
        netsActionsPanel.setLayout(netsActionsPanelLayout);
        netsActionsPanelLayout.setHorizontalGroup(
            netsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, netsActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(netsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                    .addGroup(netsActionsPanelLayout.createSequentialGroup()
                        .addComponent(projectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showButton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );
        netsActionsPanelLayout.setVerticalGroup(
            netsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(netsActionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(netsActionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showButton)
                    .addComponent(projectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        );

        intPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tresholdSpinner.setName("TresholdSpinner"); // NOI18N

        showLoadedButton.setText("Show loaded interactions");
        showLoadedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLoadedButtonActionPerformed(evt);
            }
        });

        mclButton.setText("MCL");
        mclButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mclButtonActionPerformed(evt);
            }
        });

        testButton.setText("Test");
        testButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout intPanelLayout = new javax.swing.GroupLayout(intPanel);
        intPanel.setLayout(intPanelLayout);
        intPanelLayout.setHorizontalGroup(
            intPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, intPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(intPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, intPanelLayout.createSequentialGroup()
                        .addComponent(mclButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(testButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(showLoadedButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap())
        );
        intPanelLayout.setVerticalGroup(
            intPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(intPanelLayout.createSequentialGroup()
                .addComponent(showLoadedButton)
                .addGap(11, 11, 11)
                .addGroup(intPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mclButton)
                    .addComponent(tresholdSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(testButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dataPanel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(attrPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(intPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(netsActionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(netsActionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(intPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(attrPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDataButtonActionPerformed
        UIController.getInstance().updateData();
}//GEN-LAST:event_updateDataButtonActionPerformed

    private void showButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showButtonActionPerformed
        UIController.getInstance().showSelectedNetworks();
}//GEN-LAST:event_showButtonActionPerformed

private void projectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectButtonActionPerformed
    Collection<PPINetwork> networks = UIController.getInstance().getSelectedNetworks();
    Collection<CytoProtein> selectedProteins = UIController.getInstance().getSelectedProteins(Cytoscape.getCurrentNetwork());

    if (selectedProteins.size() > 0) {
        CytoProjector.projectSelected(selectedProteins, networks);
    } else {
        Messenger.message("You have to select proteins and target network!");
    }

}//GEN-LAST:event_projectButtonActionPerformed

private void showLoadedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLoadedButtonActionPerformed
    double treshold = ((Integer) tresholdSpinner.getValue()).doubleValue() / 100.0;
    UIController.getInstance().showLoadedInteractions(treshold);

}//GEN-LAST:event_showLoadedButtonActionPerformed

private void mclButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mclButtonActionPerformed
    ClusterAlgorithm algorithm = new MCLCluster();

    ClusterSettingsDialog csd = new ClusterSettingsDialog(algorithm);
    csd.actionPerformed(evt);
}//GEN-LAST:event_mclButtonActionPerformed

private void newDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDataButtonActionPerformed

    if (PluginDataHandle.getDataHandle().speciesTreeLoaded()) {
        int ret = JOptionPane.showConfirmDialog(Cytoscape.getDesktop(), "All loaded data would be removed, are you sure?");
        System.out.println("RET: " + ret);
        if (ret == JOptionPane.OK_OPTION) {
            //NewDataWarningDialog dataWarning = new NewDataWarningDialog(Cytoscape.getDesktop(), true);
            //if (dataWarning.getReturnStatus() == NewDataWarningDialog.RET_OK) {
            UIController.getInstance().newData();
        }
    } else {
        UIController.getInstance().newData();
    }
}//GEN-LAST:event_newDataButtonActionPerformed

private void testButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testButtonActionPerformed
    Stats.printStats();
}//GEN-LAST:event_testButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel attrPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JList familiesList;
    private javax.swing.JPanel intPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea loggerTextArea;
    private javax.swing.JButton mclButton;
    private javax.swing.JPanel netsActionsPanel;
    private javax.swing.JButton newDataButton;
    private javax.swing.JButton projectButton;
    private javax.swing.JButton showButton;
    private javax.swing.JButton showLoadedButton;
    private javax.swing.JTree spaciesTree;
    private javax.swing.JButton testButton;
    private javax.swing.JSpinner tresholdSpinner;
    private javax.swing.JButton updateDataButton;
    // End of variables declaration//GEN-END:variables
}
