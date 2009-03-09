package panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffinityStatsPanelController {

    private JTable statsTable = null;

    public JPanel createAffinityStatsPanel() {
        JPanel panel = new AffinityStatsPanel(this);
        return panel;
    }

    public void setStatsTable(JTable statsTable) {
        this.statsTable = statsTable;
    }

    public void addClusteringStat(Double lambda, Double preferences, Integer clusters, Integer iterations, String clusterID) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.addRow(new Object[]{iterations, preferences, lambda, clusters, clusterID});
    }
}
