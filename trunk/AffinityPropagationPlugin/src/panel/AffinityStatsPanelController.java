package panel;

import java.io.Serializable;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
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
        System.out.println(row);
        if (row != -1) {
            statsTable.removeAll();
            DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
            model.removeRow(row);
        }
    }
}
