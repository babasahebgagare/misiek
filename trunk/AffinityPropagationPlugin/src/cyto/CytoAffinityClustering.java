package cyto;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.smart.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import listeners.IterationListener;

/**
 *
 * @author misiek
 */
public class CytoAffinityClustering extends CytoAbstractClusterAlgorithm {

    private String nodeNameAttr;
    private String edgeNameAttr;
    private int iterations;
    private double preferences;
    private double lambda;
    private Integer convits = null;
    private AffinityPropagationAlgorithm af = new SmartPropagationAlgorithm();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();

    public CytoAffinityClustering(String nodeNameAttr, String edgeNameAttr, double lambda, double preferences, int iterations) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
        this.lambda = lambda;
        this.preferences = preferences;
        this.iterations = iterations;
        this.convits = null;

    }

    public CytoAffinityClustering(String nodeNameAttr, String edgeNameAttr, double lambda, double preferences, int iterations, Integer convits) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
        this.lambda = lambda;
        this.preferences = preferences;
        this.iterations = iterations;
        this.convits = convits;
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
    public void doCluster(TaskMonitor monitor) {
        PriorityQueue<Cluster<String>> clusterprior = new PriorityQueue<Cluster<String>>();

        monitor.setStatus("Loading similarity matrix...");

        try {
            setParameters();
        } catch (IOException ex) {
            Logger.getLogger(CytoAffinityClustering.class.getName()).log(Level.SEVERE, null, ex);
        }
        monitor.setStatus("Clustering...");
        createIteractionListener(monitor);

        Map<String, Cluster<String>> clusters = af.doClusterAssoc();

        for (Cluster<String> cluster : clusters.values()) {
            clusterprior.add(cluster);
        }

        int i = 0;

        while (clusterprior.peek().size() > 1) {
            Cluster<String> cluster = clusterprior.poll();
            for (String element : cluster.getElements()) {
                String nodeID = idMapping.get(Integer.valueOf(element));
                nodesAttributes.setAttribute(nodeID, nodeNameAttr, new Integer(i));
            }
            i++;
        }

        monitor.setPercentCompleted(100);
    }

    private void setParameters() throws IOException {
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        af.setLambda(lambda);
        af.setIterations(iterations);
        af.setConvits(convits);

        int i = 0;
        af.setN(nodes.size());
        af.init();

        for (CyNode node : nodes) {
            String name = node.getIdentifier();
            idMapping.put(new Integer(i), name);
            nodeMapping.put(name, new Integer(i));
            af.setSimilarities(new Integer(i).toString(), new Integer(i).toString(), preferences);
            i++;
        }

        for (CyEdge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();
            Integer sourceIndex = nodeMapping.get(sourceID);
            Integer targetIndex = nodeMapping.get(targetID);

            if (!sourceID.equals(targetID)) {
                Double prob = edgesAttributes.getDoubleAttribute(id, edgeNameAttr);
                af.setSimilarities(sourceIndex.toString(), targetIndex.toString(), Math.log(prob));
                af.setSimilarities(targetIndex.toString(), sourceIndex.toString(), Math.log(prob));
            //                af.setSimilarity(sourceID, targetID, Math.log(prob));
            //                af.setSimilarity(targetID, sourceID, Math.log(prob));
            }
        }

    }

    public void createIteractionListener(TaskMonitor monitor) {
        af.addIterationListener(new IterationListener(monitor, iterations));
    }
}
