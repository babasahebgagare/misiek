package ui;

import controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.dialogs.plugins.TreeNode;
import cytoscape.view.CyNetworkView;
import giny.model.Edge;
import io.AbstractDataReader;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import viewmodel.controllers.CytoDataHandle;
import logicmodel.controllers.DataHandle;
import logicmodel.controllers.NetworksConverter;
import logicmodel.controllers.ProjectorInfoCalculator;
import viewmodel.structs.CytoAbstractPPINetwork;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import viewmodel.controllers.CytoInteractionsConverter;
import viewmodel.controllers.CytoVisualHandle;

public class DefaultUIController extends UIController {

    private Collection<Integer> connectedNodesIDs(CyNetwork cyNetwork) {
        int[] edgesID = cyNetwork.getEdgeIndicesArray();
        Collection<Integer> nodes = new HashSet<Integer>();

        for (int i = 0; i < edgesID.length; i++) {
            Edge edge = Cytoscape.getRootGraph().getEdge(edgesID[i]);
            nodes.add(Integer.valueOf(edge.getSource().getRootGraphIndex()));
            nodes.add(Integer.valueOf(edge.getTarget().getRootGraphIndex()));
        }
        return nodes;
    }

    private TreeNode createRecTreeModel(PPINetwork rootNetwork) {
        if (rootNetwork == null) {
            return null;
        } else {
            TreeNode ret = new TreeNode(rootNetwork.getID());

            for (PPINetwork child : rootNetwork.getContext().getChildrenNetworks()) {
                TreeNode childNode = createRecTreeModel(child);
                if (childNode != null) {
                    ret.addChild(childNode);
                }
            }
            return ret;
        }
    }

    private void deleteColorListDataView() {
        PluginMenusHandle.getFamiliesList().setModel(new DefaultListModel());
    }

    private void deleteTreeDataView() {
        PluginMenusHandle.getTree().setModel(null);
    }

    private void initColorListDataView() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<String> familiesNames = dh.getFamiliesKeys();
        PluginMenusHandle.getFamiliesList().setListData(familiesNames.toArray());
    }

    private void deleteDataView() {
        deleteTreeDataView();
        deleteColorListDataView();
    }

    private void initDataView() {
        initTreeDataView();
        initColorListDataView();
    }

    private void initTreeDataView() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        TreeNode root = createRecTreeModel(dh.getRootNetwork());
        TreeModel newModel = new DefaultTreeModel(root);
        PluginMenusHandle.getTree().setModel(newModel);
    }

    private Collection<Integer> unconnectedNodesIDs(CyNetwork cyNetwork) {
        int[] nodesID = cyNetwork.getNodeIndicesArray();
        int[] edgesID = cyNetwork.getEdgeIndicesArray();
        Collection<Integer> nodes = new HashSet<Integer>();
        for (int i = 0; i < nodesID.length; i++) {
            nodes.add(Integer.valueOf(nodesID[i]));
        }
        for (int i = 0; i < edgesID.length; i++) {
            Edge edge = Cytoscape.getRootGraph().getEdge(edgesID[i]);
            nodes.remove(Integer.valueOf(edge.getSource().getRootGraphIndex()));
            nodes.remove(Integer.valueOf(edge.getTarget().getRootGraphIndex()));
        }
        return nodes;
    }

    @Override
    public Collection<PPINetwork> getSelectedNetworks() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<PPINetwork> networks = new HashSet<PPINetwork>();

        for (TreePath path : PluginMenusHandle.getTree().getSelectionPaths()) {
            String PPINetworkID = ((TreeNode) path.getLastPathComponent()).getTitle();
            networks.add(dh.getNetworks().get(PPINetworkID));
        }

        return networks;
    }

    @Override
    public Collection<CytoProtein> getSelectedProteins(CyNetwork cyNetwork) {
        @SuppressWarnings("unchecked")
        Set<CyNode> cyNodes = cyNetwork.getSelectedNodes();
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();


        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        CytoAbstractPPINetwork currCytoNetwork = cdh.findNetworkByCytoID(PPINetworkCytoID);
        Collection<CytoProtein> ret = new HashSet<CytoProtein>();

        if (currCytoNetwork != null) {
            for (CyNode node : cyNodes) {
                ret.add(currCytoNetwork.getCytoProtein(node.getIdentifier()));
            }
        }
        return ret;
    }

    @Override
    public void selectConnectedNodes(CyNetwork cyNetwork) {
        Collection<Integer> nodesID = connectedNodesIDs(cyNetwork);

        for (Integer nodeID : nodesID) {
            cyNetwork.setSelectedNodeState(Cytoscape.getRootGraph().getNode(nodeID), true);
        }

        Cytoscape.getCurrentNetworkView().updateView();
    }

    @Override
    public void selectUnconnectedNodes(CyNetwork cyNetwork) {
        Collection<Integer> nodesID = connectedNodesIDs(cyNetwork);
        @SuppressWarnings("unchecked")
        Collection<Integer> selected = new HashSet<Integer>(cyNetwork.getSelectedNodes());

        cyNetwork.unselectAllNodes();

        selected.retainAll(nodesID);

        for (Integer nodeID : selected) {
            cyNetwork.setSelectedNodeState(Cytoscape.getRootGraph().getNode(nodeID), true);
        }
        Cytoscape.getCurrentNetworkView().updateView();
    }

    @Override
    public void unselectUnConnectedNodes(CyNetwork cyNetwork) {
        Collection<Integer> unconnected = unconnectedNodesIDs(cyNetwork);

        for (Integer sel : unconnected) {
            cyNetwork.setSelectedNodeState(Cytoscape.getRootGraph().getNode(sel), false);
        }
        Cytoscape.getCurrentNetworkView().updateView();
    }

    @Override
    public void initButtonsState() {
        PluginMenusHandle.getShowNetworkButton().setEnabled(false);
        PluginMenusHandle.getDoProjectionButton().setEnabled(false);
        PluginMenusHandle.getLoadAllInteractionsButton().setEnabled(false);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(false);
        PluginMenusHandle.getDoProjectionButton().setEnabled(false);
        PluginMenusHandle.getLoadDataButton().setEnabled(true);
        PluginMenusHandle.getLoadInteractionsForNetworkButton().setEnabled(false);
        PluginMenusHandle.getDeleteAllDataButton().setEnabled(false);
    }

    @Override
    public void loadData() {
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(fc);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filepath = file.getAbsolutePath();
            int pointPosition = filepath.lastIndexOf(".");
            filepath = filepath.substring(0, pointPosition + 1);

            PluginDataHandle.initPluginDataHandle();
            AbstractDataReader.getInstance().setFilepath(filepath);
            AbstractDataReader.getInstance().readSpacies();
            AbstractDataReader.getInstance().readTrees();

            ProjectorInfoCalculator.calculateProjectorInfo();
            initDataView();

            PluginMenusHandle.getLoadDataButton().setEnabled(true);
            PluginMenusHandle.getShowNetworkButton().setEnabled(true);
            PluginMenusHandle.getLoadAllInteractionsButton().setEnabled(true);
            PluginMenusHandle.getLoadDataButton().setEnabled(false);
            PluginMenusHandle.getDeleteAllDataButton().setEnabled(true);
        }

    }

    @Override
    public void showSelectedNetworks() {
        Collection<PPINetwork> networks = UIController.getInstance().getSelectedNetworks();
        NetworksConverter.convertNetworks(networks);

        PluginMenusHandle.getDoProjectionButton().setEnabled(true);
    }

    @Override
    public void loadAllInteractions(double treshold) {
        AbstractDataReader.getInstance().readAllInteractions(treshold);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }

    @Override
    public void showLoadedInteractions(double treshold) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        CytoAbstractPPINetwork cytoNetwork = cdh.findNetworkByCytoID(cyNetworkView.getIdentifier());

        InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork, treshold);
    }

    @Override
    public void loadInteractionsForNetwork(double treshold) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();

        CytoAbstractPPINetwork cytoNetwork = cdh.findNetworkByCytoID(cyNetworkView.getIdentifier());

        cdh.updateCytoInteractions(cytoNetwork, treshold);

        CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetworkView.getNetwork(), cytoNetwork.getCytoInteractions());

        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
    }

    @Override
    public void deleteAllData() {
        PluginDataHandle.refreshPluginDataHandle();
        deleteDataView();
        PluginMenusHandle.getLoadDataButton().setEnabled(true);
        PluginMenusHandle.getDeleteAllDataButton().setEnabled(false);
    }
}