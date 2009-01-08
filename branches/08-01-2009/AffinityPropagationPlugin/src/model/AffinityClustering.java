/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cytoscape.task.TaskMonitor;
import javax.swing.JPanel;

/**
 *
 * @author misiek
 */
public class AffinityClustering extends AbstractClusterAlgorithm {

    @Override
    public String getShortName() {
        return "Affinity propagation";
    }

    @Override
    public String getName() {
        return "Affinity propagation";
    }

    @Override
    public void updateSettings() {
    }

    @Override
    public JPanel getSettingsPanel() {
        return clusterProperties.getTunablePanel();
    }

    @Override
    public void doCluster(TaskMonitor monitor) {
        System.out.println("dsdsdssd");
    }
}
