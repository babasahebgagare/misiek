package cyto;

import algorithm.MatrixPropagationAlgorithm;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.TaskMonitor;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;

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
    private MatrixPropagationAlgorithm af = new MatrixPropagationAlgorithm();
    HashMap<String, Integer> nodeMapping = new HashMap<String, Integer>();
    HashMap<Integer, String> idMapping = new HashMap<Integer, String>();
    CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
    private double INF = 100000;

    public CytoAffinityClustering(String nodeNameAttr, String edgeNameAttr, double lambda, double preferences, int iterations) {
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

        monitor.setStatus("Ładowanie macierzy doległości");
        setParameters();
        monitor.setStatus("Klastrowanie");

        af.init();
        Integer[] clusters = af.doCluster();

        for (int i = 0; i < clusters.length; i++) {
            nodesAttributes.setAttribute(idMapping.get(new Integer(i)), nodeNameAttr, clusters[i]);
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
        af.setLambda(lambda);
        af.setIterations(iterations);

        double[][] sim = new double[nodes.size()][nodes.size()];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    sim[i][i] = 0.0;
                } else {
                    sim[i][j] = -INF;
                }
            }
        }

        int i = 0;
        for (CyNode node : nodes) {
            String name = node.getIdentifier();
            idMapping.put(new Integer(i), name);
            nodeMapping.put(name, new Integer(i));
            sim[i][i] = preferences;
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
                sim[sourceIndex.intValue()][targetIndex.intValue()] = Math.log(prob);
                sim[targetIndex.intValue()][sourceIndex.intValue()] = Math.log(prob);
            }
        }
        af.setSimilarities(sim);

    }

    public void createIteractionListener(TaskMonitor monitor) {
        //   af.addIterationListener(new IterationListener(monitor, iterations));
    }
}
