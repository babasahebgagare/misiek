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
package panel;

import cyto.CytoAffinityClustering;
import cyto.CytoClusterTask;
import cytoscape.task.util.TaskManager;
import giny.model.Edge;
import giny.view.EdgeView;
import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import utils.MathStats;
import algorithm.abs.AffinityPropagationAlgorithm.AffinityConnectingMethod;
import algorithm.abs.AffinityPropagationAlgorithm.AffinityGraphMode;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.NodeShape;
import giny.view.NodeView;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import utils.Messenger;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffinityPanelController implements Serializable {

    private final static long serialVersionUID = 7526471155622776147L;
    private final String DEFAULT_PREFERENCE = "0.500";
    private final String DEFAULT_CLUSTER_ID = "cluster_id";
    private final String DEFAULT_CENTERS_ID = "center_id";
    private final String DEFAULT_LAMBDA = "0.5";
    private final String CONVITS_DEFAULT = "50";
    private final int ITERATIONS_DEFAULT = 500;
    private final String DEFAULT = "DEFAULT";
    private JTextField lambdaField = null;
    private JTextField convitsField = null;
    private JTextField nodeAttrField = null;
    private JTextField centersAttrField = null;
    private JComboBox edgeAttrField = null;
    private JSpinner iterationsField = null;
    private JTextField preferencesField = null;
    private JTextField stepsFiled = null;
    private JRadioButton matrixImplementation = null;
    private JRadioButton smartImplementation = null;
    private JRadioButton originalModeRadio = null;
    private JRadioButton bsfModeRadio = null;
    private JRadioButton directedModeRadio = null;
    private JCheckBox refineCheckBox = null;
    private JCheckBox noiseCheckBox = null;
    private JCheckBox transformingCheckbox = null;
    private JComboBox centersAttrList = null;
    private AffinityStatsPanelController psc = null;
    private boolean cancelDialog = false;
    public final static int MATRIX_IMPLEMENTATION = 0;
    public final static int SMART_IMPLEMENTATION = 1;
    private boolean log = false;
    private Collection<String> centersAttr = new TreeSet<String>();

    public AffinityPanelController(final AffinityStatsPanelController psc) {
        this.psc = psc;
    }

    public JPanel createAffinityPanel() {
        JPanel panel = new AffinityPanel(this);
        return panel;
    }

    public void addCentersAttribute(String attr) {
        if (centersAttr.contains(attr)) {
            centersAttrList.setSelectedItem(attr);
        } else {
            centersAttr.add(attr);
            centersAttrList.addItem(attr);
            centersAttrList.setSelectedItem(attr);
        }
        //centersAttr.add(attr);
        //refreshCentersAttrList();
    }

    void doCluster() {

        AffinityGraphMode graphMode = getGraphMode();
        Double lambda = getLambda();
        Double preferences = getPreferences();
        Integer iterations = getIterations();
        Integer convits = getConvits();
        String nodeNameAttr = getNodeAttr();
        String edgeNameAttr = getEdgeAttr();
        String centersNameAttr = getCentersAttr();
        Integer steps = getStepsCount();
        int implementation = getImplementation();
        boolean refine = getRefine();
        boolean noise = getNoise();
        log = getLog();
        AffinityConnectingMethod connectingMode = getConnectingMode();

        if (!validateNetwork()) {
            return;
        }

        if (!validateValues(lambda, preferences, iterations, convits, nodeNameAttr, edgeNameAttr, centersNameAttr)) {
            return;
        }

        if (!edgeNameAttr.equals(DEFAULT)) {
            if (!validateSim(edgeNameAttr, log)) {
                return;
            }
        }

        CytoAffinityClustering algorithm = new CytoAffinityClustering(connectingMode, implementation, nodeNameAttr, edgeNameAttr, lambda.doubleValue(), preferences, iterations.intValue(), convits, refine, log, noise, centersNameAttr);
        algorithm.setStepsCount(steps);
        algorithm.setGraphMode(graphMode);
        algorithm.setAffinityPanelController(this);
        CytoClusterTask cytoAlgorithmTask = new CytoClusterTask(algorithm);

        TaskManager.executeTask(cytoAlgorithmTask,
                CytoClusterTask.getDefaultTaskConfig());
    }

    public void clusteringCompleted(Integer clustersCount, Integer madeIter) {
        Double lambda = getLambda();
        Double preferences = getPreferences();
        Integer iterations = getIterations();
        String nodeNameAttr = getNodeAttr();
        String centersNameAttr = getCentersAttr();
        Integer clusters = clustersCount;
        Integer convits = getConvits();
        Integer madeIterations = madeIter;
        Boolean takeLog = getLog();
        Boolean noise = getNoise();

        String network = Cytoscape.getCurrentNetwork().getTitle();
        psc.addClusteringStat(network, lambda, preferences, clusters, iterations, convits, madeIterations, nodeNameAttr, centersNameAttr, takeLog, noise);

        /* String centerAttr = getCentersAttr();
        centersAttr.add(centerAttr);
        refreshCentersAttrList();*/
    }

    public JCheckBox getTransformingCheckbox() {
        return transformingCheckbox;
    }

    void setDirecedGraphRadio(JRadioButton directedModeRadio) {
        this.directedModeRadio = directedModeRadio;
    }

    void setLogCheckBox(JCheckBox transformingCheckbox) {
        this.transformingCheckbox = transformingCheckbox;
    }

    void setRefineCheckBox(JCheckBox refineCheckBox) {
        this.refineCheckBox = refineCheckBox;
    }

    public JCheckBox getRefineCheckBox() {
        return refineCheckBox;
    }

    public void setBsfModeRadio(JRadioButton bsfModeRadio) {
        this.bsfModeRadio = bsfModeRadio;
    }

    public void setOriginalModeRadio(JRadioButton originalModeRadio) {
        this.originalModeRadio = originalModeRadio;
    }

    public JComboBox getCentersAttrList() {
        return centersAttrList;
    }

    public void setCentersAttrList(JComboBox centersAttrList) {
        this.centersAttrList = centersAttrList;
    }

    public void setStepsFiled(JTextField stepsFiled) {
        this.stepsFiled = stepsFiled;
    }

    public Set<String> selectConnectedNodes(final List<CyEdge> edges, final List<CyNode> nodes) {
        Set<String> nodesNames = new TreeSet<String>();

        for (CyEdge edge : edges) {
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();

            if (!sourceID.equals(targetID)) {
                if (!nodesNames.contains(sourceID)) {
                    nodesNames.add(sourceID);
                }
                if (!nodesNames.contains(targetID)) {
                    nodesNames.add(targetID);
                }
            }
        }

        return nodesNames;
    }

    public void showCenters(final String centersAttribute) {
        final CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
        if (centersAttribute != null) {
      //      SwingUtilities.invokeLater(new Runnable() {

       //         public void run() {
                    Cytoscape.getCurrentNetworkView().redrawGraph(false, true);
                    CyNetworkView currentView = Cytoscape.getCurrentNetworkView();
                    @SuppressWarnings("unchecked")
                    List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
                    @SuppressWarnings("unchecked")
                    List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();
                    Set<String> nodeNames = selectConnectedNodes(edges, nodes);

                    for (String name : nodeNames) {
                        String v = nodesAttributes.getStringAttribute(name, centersAttribute);
                        if (name.equals(v)) {
                            CyNode node = Cytoscape.getCyNode(name);
                            NodeView nodeView = currentView.getNodeView(node.getRootGraphIndex());
                            if (nodeView != null) {
                                double width = nodeView.getWidth();
                                double height = nodeView.getHeight();

                                nodeView.setWidth(width + 20.0);
                                nodeView.setHeight(height + 20.0);
                                nodeView.setShape(NodeShape.ELLIPSE.getGinyShape());
                                nodeView.setSelected(true);
                            }
                        } else {
                            CyNode node = Cytoscape.getCyNode(name);
                            NodeView nodeView = currentView.getNodeView(node.getRootGraphIndex());
                            if (nodeView != null) {
                                nodeView.setSelected(false);
                            }
                        }
                    }
                    currentView.updateView();
                }
         //   });
       // }
    }

    public String getCentersAttr() {
        return centersAttrField.getText();
    }

    private AffinityConnectingMethod getConnectingMode() {
        if (originalModeRadio.isSelected()) {
            return AffinityConnectingMethod.ORIGINAL;
        } else {
            return AffinityConnectingMethod.PRIME_ALG;
        }
    }

    private int getImplementation() {
        if (matrixImplementation.isSelected()) {
            return AffinityPanelController.MATRIX_IMPLEMENTATION;
        } else {
            return AffinityPanelController.SMART_IMPLEMENTATION;
        }
    }

    private boolean getLog() {
        return transformingCheckbox.isSelected();
    }

    private boolean getRefine() {
        return true;
    }

    private boolean getNoise() {
        //return false;
        return getNoiseCheckBox().isSelected();
    }

    public JTextField getCentersAttrField() {
        return centersAttrField;
    }

    public void setCentersAttrField(JTextField centersAttrField) {
        this.centersAttrField = centersAttrField;
    }

    private Integer getStepsCount() {
        Integer steps;
        try {
            if (stepsFiled.isEnabled()) {
                steps = Integer.valueOf(stepsFiled.getText());
            } else {
                steps = null;
            }
        } catch (NumberFormatException e) {
            steps = null;
        }
        return steps;
    }

    private boolean validateConvits(final Integer convits) {
        return true;
    }

    private boolean validateEdgeNameAttr(final String edgeNameAttr) {
        return (edgeNameAttr != null && !edgeNameAttr.equals(""));
    }

    private boolean validateIterations(final Integer iterations) {
        return (iterations != null && iterations > 0);
    }

    private boolean validateLambda(final Double lambda) {
        return (lambda != null && lambda < 1.0 && lambda > 0.0);
    }

    private boolean validateNodeNameAttr(final String nodeNameAttr) {
        if (nodeNameAttr == null || nodeNameAttr.equals("")) {
            return false;
        }
        String[] names = Cytoscape.getNodeAttributes().getAttributeNames();

        boolean exist = false;
        for (String name : names) {
            if (name.equals(nodeNameAttr)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            int ret = Messenger.confirmWarning("Clustering node name attribute already exist, overwrite?");

            if (ret == JOptionPane.OK_OPTION) {
                return true;
            } else {
                cancelDialog = true;
                return false;
            }
        }

        return true;
    }

    private boolean validateCentersNameAttr(final String centersNameAttr) {
        if (centersNameAttr == null || centersNameAttr.equals("")) {
            return false;
        }
        String[] names = Cytoscape.getNodeAttributes().getAttributeNames();

        boolean exist = false;
        for (String name : names) {
            if (name.equals(centersNameAttr)) {
                exist = true;
                break;
            }
        }

        if (exist) {
            int ret = Messenger.confirmWarning("Clustering centers name attribute already exist, overwrite?");

            if (ret == JOptionPane.OK_OPTION) {
                return true;
            } else {
                cancelDialog = true;
                return false;
            }
        }

        return true;
    }

    private boolean validatePreferences(final Double preferences) {
        if (preferences == null) {
            return false;
        }
        if (log && preferences < 0.0) {
            Messenger.message("You have selected 'take log' option and chose negative preferences paremater. Uncheck 'take log' option or change preferences.");
            return false;
        }
        return true;
    }

    private boolean validateValues(final Double lambda, final Double preferences, final Integer iterations, final Integer convits, final String nodeNameAttr, final String edgeNameAttr, final String centersNameAttr) {
        if (!validateLambda(lambda)) {
            Messenger.message("Lambda paremater has to be between 0 and 1.");
            return false;
        }
        if (!validatePreferences(preferences)) {
            //Messenger.message("Preferences paremater has to be a real number.");
            return false;
        }
        if (!validateIterations(iterations)) {
            Messenger.message("Iteration number has to be a integer greater equal 1.");
            return false;
        }
        if (!validateConvits(convits)) {
            Messenger.message("Convits paremater has to be empty or integer.");
            return false;
        }
        if (!validateEdgeNameAttr(edgeNameAttr)) {
            Messenger.message("Edge name paremater is not valid.");
            return false;
        }
        if (!validateNodeNameAttr(nodeNameAttr)) {
            if (cancelDialog) {
                cancelDialog = false;
            } else {
                Messenger.message("Node name paremater is not valid");
            }
            return false;
        }
        if (!validateCentersNameAttr(centersNameAttr)) {
            if (cancelDialog) {
                cancelDialog = false;
            } else {
                Messenger.message("Node name paremater is not valid!");
            }
            return false;
        }

        return true;
    }

    private void initConvitsField() {
        convitsField.setText(CONVITS_DEFAULT);
    }

    public void refreshEdgeAttrField() {
        edgeAttrField.removeAllItems();
        edgeAttrField.addItem(DEFAULT);
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();
        for (String attrName : edgesAttributes.getAttributeNames()) {
            final byte cyType = edgesAttributes.getType(attrName);
            if (cyType == CyAttributes.TYPE_FLOATING) {
                CyNetworkView view = Cytoscape.getCurrentNetworkView();
                if (view.getEdgeViewsList().size() > 0) {
                    EdgeView edgeView = (EdgeView) view.getEdgeViewsIterator().next();
                    Edge edge = edgeView.getEdge();
                    Double attr = edgesAttributes.getDoubleAttribute(edge.getIdentifier(), attrName);
                    if (attr != null) {
                        edgeAttrField.addItem(attrName);
                    }
                }
            } else if (cyType == CyAttributes.TYPE_STRING) {
                CyNetworkView view = Cytoscape.getCurrentNetworkView();
                if (view.getEdgeViewsList().size() > 0) {
                    EdgeView edgeView = (EdgeView) view.getEdgeViewsIterator().next();
                    Edge edge = edgeView.getEdge();
                    String attr = edgesAttributes.getStringAttribute(edge.getIdentifier(), attrName);
                    try {
                        if (attr != null) {
                            Double val = Double.parseDouble(attr);
                            edgeAttrField.addItem(attrName);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        }
    }

    public void refreshPreferences() {
        String edgeNameAttr = getEdgeAttr();
        if (edgeNameAttr.equals(DEFAULT)) {
            setPreferences(Double.parseDouble(DEFAULT_PREFERENCE));
            return;
        }
        if (!validateEdgeNameAttr(edgeNameAttr)) {
            return;
        }
        @SuppressWarnings("unchecked")
        List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();
        Vector<Double> probs = new Vector<Double>();

        for (Edge edge : edges) {

            String id = edge.getIdentifier();
            String sourceID = edge.getSource().getIdentifier();
            String targetID = edge.getTarget().getIdentifier();

            if (!sourceID.equals(targetID)) {
                try {
                    Double prob = tryGetDoubleAttribute(edgesAttributes, id, edgeNameAttr);
                    if (prob != null) {
                        //System.out.println("adding to prob: " + prob);
                        probs.add(prob);
                    }
                } catch (NullPointerException ne) {
                    if (!edgeNameAttr.equals(DEFAULT)) {
                        Messenger.message("Edges attribute: " + edgeNameAttr + " is not appropriate for this network.");
                        break;
                    }
                }
            }
        }
        if (probs.size() > 0) {
            Double median = MathStats.median(probs);
            setPreferences(median);
        }

    }

    public static String getFormattedValue(Double value) {

        long val = Math.round(value * 1000);
        Double valHelp = Double.valueOf(val) / 1000;
        String pom = String.valueOf(valHelp);
        int dot = pom.indexOf(".");
        String pocz = pom.substring(0, dot);
        String kon = pom.substring(dot + 1);
        if (kon.length() >= 3) {
            kon = kon.substring(0, 3);
        } else if (kon.length() == 0) {
            kon = kon.concat("000");
        } else if (kon.length() == 1) {
            kon = kon.concat("00");
        } else if (kon.length() == 2) {
            kon = kon.concat("0");
        }

        String res = pocz.concat(".").concat(kon);

        return res;

    }

    private Double tryGetDoubleAttribute(CyAttributes edgesAttributes, String id, String edgeNameAttr) {
        Double sim;
        Object val = edgesAttributes.getAttribute(id, edgeNameAttr);

        try {
            sim = Double.valueOf(val.toString());
        } catch (NullPointerException e) {
            sim = null;
        } catch (NumberFormatException e) {
            sim = null;
        }

        return sim;
    }

    private void initIterationsField() {
        iterationsField.setValue(Integer.valueOf(ITERATIONS_DEFAULT));
    }

    private void initLambdaField() {
        lambdaField.setText(DEFAULT_LAMBDA);
    }

    private void initNodeAttrField() {
        nodeAttrField.setText(DEFAULT_CLUSTER_ID);
    }

    private void initPreferencesField() {
        preferencesField.setText(DEFAULT_PREFERENCE);
    }

    private void initCentersNameAttr() {
        centersAttrField.setText(DEFAULT_CENTERS_ID);
    }

    public void initPanelFields() {
        initLambdaField();
        initConvitsField();
        initNodeAttrField();
        refreshEdgeAttrField();
        initIterationsField();
        initPreferencesField();
        initCentersNameAttr();
    }

    public Integer getIterations() {
        try {
            return (Integer) iterationsField.getValue();
        } catch (NumberFormatException e) {
            //   Messenger.error(e);
            return null;
        }
    }

    public Double getPreferences() {

        try {
            return Double.valueOf(preferencesField.getText());
        } catch (NumberFormatException e) {
            //     Messenger.error(e);
            //     Messenger.message(preferencesField.getText());
            return null;
        }
    }

    public void setPreferences(final Double p) {

        String pStr = getFormattedValue(p);
        preferencesField.setText(pStr);
    }

    public Double getLambda() {
        try {
            return Double.valueOf(lambdaField.getText());
        } catch (NumberFormatException ex) {
            //Logger.getLogger(AffinityPanelController.class.getName()).log(Level.SEVERE, null, ex);
            //  Messenger.error(ex);
            return null;
        }
    }

    public Integer getConvits() {

        try {
            return Integer.valueOf(convitsField.getText());
        } catch (NumberFormatException e) {
            //      Messenger.error(e);
            return null;
        }
    }

    public String getNodeAttr() {
        return nodeAttrField.getText();
    }

    public String getEdgeAttr() {
        return (String) edgeAttrField.getSelectedItem();
    }

    public JTextField getCovitsField() {
        return convitsField;
    }

    public void setCovitsField(final JTextField covitsField) {
        this.convitsField = covitsField;
    }

    public JTextField getConvitsField() {
        return convitsField;
    }

    public void setConvitsField(final JTextField convitsField) {
        this.convitsField = convitsField;
    }

    public JComboBox getEdgeAttrField() {
        return edgeAttrField;
    }

    public void setEdgeAttrField(final JComboBox edgeAttrField) {
        this.edgeAttrField = edgeAttrField;
    }

    public JSpinner getIterationsField() {
        return iterationsField;
    }

    public void setIterationsField(final JSpinner iterationsField) {
        this.iterationsField = iterationsField;
    }

    public JTextField getLambdaField() {
        return lambdaField;
    }

    public void setLambdaField(final JTextField lambdaField) {
        this.lambdaField = lambdaField;
    }

    public JTextField getNodeAttrField() {
        return nodeAttrField;
    }

    public void setNodeAttrField(final JTextField nodeAttr) {
        this.nodeAttrField = nodeAttr;
    }

    public JTextField getPreferencesField() {
        return preferencesField;
    }

    public void setPreferencesField(final JTextField preferencesField) {
        this.preferencesField = preferencesField;
    }

    public JRadioButton getMatrixImplementation() {
        return matrixImplementation;
    }

    public void setMatrixImplementation(JRadioButton matrixImplementation) {
        this.matrixImplementation = matrixImplementation;
    }

    public JRadioButton getSmartImplementation() {
        return smartImplementation;
    }

    public void setSmartImplementation(JRadioButton smartImplementation) {
        this.smartImplementation = smartImplementation;
    }

    void setNoiseCheckBox(JCheckBox noiseCheckbox) {
        this.noiseCheckBox = noiseCheckbox;
    }

    private JCheckBox getNoiseCheckBox() {
        return noiseCheckBox;
    }

    private AffinityGraphMode getGraphMode() {
        if (directedModeRadio.isSelected()) {
            return AffinityGraphMode.DIRECTED;
        } else {
            return AffinityGraphMode.UNDIRECTED;
        }
    }

    private boolean validateSim(String edgeNameAttr, boolean takelog) {
        if (takelog) {
            @SuppressWarnings("unchecked")
            List<CyEdge> edges = Cytoscape.getCurrentNetwork().edgesList();
            CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();

            for (CyEdge edge : edges) {
                String id = edge.getIdentifier();
                Double probOrNull = tryGetDoubleAttribute(edgesAttributes, id, edgeNameAttr);
                if (probOrNull != null) {
                    if (probOrNull < 0) {
                        Messenger.message("You have selected 'take log' option and for edge: " + edge.getIdentifier() + " similarity is negative. Unckeck 'take log' option or use other attribute for weight on edges");
                        return false;
                    }
                }
            }

        }
        return true;
    }

    private boolean validateNetwork() {
        if (Cytoscape.getCurrentNetwork() == Cytoscape.getNullNetwork()) {
            Messenger.message("You have to select some network");
            return false;
        }

        if (Cytoscape.getCurrentNetworkView() == Cytoscape.getNullNetworkView()) {
            Messenger.message("You have to select some network view");
            return false;
        }
        return true;
    }
}
