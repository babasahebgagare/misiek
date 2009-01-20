/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import algorithm.AffinityPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import matrix.DoubleMatrix2D;

/**
 *
 * @author misiek
 */
public class CytoAffinityClustering extends CytoAbstractClusterAlgorithm {

    private final String ATTRIBUTE_NAME = "Probability";
    private final String ATTRIBUTE_CLUSTER_ID = "cluster_id";
    private AffinityPropagationAlgorithm af = new AffinityPropagationAlgorithm();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();

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
        Integer[] clusters = af.doCluster();

        for (int i = 0; i < clusters.length; i++) {
            nodesAttributes.setAttribute(idMapping.get(new Integer(i)), ATTRIBUTE_CLUSTER_ID, clusters[i]);
        }
        monitor.setPercentCompleted(100);
    /*    double v[][] = {{-1, -2, -1, -10}, {-3, -1, -120, -22}, {-10, -50, -3, -1}, {-1, -30, -4, -1}};




    af.setN(4);
    af.setLambda(0.5);
    af.setSimilarities(v);
    af.init();
    System.out.println(af.doCluster());*/

    }

    private void setParameters() {
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

        int N = nodes.size();
        af.setN(N);
        af.setLambda(0.5);
        double[][] sim = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    sim[i][i] = 0.0;
                } else {
                    sim[i][j] = -1.0;
                }
            }
        }
        int i = 0;
        for (CyNode node : nodes) {
            nodeMapping.put(node.getIdentifier(), new Integer(i));
            idMapping.put(new Integer(i), node.getIdentifier());
            i++;
        }
        for (CyEdge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();
            Integer sourceIndex = nodeMapping.get(sourceID);
            Integer targetIndex = nodeMapping.get(targetID);
            System.out.println(sourceID);
            System.out.println(targetID);
            System.out.println(sourceIndex);
            System.out.println(targetIndex);

            Double prob = edgesAttributes.getDoubleAttribute(id, ATTRIBUTE_NAME);

            //   System.out.println(prob);

            //       System.out.flush();

            sim[sourceIndex.intValue()][targetIndex.intValue()] = prob.doubleValue() - 1;
            sim[targetIndex.intValue()][sourceIndex.intValue()] = prob.doubleValue() - 1;
        }
        af.setSimilarities(sim);

    // System.out.println("m:" + m);
    }
}
