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

public class AffinityConnModePanel extends javax.swing.JPanel {

    private AffinityPanelController pc;

    /** Creates new form AffinityConnModePanel */
    public AffinityConnModePanel(final AffinityPanelController pc) {
        this.pc = pc;
        initComponents();

        stepsCheckbox.setEnabled(true);
        stepsCheckbox.setSelected(true);
        stepsField.setEnabled(true);
        stepsField.setText("1");
        pc.setStepsFiled(stepsField);
        pc.setOriginalModeRadio(orgRadio);
        pc.setBsfModeRadio(BSFRadio);
        //pc.initPanelFields();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        weighetCentersGroup = new javax.swing.ButtonGroup();
        nearesCenterPanel = new javax.swing.JPanel();
        orgRadio = new javax.swing.JRadioButton();
        BSFRadio = new javax.swing.JRadioButton();
        stepsField = new javax.swing.JTextField();
        stepsCheckbox = new javax.swing.JCheckBox();

        nearesCenterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Search for nearest center"));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("panel/ui_properties"); // NOI18N
        nearesCenterPanel.setToolTipText(bundle.getString("SearchNearestNodeCenterPanel.ToolTip")); // NOI18N
        nearesCenterPanel.setMaximumSize(null);
        nearesCenterPanel.setName("nearesCenterPanel"); // NOI18N

        weighetCentersGroup.add(orgRadio);
        orgRadio.setSelected(true);
        orgRadio.setText("Original method");
        orgRadio.setToolTipText("For each object we look for nearest center iteratively");
        orgRadio.setName("orgRadio"); // NOI18N
        orgRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orgRadioActionPerformed(evt);
            }
        });
        orgRadio.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                orgRadioAncestorMoved(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });

        weighetCentersGroup.add(BSFRadio);
        BSFRadio.setText("Weighted BSF algorithm");
        BSFRadio.setToolTipText("Weighet BSF from a set of centers");
        BSFRadio.setName("BSFRadio"); // NOI18N
        BSFRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSFRadioActionPerformed(evt);
            }
        });

        stepsField.setEnabled(false);
        stepsField.setName("stepsField"); // NOI18N

        stepsCheckbox.setText("depth");
        stepsCheckbox.setName("stepsCheckbox"); // NOI18N
        stepsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepsCheckboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nearesCenterPanelLayout = new javax.swing.GroupLayout(nearesCenterPanel);
        nearesCenterPanel.setLayout(nearesCenterPanelLayout);
        nearesCenterPanelLayout.setHorizontalGroup(
            nearesCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nearesCenterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nearesCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nearesCenterPanelLayout.createSequentialGroup()
                        .addComponent(orgRadio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(stepsCheckbox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stepsField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BSFRadio))
                .addContainerGap())
        );
        nearesCenterPanelLayout.setVerticalGroup(
            nearesCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nearesCenterPanelLayout.createSequentialGroup()
                .addGroup(nearesCenterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orgRadio)
                    .addComponent(stepsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stepsCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BSFRadio))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nearesCenterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nearesCenterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void orgRadioAncestorMoved(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_orgRadioAncestorMoved
        // TODO add your handling code here:
}//GEN-LAST:event_orgRadioAncestorMoved

    private void checkBoxRefresh() {
        if (BSFRadio.isSelected()) {
            stepsCheckbox.setEnabled(false);
            stepsField.setEnabled(false);
        } else {
            stepsCheckbox.setEnabled(true);
            if (stepsCheckbox.isSelected()) {
                stepsField.setEnabled(true);
            }
        }
    }

    private void stepsCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepsCheckboxActionPerformed
        if (stepsCheckbox.isSelected()) {
            stepsField.setEnabled(true);
        } else {
            stepsField.setEnabled(false);
        }
    }//GEN-LAST:event_stepsCheckboxActionPerformed

    private void orgRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orgRadioActionPerformed
        checkBoxRefresh();
    }//GEN-LAST:event_orgRadioActionPerformed

    private void BSFRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSFRadioActionPerformed
        checkBoxRefresh();
}//GEN-LAST:event_BSFRadioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BSFRadio;
    private javax.swing.JPanel nearesCenterPanel;
    private javax.swing.JRadioButton orgRadio;
    private javax.swing.JCheckBox stepsCheckbox;
    private javax.swing.JTextField stepsField;
    private javax.swing.ButtonGroup weighetCentersGroup;
    // End of variables declaration//GEN-END:variables
}
