package cyto;

import cytoscape.layout.Tunable;
import cytoscape.task.TaskMonitor;

import cytoscape.task.ui.JTask;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

// clusterMaker imports
public abstract class CytoAbstractClusterAlgorithm implements CytoClusterAlgorithm {
    // Common class values

    protected Integer clustersNumber;
    protected CytoClusterProperties clusterProperties = null;
    protected PropertyChangeSupport pcs;
    protected boolean debug = false;
    protected boolean canceled = false;
    private Thread myThread = null;
    protected TaskMonitor taskMonitor = null;

    public CytoAbstractClusterAlgorithm() {
        pcs = new PropertyChangeSupport(new Object());
        clusterProperties = new CytoClusterProperties(getShortName());
    }

    /************************************************************************
     * Abstract inteface -- override these methods!                         *
     **********************************************************************
     * @return 
     */
    
    public abstract String getShortName();

    public abstract String getName();

    public abstract void updateSettings();

    public abstract JPanel getSettingsPanel();

    public abstract void doCluster(TaskMonitor monitor);

    /************************************************************************
     * Convenience routines                                                 *
     ***********************************************************************/
    protected void initializeProperties() {
        clusterProperties.add(new Tunable("debug", "Enable debugging",
                Tunable.BOOLEAN, Boolean.valueOf(false),
                Tunable.NOINPUT));
    }

    public void updateSettings(final boolean force) {
        Tunable t = clusterProperties.get("debug");
        if ((t != null) && (t.valueChanged() || force)) {
            debug = ((Boolean) t.getValue()).booleanValue();
        }
    }

    public void revertSettings() {
        clusterProperties.revertProperties();
    }

    public CytoClusterProperties getSettings() {
        return clusterProperties;
    }

    @Override
    public String toString() {
        return getName();
    }

    public void halt() {
        canceled = true;
        if (myThread != null) {
            myThread.interrupt();
            ((JTask) taskMonitor).setDone();
        }
    }

    public Integer getClustersNumber() {
        return clustersNumber;
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }

    void setMyThread(final Thread currentThread) {
        myThread = currentThread;
    }
}
