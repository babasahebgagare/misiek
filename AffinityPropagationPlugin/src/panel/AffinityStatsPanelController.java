/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of affinity propagation
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
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.VerticalLayout;

public class AffinityStatsPanelController implements Serializable {

    private final static long serialVersionUID = 7526471155622776148L;
    private JTable statsTable = null;
    private ArrayList<ClusteringHistoryData> logs = new ArrayList<ClusteringHistoryData>();

    public JPanel createAffinityStatsPanel() {
        JPanel panel = new AffinityStatsPanel(this);
        return panel;
    }

    public void setStatsTable(final JTable statsTable) {
        this.statsTable = statsTable;
        statsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void addClusteringStat(final String network, final Double lambda, final Double preferences, final Integer clusters, final Integer iterations, final Integer convits, final Integer madeIterations, final String clusterID, final String centerID, final Boolean getLog, final Boolean noise) {
        ClusteringHistoryData log = new ClusteringHistoryData();
        log.setCenterID(centerID);
        log.setClusterID(clusterID);
        log.setClusters(clusters);
        log.setConvits(convits);
        log.setIterations(iterations);
        log.setLambda(lambda);
        log.setNetwork(network);
        log.setPreferences(preferences);
        log.setMadeIterations(madeIterations);
        log.setTakeLog(getLog);
        log.setNoise(noise);

        logs.add(0, log);
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.insertRow(0, new Object[]{network, preferences, lambda, clusters});
    }

    void deleteSelectedRow() {
        int row = statsTable.getSelectedRow();
        if (row != -1) {
            statsTable.removeAll();
            DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
            model.removeRow(row);
            logs.remove(row);
        }
    }

    public void showDetails() {
        //System.out.println("logs count: " + logs.size());
        JFrame frame = new JFrame("Details");

        PanelsWithDetails panel = new PanelsWithDetails(frame);
        JTable table = panel.getTable();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // System.out.println("count: " + model.getColumnCount());
        for (ClusteringHistoryData log : logs) {
            //   System.out.println("adding");
            String strTakeLog = "NO";
            if (log.getTakeLog()) {
                strTakeLog = "YES";
            }
            String strTakeNoise = "NO";
            if (log.getNoise()) {
                strTakeNoise = "YES";
            }
            Object[] row = new Object[]{log.getNetwork(), log.getIterations(), log.getConvits(), log.getPreferences(), log.getLambda(), strTakeLog, strTakeNoise, log.getClusterID(), log.getCenterID(), log.getMadeIterations(), log.getClusters()};
            model.addRow(row);
        }

        frame.setLayout(new VerticalLayout());
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(Cytoscape.getDesktop());
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
