package cyto;

import cytoscape.layout.Tunable;
import cytoscape.task.TaskMonitor;

import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

// clusterMaker imports
public abstract class CytoAbstractClusterAlgorithm implements CytoClusterAlgorithm {
    // Common class values

    protected CytoClusterProperties clusterProperties = null;
    protected PropertyChangeSupport pcs;
    protected boolean debug = false;
    boolean canceled = false;
    private Thread myThread = null;

    public CytoAbstractClusterAlgorithm() {
        pcs = new PropertyChangeSupport(new Object());
        clusterProperties = new CytoClusterProperties(getShortName());
    }

    /************************************************************************
     * Abstract inteface -- override these methods!                         *
     ***********************************************************************/
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
                Tunable.BOOLEAN, new Boolean(false),
                Tunable.NOINPUT));
    }

    public void updateSettings(boolean force) {
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
        }
    }

    protected void setCurrentThread() {
        myThread = Thread.currentThread();
    }

    /*public static double mean(Double[] vector) {
    double result = 0.0;
    for (int i = 0; i < vector.length; i++) {
    result += vector[i].doubleValue();
    }
    return (result/(double)vector.length);
    }

    // Inefficient, but simple approach to finding the median
    public static double median(Double[] vector) {
    // Clone the input vector
    Double[] vectorCopy = new Double[vector.length];
    for (int i = 0; i < vector.length; i++) {
    vectorCopy[i] = new Double(vector[i].doubleValue());
    }

    // sort it
    Arrays.sort(vectorCopy);

    // Get the median
    int mid = vector.length/2;
    if (vector.length%2 == 1) {
    return (vectorCopy[mid].doubleValue());
    }
    return ((vectorCopy[mid-1].doubleValue()+vectorCopy[mid].doubleValue()) / 2);
    }*/
    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }
}
