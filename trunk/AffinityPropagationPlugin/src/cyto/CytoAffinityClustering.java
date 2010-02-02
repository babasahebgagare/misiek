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

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.abs.AffinityPropagationAlgorithm.AffinityConnectingMethod;
import algorithm.matrix.MatrixPropagationAlgorithm;
import algorithm.abs.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.layout.CyLayoutAlgorithm;
import cytoscape.layout.CyLayouts;
import cytoscape.task.TaskMonitor;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.NodeShape;
import giny.view.NodeView;
import java.util.Collection;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JPanel;
import listeners.IterationListener;
import panel.AffinityPanelController;
import utils.Messenger;

/**
 *
 * @author misiek
 */
public class CytoAffinityClustering extends CytoAbstractClusterAlgorithm {

    private final double DEFAULT_WEIGHT = -0.5;
    private String nodeNameAttr;
    private String edgeNameAttr;
    private String centersNameAttr;
    private int iterations;
    private AffinityConnectingMethod connectingMode;
    private double preferences;
    private double lambda;
    private boolean refine;
    private boolean log;
    private boolean noise;
    private Integer steps = null;
    private Integer convits = null;
    private AffinityPropagationAlgorithm af = null;
    private CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
    private Map<String, Integer> nodeMapping = new TreeMap<String, Integer>();
    private Map<Integer, String> idMapping = new TreeMap<Integer, String>();
    private AffinityPanelController psc;

    public CytoAffinityClustering(final AffinityConnectingMethod connectingMode, final int implementation, final String nodeNameAttr, final String edgeNameAttr, final double lambda, final double preferences, final int iterations, final Integer convits, final boolean refine, final boolean log, final boolean noise, final String centersNameAttr) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
        this.centersNameAttr = centersNameAttr;
        this.lambda = lambda;
        this.preferences = preferences;
        this.iterations = iterations;
        this.convits = convits;
        this.connectingMode = connectingMode;
        this.refine = refine;
        this.log = log;
        this.noise = noise;
        this.af = createAlgorithm(implementation);
    }

    public void setStepsCount(Integer steps) {
        this.steps = steps;
    }

    @Override
    public String getShortName() {
        return "AP";
    }

    @Override
    public String getName() {
        return "Affinity Propagation";
    }

    @Override
    public void updateSettings() {
    }

    @Override
    public JPanel getSettingsPanel() {
        return clusterProperties.getTunablePanel();
    }

    @Override
    public void doCluster(final TaskMonitor monitor) {
        this.taskMonitor = monitor;
        super.setMyThread(Thread.currentThread());
        PriorityQueue<Cluster<Integer>> clusterprior = new PriorityQueue<Cluster<Integer>>();

        monitor.setStatus("Loading similarity matrix...");

        setParameters();
        monitor.setStatus("Clustering...");
        createIteractionListener(monitor);

        Map<Integer, Cluster<Integer>> clusters = af.doClusterAssocInt();

        if (clusters != null) {
            for (Cluster<Integer> cluster : clusters.values()) {
                clusterprior.add(cluster);
            }

            int i = 0;

            while (clusterprior.size() > 0) {
                Cluster<Integer> cluster = clusterprior.poll();
                for (Integer element : cluster.getElements()) {
                    String centerID = idMapping.get(cluster.getName());
                    String nodeID = idMapping.get(Integer.valueOf(element));
                    nodesAttributes.setAttribute(nodeID, nodeNameAttr, centerID);
                }
                i++;
            }
            for (CyLayoutAlgorithm layout : CyLayouts.getAllLayouts()) {
                layout.setLayoutAttribute(nodeNameAttr);
            }
        }
        //  clustersNumber = af.getClustersNumber();
        monitor.setPercentCompleted(100);
        psc.clusteringCompleted();
    }

    public void setAffinityPanelController(AffinityPanelController psc) {
        this.psc = psc;
    }

    private AffinityPropagationAlgorithm createAlgorithm(final int implementation) {
        if (implementation == AffinityPanelController.MATRIX_IMPLEMENTATION) {
            return new MatrixPropagationAlgorithm();
        } else {
            return new SmartPropagationAlgorithm();
        }
    }

    private Set<String> selectConnectedNodes(final List<CyEdge> edges, final List<CyNode> nodes) {
        Set<String> nodesNames = new TreeSet<String>();


        for (CyEdge edge : edges) {
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();

            nodesNames.add(targetID);
            nodesNames.add(sourceID);

        }

        return nodesNames;
    }

    private void setParameters() {
        @SuppressWarnings("unchecked")
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        @SuppressWarnings("unchecked")
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        Set<String> nodeNames = selectConnectedNodes(edges, nodes);

        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        af.setRefine(refine);
        af.setLambda(lambda);
        af.setIterations(iterations);
        af.setConvits(convits);
        af.setConnectingMode(connectingMode);
        af.setSteps(steps);
        af.setNoise(noise);
        af.setN(nodeNames.size());
        af.init();



        boolean integers = testNamesAsInteger(nodeNames);

        if (!integers) {
            System.out.println("not integers");
            int i = 0;
            for (String name : nodeNames) {
                Integer it = Integer.valueOf(i);
                idMapping.put(it, name);
                nodeMapping.put(name, it);
                //    af.setSimilarityInt(it, it, preferences);
                i++;
            }
        } else {
            System.out.println("integers");
            for (String name : nodeNames) {
                Integer it = Integer.valueOf(name);
                idMapping.put(it, name);
                nodeMapping.put(name, it);
                //        af.setSimilarityInt(it, it, preferences);
            }
        }

        for (CyEdge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();
            CyNode cyTarget = (CyNode) Cytoscape.getRootGraph().getNode(edge.getTarget().getRootGraphIndex());
            CyNode cySource = (CyNode) Cytoscape.getRootGraph().getNode(edge.getSource().getRootGraphIndex());
            Integer sourceIndex = nodeMapping.get(sourceID);
            Integer targetIndex = nodeMapping.get(targetID);

            debug = false;
            if (sourceID.equals("1") && targetID.equals("58")) {
                debug = true;
            }

            if (!sourceID.equals(targetID)) {

                Double probOrNull = tryGetDoubleAttribute(edgesAttributes, id, edgeNameAttr);
                Double sim;
                if (probOrNull != null) {
                    if (log) {
                        sim = Math.log(probOrNull);
                    } else {
                        sim = probOrNull;
                    }
                } else {
                    sim = DEFAULT_WEIGHT;
                }

                af.setSimilarityInt(sourceIndex, targetIndex, sim);
                // if (Cytoscape.getCurrentNetwork().getEdgeCount(cyTarget, cySource, true) == 0) {
                //  System.out.println("two ways: " + sourceID + " " + targetID);
                //af.setSimilarityInt(sourceIndex, targetIndex, sim);
                // af.setSimilarityInt(targetIndex, sourceIndex, sim);
                // } else {
                //    System.out.println("one way");
                // }
            }
        }

        Double val;
        if (log) {
            val = Math.log(preferences);
        } else {
            val = preferences;
        }

        af.setConstPreferences(val);
    }

    public void createIteractionListener(final TaskMonitor monitor) {
        af.addIterationListener(new IterationListener(monitor, iterations));
    }

    private Double tryGetDoubleAttribute(CyAttributes edgesAttributes, String id, String edgeNameAttr) {
        Object val = edgesAttributes.getAttribute(id, edgeNameAttr);
        if (val == null) {
            return null;
        }
        Double sim;
        try {
            sim = Double.valueOf(val.toString());
        } catch (NumberFormatException e) {
            //      Messenger.error(e);
            sim = null;
        }
        return sim;
    }

    public void showInfoAfterClustering() {
        if (!af.didConvergence()) {
            Messenger.messageInfo("Algorithm did not convergence after: " + (af.getCurrentIteration() - 1) + " iterations");
        } else {
            Messenger.messageInfo("Algorithm has convergenced after: " + af.getCurrentIteration() + " iterations");
        }
    }

    public void showCenters(final String centersAttribute) {

        CyNetworkView currentView = Cytoscape.getCurrentNetworkView();
        @SuppressWarnings("unchecked")
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        @SuppressWarnings("unchecked")
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        Set<String> nodeNames = selectConnectedNodes(edges, nodes);

        Cytoscape.getCurrentNetworkView().redrawGraph(false, true);

        for (String name : nodeNames) {
            String v = nodesAttributes.getStringAttribute(name, centersAttribute);
            if (v != null) {
                if (v.equals("1")) {
                    CyNode node = Cytoscape.getCyNode(name);
                    NodeView nodeView = currentView.getNodeView(node.getRootGraphIndex());
                    double width = nodeView.getWidth();
                    double height = nodeView.getHeight();

                    nodeView.setWidth(width + 20.0);
                    nodeView.setHeight(height + 20.0);
                    nodeView.setShape(NodeShape.ELLIPSE.getGinyShape());
                }
            }
        }
        currentView.updateView();
    }

    public void saveCenters() {
        Collection<Integer> centers = af.getCenters();
        @SuppressWarnings("unchecked")
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();

        for (CyNode node : nodes) {
            nodesAttributes.setAttribute(node.getIdentifier(), centersNameAttr, "");
        }

        for (Integer center : centers) {
            String nodeStr = idMapping.get(center);
            nodesAttributes.setAttribute(nodeStr, centersNameAttr, "1");
        }
    }

    public void showCentersAfetrClustering() {
        showCenters(centersNameAttr);
    }

    @Override
    public Integer getClustersNumber() {
        return Integer.valueOf(af.getClustersNumber());
    }

    private boolean testNamesAsInteger(Set<String> nodeNames) {
        for (String name : nodeNames) {
            try {
                Integer i = Integer.valueOf(name);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
