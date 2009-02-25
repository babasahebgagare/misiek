package listeners;

import algorithm.smart.IterationData;
import cytoscape.task.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IterationListener implements ActionListener {

    TaskMonitor monitor;
    int n;

    public IterationListener(TaskMonitor monitor, int iterations) {
        this.monitor = monitor;
        this.n = iterations;
    }

    public void actionPerformed(ActionEvent e) {
        IterationData data = (IterationData) e.getSource();
        Integer iter = data.getIter();
        monitor.setPercentCompleted(iter * 100 / n);
        monitor.setStatus("Iteration: " + iter + ", number of clusters: " + data.getClusters());
    }
}
