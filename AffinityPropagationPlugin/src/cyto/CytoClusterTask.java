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


package cyto;

import cytoscape.Cytoscape;

import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;

import cytoscape.task.ui.JTaskConfig;

/**
 * A wrapper for applying a cluster in a task. Use it something like
 * this:
 * <p>TaskManager.executeTask( new ClusterTask(cluster), ClusterTask.getDefaultTaskConfig() );

 */
public class CytoClusterTask implements Task {

    CytoClusterAlgorithm cluster;
    TaskMonitor monitor;

    /**
     * Creates the task.
     *
     * @param cluster The CyClusterAlgorithm to apply.
     */
    public CytoClusterTask(final CytoClusterAlgorithm cluster) {
        this.cluster = cluster;
    }

    /**
     * Sets the task monitor to be used for the cluster.
     * @param monitor
     */
    public void setTaskMonitor(final TaskMonitor monitor) {
        this.monitor = monitor;
    }

    /**
     * Run the algorithm.
     */
    public void run() {
        cluster.doCluster(monitor);
        cluster.showInfoAfterClustering();
    }

    /**
     * Halt the algorithm if the ClusterAlgorithm supports it.
     */
    public void halt() {
        cluster.halt();
    }

    /**
     * Get the "nice" title of this algorithm
     *
     * @return algorithm title
     */
    public String getTitle() {
        return "Performing " + cluster.toString();
    }

    /**
     * This method returns a default TaskConfig object.
     * @return a default JTaskConfig object.
     */
    public static JTaskConfig getDefaultTaskConfig() {
        JTaskConfig result = new JTaskConfig();

        result.displayCancelButton(true);
        result.displayCloseButton(false);
        result.displayStatus(true);
        result.displayTimeElapsed(false);
        result.setAutoDispose(true);
        result.setModal(true);
        result.setOwner(Cytoscape.getDesktop());

        return result;
    }
}
