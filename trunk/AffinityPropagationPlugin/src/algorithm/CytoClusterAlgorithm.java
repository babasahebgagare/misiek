

package algorithm;

import cytoscape.task.TaskMonitor;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;
import model.CytoClusterProperties;


public interface CytoClusterAlgorithm {


    public static String CLUSTER_COMPUTED = "CLUSTER_COMPUTED";

    public String getShortName();

    public String getName();

    public JPanel getSettingsPanel();

    public void revertSettings();

    public void updateSettings();

    public CytoClusterProperties getSettings();

    public void halt();

    public void doCluster(TaskMonitor monitor);

    public PropertyChangeSupport getPropertyChangeSupport();
}
