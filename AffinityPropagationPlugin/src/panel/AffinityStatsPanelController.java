package panel;

import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffinityStatsPanelController implements Serializable {

    private final static long serialVersionUID = 7526471155622776148L;
    private JTable statsTable = null;

    public JPanel createAffinityStatsPanel() {
        JPanel panel = new AffinityStatsPanel(this);
        return panel;
    }

    public void setStatsTable(JTable statsTable) {
        this.statsTable = statsTable;
    }

    public void addClusteringStat(String network, Double lambda, Double preferences, Integer clusters, Integer iterations, String clusterID) {
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.addRow(new Object[]{network, iterations, preferences, lambda, clusters, clusterID});
    }
}
