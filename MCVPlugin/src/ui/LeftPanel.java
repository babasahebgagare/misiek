/*
 * LeftPanel.java
 *
 * Created on 23 wrzesień 2008, 20:25
 */
package ui;

import IO.DataReader;
import IO.DataReaderInterface;
import IO.defaultreader.DefaultDataReader;
import converter.NetworksConverter;
import cytoscape.Cytoscape;
import cytoscape.dialogs.plugins.TreeNode;
import cytoscape.view.CyNetworkView;
import java.awt.Color;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import main.CytoDataHandle;
import main.DataHandle;
import main.MenusHandle;
import projector.CytoProjector;
import projector.ProjectorInfoCalculator;
import structs.model.CytoPPINetworkProjection;
import structs.model.Family;
import structs.model.PPINetwork;
import utils.MemoLogger;
import utils.Messenger;
import visual.layout.Layouter;
import visual.renderers.MCVBackgroundRenderer;

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
        MenusHandle.setMemo(jTextArea1);
    }

    private TreeNode createRecTreeModel(PPINetwork rootNetwork) {
        if (rootNetwork == null) {
            return null;
        } else {
            TreeNode ret = new TreeNode(rootNetwork.getID());

            for (PPINetwork child : rootNetwork.getContext().getChildrenNetworks()) {
                TreeNode childNode = createRecTreeModel(child);
                if (childNode != null) {
                    ret.addChild(childNode);
                }
            }
            return ret;
        }
    }

    private Collection<PPINetwork> getSelectedNetworks() {
        Collection<PPINetwork> networks = new HashSet<PPINetwork>();

        for (TreePath path : jTree1.getSelectionPaths()) {
            String PPINetworkID = ((TreeNode) path.getLastPathComponent()).getTitle();
            networks.add(DataHandle.getNetworks().get(PPINetworkID));
        }

        return networks;
    }

    private void initColorList() {
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setListData(new String[0]);
        jList1.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    String FamilyID = (String) jList1.getSelectedValue();
                    if (FamilyID == null) {
                        return;
                    }
                    Family family = DataHandle.getFamily(FamilyID);
                    Color color = family.getColor();
                    color = JColorChooser.showDialog(null, "Wybierz kolor dla rodziny białek: " + FamilyID, color);
                    family.setColor(color);
                    jList1.clearSelection();
                }
            }
        });
    }

    private void initColorListDataView() {
        Collection<String> familiesNames = DataHandle.getFamiliesKeys();
        jList1.setListData(familiesNames.toArray());
    }

    private void initDataView() {
        initTreeDataView();
        initColorListDataView();
    }

    private void initSpeciesTree() {
        jTree1.setModel(null);
    }

    private void initTreeDataView() {
        TreeNode root = createRecTreeModel(DataHandle.getRootNetwork());
        TreeModel newModel = new DefaultTreeModel(root);
        jTree1.setModel(newModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Załaduj dane");
        jButton1.setToolTipText("Ładuje dane z plików .spy, .trees, .int");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSpinner1.setName("TresholdSpinner"); // NOI18N

        jButton7.setText("Załaduj interakcje");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton4.setText("Rozmiesc wierzcholki");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setViewportView(jTree1);

        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane3.setViewportView(jList1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setText("Rzutuj");
        jButton3.setToolTipText("Rzutuje na zaznaczone sieci w drzewku");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Wyświetl sieć");
        jButton2.setToolTipText("Wyswietla zaznaczone sieci w drzewku");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(fc);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fc.getSelectedFile();
        String filepath = file.getAbsolutePath();
        int pointPosition = filepath.lastIndexOf(".");
        filepath = filepath.substring(0, pointPosition + 1);

        String spaciespath = filepath.concat("spy");
        String treepath = filepath.concat("trees");
        String intpath = filepath.concat("int");

        DataReaderInterface dr = new DefaultDataReader();
        dr.readSpacies(spaciespath);
        MemoLogger.log("Drzewo gatunkow wczytane");

        dr.readTrees(treepath);
        MemoLogger.log("Drzewo rodzin wczytane");
        double treshold = ((Integer) jSpinner1.getValue()).doubleValue() / 100.0;

        Messenger.Message(treshold);
        dr.readInteractions(intpath, treshold);
        MemoLogger.log("Interakcje wczytane");
        //DataReader.ReadDataFromFile(file.getAbsolutePath());
        ProjectorInfoCalculator.calculateProjectorInfo();
        initDataView();
    } else {
    }

}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    Collection<PPINetwork> networks = getSelectedNetworks();
    NetworksConverter.convertNetworks(networks);

//  Cytoscape.getVisualMappingManager().setVisualStyle("MCVStyle");
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    Collection<PPINetwork> networks = getSelectedNetworks();

    CytoProjector.projectSelected(networks);
/*CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
if (projection != null && cyNetworkView != Cytoscape.getNullNetworkView()) {
Layouter.ProjectionLayout(projection, cyNetworkView);
}*/
//    Cytoscape.getVisualMappingManager().setVisualStyle("MCVStyle");

}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    /*    CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
    CytoPPINetworkProjection projection = CytoDataHandle.findNetworkProjectionByCytoID(cyNetworkView.getIdentifier());

    if (projection != null) {
    Layouter.ProjectionLayout(projection, cyNetworkView);
    }*/
    //  double treshold = ((Integer) jSpinner1.getValue()).doubleValue() / 100.0;
    // Messenger.Message(treshold);
}//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
