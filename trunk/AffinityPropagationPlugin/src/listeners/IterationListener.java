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
