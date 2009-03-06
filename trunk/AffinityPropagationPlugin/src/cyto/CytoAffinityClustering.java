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
public class CytoAffinityCluster extends CytoAbstractClusterAlgorithm {

    private String nodeNameAttr;
    private String edgeNameAttr;
    private int iterations;
    private double preferences;
    private double lambda;
    private AffinityPropagationAlgorithm af = new SmartPropagationAlgorithm();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();

    public CytoAffinityCluster(String nodeNameAttr, String edgeNameAttr, double lambda, double preferences, int iterations) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
        this.lambda = lambda;
        this.preferences = preferences;
        this.iterations = iterations;

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

        monitor.setStatus("Ładowanie macierzy doległości");

        try {
            setParameters();
        } catch (IOException ex) {
            Logger.getLogger(CytoAffinityCluster.class.getName()).log(Level.SEVERE, null, ex);
        }
        monitor.setStatus("Klastrowanie");
        createIteractionListener(monitor);

        //Map<String, String> clusters = af.doClusterString();
        //


        Map<String, Cluster<String>> clusters = af.doClusterAssoc();



        //    for (String cluster : clusters.keySet()) {

        //     System.out.println(cluster);
        //  }
        //  System.out.println("SIZE: " + clusters.size());
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
        /*        for (String nodeID : clusters.keySet()) {
        String cluserid = clusters.get(nodeID);

        if (nodeID != null && cluserid != null) {
        nodesAttributes.setAttribute(nodeID, nodeNameAttr, cluserid);
        }
        }*/

        monitor.setPercentCompleted(100);
    }

    private void setParameters() throws IOException {
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        af.setLambda(lambda);
        af.setIterations(iterations);

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

        System.out.println("N: " + (i - 1));
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
