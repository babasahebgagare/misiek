/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyto;

import algorithm.smart.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javax.swing.JPanel;

/**
 *
 * @author misiek
 */
public class CytoSmartAffinityClustering extends CytoAbstractClusterAlgorithm {

    private String nodeNameAttr;
    private String edgeNameAttr;
    private int iterations;
    private double preferences;
    private double lambda;
    private SmartPropagationAlgorithm af = new SmartPropagationAlgorithm();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();

    public CytoSmartAffinityClustering(String nodeNameAttr, String edgeNameAttr, double lambda, double preferences, int iterations) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
        this.lambda = lambda;
        this.preferences = preferences;
        this.iterations = iterations;

    }

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
        PriorityQueue<Cluster<String>> clusterprior = new PriorityQueue<Cluster<String>>();

        monitor.setStatus("Ładowanie macierzy doległości");
        setParameters();
        monitor.setStatus("Klastrowanie");

        af.init();
        //Map<String, String> clusters = af.doClusterString();
        //


        Map<String, Cluster<String>> clusters = af.doClusterString2();

        for (Cluster<String> cluster : clusters.values()) {
            clusterprior.add(cluster);
        }

        int i = 0;

        while (clusterprior.peek().size() > 1) {
            Cluster<String> cluster = clusterprior.poll();
            for (String element : cluster.getElements()) {
                nodesAttributes.setAttribute(element, nodeNameAttr, new Integer(i));
            }
            i++;
        }
        /*        for (String nodeID : clusters.keySet()) {
        String cluserid = clusters.get(nodeID);

        if (nodeID != null && cluserid != null) {
        nodesAttributes.setAttribute(nodeID, nodeNameAttr, cluserid);
        }
        }*/

        monitor.setPercentCompleted(100);
    }

    private void setParameters() {
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        af.setLambda(lambda);
        af.setIterations(iterations);

        for (CyNode node : nodes) {
            String name = node.getIdentifier();
            af.setSimilarity(name, name, preferences);
        }
        for (CyEdge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();
            if (sourceID.equals("fu_i6587") || targetID.equals("fu_i6587")) {
                System.out.println("SOURCE " + sourceID + "TARGET: " + targetID);
            }

            if (!sourceID.equals(targetID)) {
                Double prob = edgesAttributes.getDoubleAttribute(id, edgeNameAttr);
                af.setSimilarity(sourceID, targetID, Math.log(prob));
                af.setSimilarity(targetID, sourceID, Math.log(prob));
            }
        }

    }
}
