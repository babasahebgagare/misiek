/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of affinity propagation
 * algorithm as Cytoscape plugin.
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
 * APGraphClusteringPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */


package panel;

public class AffinityChooseImplPanel extends javax.swing.JPanel {

    private AffinityPanelController pc;
    /** Creates new form AffinityChooseImplPanel
     * @param pc
     */
    public AffinityChooseImplPanel(final AffinityPanelController pc) {
        this.pc = pc;
        initComponents();
        pc.setMatrixImplementation(radioMatrix);
        pc.setSmartImplementation(radioSibling);
     //   pc.initPanelFields();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radioImplementationGroup = new javax.swing.ButtonGroup();
        implementationPanel = new javax.swing.JPanel();
        radioSibling = new javax.swing.JRadioButton();
        radioMatrix = new javax.swing.JRadioButton();

        implementationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Implementation"));
        implementationPanel.setMaximumSize(new java.awt.Dimension(270, 32767));
        implementationPanel.setName("implementationPanel"); // NOI18N

        radioImplementationGroup.add(radioSibling);
        radioSibling.setSelected(true);
        radioSibling.setText("Sibling lists");
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("panel/ui_properties"); // NOI18N
        radioSibling.setToolTipText(bundle.getString("SiblingLists.Tooltip")); // NOI18N
        radioSibling.setName("radioSibling"); // NOI18N

        radioImplementationGroup.add(radioMatrix);
        radioMatrix.setText("Matrix");
        radioMatrix.setToolTipText(bundle.getString("MatrixImplementation.Tooltip")); // NOI18N
        radioMatrix.setName("radioMatrix"); // NOI18N

        javax.swing.GroupLayout implementationPanelLayout = new javax.swing.GroupLayout(implementationPanel);
        implementationPanel.setLayout(implementationPanelLayout);
        implementationPanelLayout.setHorizontalGroup(
            implementationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(implementationPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(radioMatrix)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(radioSibling)
                .addGap(46, 46, 46))
        );
        implementationPanelLayout.setVerticalGroup(
            implementationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(implementationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(radioMatrix)
                .addComponent(radioSibling))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(implementationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(implementationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel implementationPanel;
    private javax.swing.ButtonGroup radioImplementationGroup;
    private javax.swing.JRadioButton radioMatrix;
    private javax.swing.JRadioButton radioSibling;
    // End of variables declaration//GEN-END:variables

}
