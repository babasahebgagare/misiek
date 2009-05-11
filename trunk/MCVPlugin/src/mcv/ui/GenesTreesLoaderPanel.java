/*
 * GenesTreesLoaderPanel.java
 *
 * Created on 2009-04-27, 22:45:13
 */
package mcv.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.help.CSH;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import mcv.help.MCVHelpBroker;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.ui.listeners.ProteinsLoadedListener;

/**
 *
 * @author misiek
 */
public class GenesTreesLoaderPanel extends javax.swing.JPanel {

    String filepath;
    ProteinsLoadedListener list;

    /** Creates new form GenesTreesLoaderPanel
     * @param list
     */
    public GenesTreesLoaderPanel(ProteinsLoadedListener list) {
        this.list = list;
        initComponents();
        initState();
    }

    public void initState() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        if (dh.isProteinsLoaded()) {
            setLoadedState();
        } else {
            setUnloadedState();
        }
        refreshStats();
    }

    public void refreshStats() {
        DefaultTableModel model = (DefaultTableModel) proteinsStatsTable.getModel();
        model.setRowCount(0);
        DataHandle dh = PluginDataHandle.getDataHandle();
        for (PPINetwork network : dh.getNetworks().values()) {
            String networkID = network.getID();
            Integer proteinsCount = Integer.valueOf(network.getProteins().size());
            Integer interactionsCount = Integer.valueOf(network.getInteractions().size());
            model.addRow(new Object[]{networkID, proteinsCount, interactionsCount});
        }
    }

    private void setFilenameLabel() {
        String filename = PluginDataHandle.getLoadingDataHandle().getGenesFilename();
        if (filename != null) {
            filenameLabel.setText(filename);
            filepath = filename;
        } else {
            loadButton.setEnabled(false);
        }
    }

    public void setLoadedState() {
        loadButton.setEnabled(false);
        chooseButton.setEnabled(false);
        setFilenameLabel();
    }

    public void setUnloadedState() {
        loadButton.setEnabled(true);
        chooseButton.setEnabled(true);
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

        filenameLabel = new javax.swing.JLabel();
        chooseButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        proteinsStatsTable = new javax.swing.JTable();
        infoButton = new javax.swing.JButton();

        filenameLabel.setText("filename");
        filenameLabel.setName("filenameLabel"); // NOI18N

        chooseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mcv/resources/icons/com.png"))); // NOI18N
        chooseButton.setText("Choose file");
        chooseButton.setName("chooseButton"); // NOI18N
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        loadButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mcv/resources/icons/save.png"))); // NOI18N
        loadButton.setText("Load");
        loadButton.setName("loadButton"); // NOI18N
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        proteinsStatsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Species name", "Proteins count", "Interactions count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        proteinsStatsTable.setName("proteinsStatsTable"); // NOI18N
        jScrollPane2.setViewportView(proteinsStatsTable);

        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mcv/resources/icons/help.png"))); // NOI18N
        infoButton.setText("File format info");
        infoButton.setName("infoButton"); // NOI18N
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(chooseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                        .addComponent(infoButton))
                    .addComponent(filenameLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseButton)
                    .addComponent(infoButton)
                    .addComponent(loadButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filenameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            filepath = file.getAbsolutePath();
            PluginDataHandle.getLoadingDataHandle().setGenesFilename(filepath);
            filenameLabel.setText(filepath);
            loadButton.setEnabled(true);
        }
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        if (filepath != null) {
            UIController.getInstance().loadGenesTreeData(filepath);
            setLoadedState();
            list.actionPerformed(new ActionEvent(this, 2, "Proteins tree loaded"));
            refreshStats();
        }
    }//GEN-LAST:event_loadButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        CSH.DisplayHelpFromSource csh = new CSH.DisplayHelpFromSource(MCVHelpBroker.getHelpBroker("Proteins trees file format"));
        csh.actionPerformed(new ActionEvent(this, 120, "Proteins trees file format"));
    }//GEN-LAST:event_infoButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseButton;
    private javax.swing.JLabel filenameLabel;
    private javax.swing.JButton infoButton;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadButton;
    private javax.swing.JTable proteinsStatsTable;
    // End of variables declaration//GEN-END:variables
}
