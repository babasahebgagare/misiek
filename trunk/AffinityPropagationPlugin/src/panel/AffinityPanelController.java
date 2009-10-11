package panel;

import algorithm.abs.AffinityPropagationAlgorithm.AffinityConnectingMethod;
import algorithm.abs.AffinityPropagationAlgorithm.AffinityGraphMode;
import cyto.CytoAffinityClustering;
import cyto.CytoClusterTask;
import cytoscape.CyEdge;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.task.util.TaskManager;
import giny.model.Edge;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import utils.Messenger;
import utils.MathStats;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffinityPanelController implements Serializable {

    private final static long serialVersionUID = 7526471155622776147L;
    private JTextField lambdaField = null;
    private JTextField convitsField = null;
    private JTextField nodeAttrField = null;
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
    private JCheckBox transformingCheckbox = null;
    private AffinityStatsPanelController psc = null;
    private boolean cancelDialog = false;
    public final static int MATRIX_IMPLEMENTATION = 0;
    public final static int SMART_IMPLEMENTATION = 1;
    private boolean log = false;

    public AffinityPanelController(final AffinityStatsPanelController psc) {
        this.psc = psc;
    }

    public JPanel createAffinityPanel() {
        JPanel panel = new AffinityPanel(this);
        return panel;
    }

    void doCluster() {
        CytoAffinityClustering algorithm;

        Double lambda = getLambda();
        Double preferences = getPreferences();
        Integer iterations = getIterations();
        Integer convits = getConvits();
        String nodeNameAttr = getNodeAttr();
        String edgeNameAttr = getEdgeAttr();
        Integer steps = getStepsCount();
        int implementation = getImplementation();
        boolean refine = getRefine();
        log = getLog();
        AffinityGraphMode graphMode = getGraphMode();
        AffinityConnectingMethod connectingMode = getConnectingMode();

        if (!validateValues(lambda, preferences, iterations, convits, nodeNameAttr, edgeNameAttr)) {
            return;
        }

        algorithm = new CytoAffinityClustering(connectingMode, implementation, nodeNameAttr, edgeNameAttr, lambda.doubleValue(), preferences.doubleValue(), iterations.intValue(), convits, refine, log);
        algorithm.setStepsCount(steps);
        algorithm.setGraphMode(graphMode);

        TaskManager.executeTask(new CytoClusterTask(algorithm),
                CytoClusterTask.getDefaultTaskConfig());

        Integer clusters = algorithm.getClustersNumber();
        String network = Cytoscape.getCurrentNetwork().getTitle();
        psc.addClusteringStat(network, lambda, preferences, clusters, iterations, nodeNameAttr);
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

    public void setStepsFiled(JTextField stepsFiled) {
        this.stepsFiled = stepsFiled;
    }

    private AffinityConnectingMethod getConnectingMode() {
        if (originalModeRadio.isSelected()) {
            return AffinityConnectingMethod.ORIGINAL;
        } else {
            return AffinityConnectingMethod.PRIME_ALG;
        }
    }

    private AffinityGraphMode getGraphMode() {
        if (directedModeRadio.isSelected()) {
            return AffinityGraphMode.DIRECTED;
        } else {
            return AffinityGraphMode.UNDIRECTED;
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
        return getRefineCheckBox().isSelected();
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

    private boolean validatePreferences(final Double preferences) {
        if (preferences == null) {
            return false;
        }
        if (log && preferences < 0.0) {
            Messenger.message("Preferences paremater is not valid, if you want take log.");
            return false;
        }
        return true;
    }

    private boolean validateValues(final Double lambda, final Double preferences, final Integer iterations, final Integer convits, final String nodeNameAttr, final String edgeNameAttr) {
        if (!validateLambda(lambda)) {
            Messenger.message("Lambda paremater is not valid!");
            return false;
        }
        if (!validatePreferences(preferences)) {

            return false;
        }
        if (!validateIterations(iterations)) {
            Messenger.message("Iteration number paremater is not valid!");
            return false;
        }
        if (!validateConvits(convits)) {
            Messenger.message("Convits paremater is not valid!");
            return false;
        }
        if (!validateEdgeNameAttr(edgeNameAttr)) {
            Messenger.message("Edge name paremater is not valid!");
            return false;
        }
        if (!validateNodeNameAttr(nodeNameAttr)) {
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
        convitsField.setText("3");
    }

    public void refreshEdgeAttrField() {
        edgeAttrField.removeAllItems();
        CyAttributes edgesAttributes = Cytoscape.getEdgeAttributes();
        for (String attrName : edgesAttributes.getAttributeNames()) {
            final byte cyType = edgesAttributes.getType(attrName);
            if (cyType == CyAttributes.TYPE_FLOATING) {
                edgeAttrField.addItem(attrName);
            } else if (cyType == CyAttributes.TYPE_STRING) {
                edgeAttrField.addItem(attrName);
            }
        }
    }

    public void refreshPreferences() {
        String edgeNameAttr = getEdgeAttr();
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
                    Messenger.message("Edges attribute: " + edgeNameAttr + " is not appropriate for this network.");
                    break;
                }
            }
        }
        if (probs.size() > 0) {
            Double median = MathStats.median(probs);
            setPreferences(median);
        }

    }

    private Double tryGetDoubleAttribute(CyAttributes edgesAttributes, String id, String edgeNameAttr) {
        Double sim;
        Object val = edgesAttributes.getAttribute(id, edgeNameAttr);

        try {
            sim = Double.valueOf(val.toString());
        } catch (NumberFormatException e) {
            sim = null;
        }
        return sim;
    }

    private void initIterationsField() {
        iterationsField.setValue(Integer.valueOf(10));
    }

    private void initLambdaField() {
        lambdaField.setText("0.5");
    }

    private void initNodeAttrField() {
        nodeAttrField.setText("clusterid");
    }

    private void initPreferencesField() {
        preferencesField.setText("0.2");
    }

    public void initPanelFields() {
        initLambdaField();
        initConvitsField();
        initNodeAttrField();
        refreshEdgeAttrField();
        initIterationsField();
        initPreferencesField();
    }

    public Integer getIterations() {
        try {
            return (Integer) iterationsField.getValue();
        } catch (NumberFormatException e) {
            Messenger.error(e);
            return null;
        }
    }

    public Double getPreferences() {

        try {
            return Double.valueOf(preferencesField.getText());
        } catch (NumberFormatException e) {
            Messenger.error(e);
            Messenger.message(preferencesField.getText());
            return null;
        }
    }

    public void setPreferences(final Double p) {

        preferencesField.setText(String.valueOf(p));
    }

    public Double getLambda() {
        try {
            return Double.valueOf(lambdaField.getText());
        } catch (NumberFormatException ex) {
            //Logger.getLogger(AffinityPanelController.class.getName()).log(Level.SEVERE, null, ex);
            Messenger.error(ex);
            return null;
        }
    }

    public Integer getConvits() {

        try {
            return Integer.valueOf(convitsField.getText());
        } catch (NumberFormatException e) {
            Messenger.error(e);
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
}
