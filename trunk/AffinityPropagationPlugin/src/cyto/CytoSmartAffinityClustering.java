/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cyto;

import algorithm.smart.SmartPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author misiek
 */
public class CytoSmartAffinityClustering extends CytoAbstractClusterAlgorithm {

    private final String nodeNameAttr;
    private final String edgeNameAttr;
    private SmartPropagationAlgorithm af = new SmartPropagationAlgorithm();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();

    public CytoSmartAffinityClustering(String nodeNameAttr, String edgeNameAttr) {
        this.nodeNameAttr = nodeNameAttr;
        this.edgeNameAttr = edgeNameAttr;
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

        monitor.setStatus("Ładowanie macierzy doległości");
        setParameters();
        monitor.setStatus("Klastrowanie");

        af.init();
        Map<String, String> clusters = af.doClusterString();

        for (String nodeID : clusters.keySet()) {
            String cluserid = clusters.get(nodeID);

            if (nodeID != null && cluserid != null) {
                nodesAttributes.setAttribute(nodeID, nodeNameAttr, cluserid);
            }
        }

        monitor.setPercentCompleted(100);
    }

    private void setParameters() {
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        af.setLambda(0.5);

        for (CyNode node : nodes) {
            String name = node.getIdentifier();
            af.setSimilarity(name, name, Math.log(0.8), 0);
        }
        for (CyEdge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();

            if (!sourceID.equals(targetID)) {
                Double prob = edgesAttributes.getDoubleAttribute(id, edgeNameAttr);
                af.setSimilarity(sourceID, targetID, Math.log(prob), 0);
            }
        }

    }
}
