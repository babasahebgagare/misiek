/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of Affinity Propagation
 * algorithm as Cytoscape plugin.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
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
 * APGraphClusteringPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package panel;

import cytoscape.Cytoscape;
import help.AffHelpBroker;
import java.awt.event.ActionEvent;
import javax.help.CSH;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AffinityButtonsPanel extends javax.swing.JPanel {

    private AffinityPanelController pc;
    private JPanel chooseImplPanel;
    private JPanel connModePanel;
    private JPanel graphModePanel;

    /** Creates new form JActionButton
     * @param pc
     */
    public AffinityButtonsPanel(final AffinityPanelController pc) {
        this.pc = pc;
        initComponents();
        centersAttrList.removeAllItems();
        pc.setCentersAttrList(centersAttrList);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        centersAttrList = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        jButton2.setText("jButton2");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.setName("jButton2"); // NOI18N

        setMaximumSize(new java.awt.Dimension(270, 32767));

        startButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/run.png"))); // NOI18N
        startButton.setText("Start");
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("panel/ui_properties"); // NOI18N
        startButton.setToolTipText(bundle.getString("StartButton.ToolTip")); // NOI18N
        startButton.setMargin(new java.awt.Insets(2, 0, 2, 0));
        startButton.setName("startButton"); // NOI18N
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/help.png"))); // NOI18N
        helpButton.setToolTipText(bundle.getString("HelpButton.ToolTip")); // NOI18N
        helpButton.setAlignmentY(0.0F);
        helpButton.setName("helpButton"); // NOI18N
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/refresh.png"))); // NOI18N
        jButton1.setText("Show");
        jButton1.setToolTipText("Refresh centers");
        jButton1.setAlignmentY(0.0F);
        jButton1.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        centersAttrList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        centersAttrList.setToolTipText("Select centers attribute");
        centersAttrList.setName("centersAttrList"); // NOI18N

        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText(">>Advanced options");
        jLabel1.setToolTipText("Click here to customise advanced options");
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabel1.setName("jLabel1"); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AdvancedOptionsOpen(evt);
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
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(centersAttrList, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(centersAttrList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(startButton))
                    .addComponent(helpButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        //System.out.println("------------");
        pc.doCluster();
}//GEN-LAST:event_startButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        CSH.DisplayHelpFromSource csh = new CSH.DisplayHelpFromSource(AffHelpBroker.getHelpBroker("Introduction"));
        csh.actionPerformed(new ActionEvent(this, 120, "Introduction"));
    }//GEN-LAST:event_helpButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String centersAttribute = (String) centersAttrList.getSelectedItem();
        //    System.out.println("selected:" + centersAttribute);
        pc.showCenters(centersAttribute);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AdvancedOptionsOpen(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AdvancedOptionsOpen
        JFrame frame = new JFrame("Menu of Advanced Options");

        AffinityAdvancedOptionsPanel advancedOptionsPanel = new AffinityAdvancedOptionsPanel(frame);
        advancedOptionsPanel.addToMainPanel(chooseImplPanel);
        advancedOptionsPanel.addToMainPanel(connModePanel);
  //      advancedOptionsPanel.addToMainPanel(graphModePanel);

        frame.add(advancedOptionsPanel);
        frame.pack();
        frame.setLocationRelativeTo(Cytoscape.getDesktop());
        frame.setResizable(false);
        frame.setVisible(true);
    }//GEN-LAST:event_AdvancedOptionsOpen
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox centersAttrList;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton startButton;
    // End of variables declaration//GEN-END:variables

    public void addChooseImplPanel(JPanel chooseImplPanel) {
        this.chooseImplPanel = chooseImplPanel;
    }

    public void addConnModePanel(JPanel connModePanel) {
        this.connModePanel = connModePanel;
    }

    public void addGraphModePanel(JPanel graphModePanel) {
        this.graphModePanel = graphModePanel;
    }
}
