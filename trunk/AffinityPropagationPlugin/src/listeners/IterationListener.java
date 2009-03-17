package listeners;

import algorithm.smart.IterationData;
import cytoscape.task.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IterationListener implements ActionListener {

    TaskMonitor monitor;
    int n;

    public IterationListener(final TaskMonitor monitor, final int iterations) {
        this.monitor = monitor;
        this.n = iterations;
    }

    public void actionPerformed(final ActionEvent e) {
        IterationData data = (IterationData) e.getSource();
        Integer iter = data.getIter();
        monitor.setPercentCompleted(iter * 100 / n);
        monitor.setStatus("Iteration: " + iter + ", number of clusters: " + data.getClusters());
    }
}
