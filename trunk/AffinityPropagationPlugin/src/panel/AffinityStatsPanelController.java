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

import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class AffinityStatsPanelController implements Serializable {

    private final static long serialVersionUID = 7526471155622776148L;
    private JTable statsTable = null;

    public JPanel createAffinityStatsPanel() {
        JPanel panel = new AffinityStatsPanel(this);
        return panel;
    }

    public void setStatsTable(final JTable statsTable) {
        this.statsTable = statsTable;
        statsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void addClusteringStat(final String network, final Double lambda, final Double preferences, final Integer clusters, final Integer iterations, final String clusterID) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.addRow(new Object[]{network, iterations, preferences, lambda, clusters, clusterID});
    }

    void deleteSelectedRow() {
        int row = statsTable.getSelectedRow();
        if (row != -1) {
            statsTable.removeAll();
            DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
            model.removeRow(row);
        }
    }
}
