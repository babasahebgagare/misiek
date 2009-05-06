/*
 * InteractionsLoaderPanel.java
 *
 * Created on 2009-04-11, 22:30:19
 */
package mcv.ui;

import mcv.io.AbstractDataReader;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Vector;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.LoadedDataHandle;
import mcv.main.PluginDataHandle;
import mcv.ui.listeners.InteractionsLoadedListener;

/**
 *
 * @author misiek
 */
public class InteractionsLoaderPanel extends javax.swing.JPanel {

    Collection<SpeciesInteractionsLoaderPanel> panels = new Vector<SpeciesInteractionsLoaderPanel>();
    InteractionsLoadedListener list;

    public InteractionsLoaderPanel(InteractionsLoadedListener list) {
        this.list = list;
        initComponents();
        //    System.out.println("HEHEHE");
        // initSpeciesList();
        initSpeciesList();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        loadingPanel = new javax.swing.JPanel();

        loadButton.setText("Update");
        loadButton.setName("loadButton"); // NOI18N
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        loadingPanel.setName("loadingPanel"); // NOI18N

        javax.swing.GroupLayout loadingPanelLayout = new javax.swing.GroupLayout(loadingPanel);
        loadingPanel.setLayout(loadingPanelLayout);
        loadingPanelLayout.setHorizontalGroup(
            loadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );
        loadingPanelLayout.setVerticalGroup(
            loadingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(loadingPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(282, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /* private void initSpeciesList() {
    DataHandle dh = PluginDataHandle.getDataHandle();
    if (dh == null) {
    return;
    }

    DefaultTableModel tableModel = (DefaultTableModel) intLoadingTable.getModel();
    for (String species : dh.getNetworks().keySet()) {
    tableModel.addRow(new Object[]{species, genereteTreshold(species)});
    }
    }*/
    public void initSpeciesList() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        if (!dh.isProteinsLoaded()) {
            return;
        }

        loadingPanel.setLayout(new GridLayout(dh.getNetworks().keySet().size(), 1));

        for (String species : dh.getNetworks().keySet()) {
            SpeciesInteractionsLoaderPanel panel = new SpeciesInteractionsLoaderPanel(species);
            System.out.println("species: " + species);
            panels.add(panel);
            loadingPanel.add(panel);
        }
    }

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        for (SpeciesInteractionsLoaderPanel speciesPanel : panels) {

            String speciesName = speciesPanel.getSpeciesName();
            PPINetwork network = PluginDataHandle.getDataHandle().getNetworks().get(speciesName);
            if (speciesPanel.checked()) {
                String filename = speciesPanel.tryGetFilepath();
                if (filename != null) {
                    Double oldTresholdOrNull = PluginDataHandle.getLoadedDataHandle().getSpeciesInteractionsTreshold(speciesName);
                    Double tresholdOrNull = speciesPanel.tryGetTreshold();
                    updateInteractionsDataForSpecies(network, speciesName, filename, tresholdOrNull, oldTresholdOrNull);
                }
            } else {
                network.deleteAllInteractions();
                PluginDataHandle.getLoadedDataHandle().deleteInteractionData(speciesName);
            }
        }
        list.actionPerformed(new ActionEvent(this, 3, "Interactions loaded"));

}//GEN-LAST:event_loadButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadButton;
    private javax.swing.JPanel loadingPanel;
    // End of variables declaration//GEN-END:variables

    private void updateInteractionsDataForSpecies(PPINetwork network, String speciesName, String filename, Double tresholdOrNull, Double oldTresholdOrNull) {
        LoadedDataHandle ldh = PluginDataHandle.getLoadedDataHandle();
        if (ldh.loadedInteractions(speciesName)) {
            ldh.deleteInteractionData(speciesName);
            network.deleteAllInteractions();
            ldh.addInteractionData(speciesName, filename, tresholdOrNull);
            AbstractDataReader.getInstance().readSpeciesInteractions(network, filename, tresholdOrNull);
        } else {
            ldh.addInteractionData(speciesName, filename, tresholdOrNull);
            AbstractDataReader.getInstance().readSpeciesInteractions(network, filename, tresholdOrNull);
        }
    }
}