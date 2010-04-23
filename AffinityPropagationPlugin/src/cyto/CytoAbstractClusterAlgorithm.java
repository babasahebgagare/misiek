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


package cyto;

import cytoscape.layout.Tunable;
import cytoscape.task.TaskMonitor;

import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

// clusterMaker imports
public abstract class CytoAbstractClusterAlgorithm implements CytoClusterAlgorithm {
    // Common class values

//    protected Integer clustersNumber;
    protected CytoClusterProperties clusterProperties = null;
    protected PropertyChangeSupport pcs;
    protected boolean debug = false;
    protected boolean canceled = false;
    protected Thread myThread = null;
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

    public abstract Integer getClustersNumber();
    

    public PropertyChangeSupport getPropertyChangeSupport() {
        return pcs;
    }

    void setMyThread(final Thread currentThread) {
        myThread = currentThread;
    }
}
