package ui;

import controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.dialogs.plugins.TreeNode;
import cytoscape.view.CyNetworkView;
import giny.model.Edge;
import io.AbstractDataReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import viewmodel.controllers.CytoDataHandle;
import logicmodel.controllers.DataHandle;
import logicmodel.controllers.NetworksConverter;
import logicmodel.controllers.ProjectorInfoCalculator;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoProtein;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import utils.JTreeModelSpeciesGenerator;
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
        TreeModel newModel = JTreeModelSpeciesGenerator.generateModel();
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
        CytoAbstractPPINetwork currCytoNetwork = cdh.tryFindNetworkByCytoID(PPINetworkCytoID);
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
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(false);
        PluginMenusHandle.getDoProjectionButton().setEnabled(false);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getUpdateDataButton().setEnabled(false);
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
        if (cyNetworkView != Cytoscape.getNullNetworkView()) {
            CytoAbstractPPINetwork cytoNetwork = cdh.tryFindNetworkByCytoID(cyNetworkView.getIdentifier());

            InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork, treshold);
        }
    }

    @Override
    public void loadInteractionsForCurrentNetwork(double treshold) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();

        CytoAbstractPPINetwork cytoNetwork = cdh.tryFindNetworkByCytoID(cyNetworkView.getIdentifier());

        cdh.updateCytoInteractions(cytoNetwork, treshold);

        CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetworkView.getNetwork(), cytoNetwork.getCytoInteractions());

        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
    }

    @Override
    public void loadAllInteractions(Map<String, Double> tresholds) {
        AbstractDataReader.getInstance().readAllInteractions(tresholds);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }

    @Override
    public void loadSpeciesTreeData(String filepath) {
        PluginDataHandle.initPluginDataHandle();
        AbstractDataReader.getInstance().setFilepath(filepath);
        AbstractDataReader.getInstance().readSpecies();

        //ProjectorInfoCalculator.calculateProjectorInfo();
        initDataView();

    /* PluginMenusHandle.getLoadDataButton().setEnabled(true);
    PluginMenusHandle.getShowNetworkButton().setEnabled(true);
    PluginMenusHandle.getLoadAllInteractionsButton().setEnabled(true);
    PluginMenusHandle.getLoadDataButton().setEnabled(false);
    PluginMenusHandle.getDeleteAllDataButton().setEnabled(true);*/
    }

    @Override
    public void loadGenesTreeData(String filepath) {
        AbstractDataReader.getInstance().setFilepath(filepath);
        AbstractDataReader.getInstance().readTrees();

        ProjectorInfoCalculator.calculateProjectorInfo();
        initDataView();
    }

    @Override
    public void refreshUIafterProteinsLoading() {
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
        PluginMenusHandle.getShowNetworkButton().setEnabled(true);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
    }

    @Override
    public void refreshUIafterSpeciesLoading() {
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
    }

    @Override
    public void newData() {
        deleteData();
        DataLoaderFrame dataloaderframe = new DataLoaderFrame();
        dataloaderframe.pack();
        dataloaderframe.setVisible(true);
    }

    @Override
    public void updateData() {
        DataLoaderFrame dataloaderframe = new DataLoaderFrame();
        dataloaderframe.pack();
        dataloaderframe.setVisible(true);
    }

    @Override
    public void deleteData() {
        PluginDataHandle.refreshPluginDataHandle();
        deleteDataView();
        PluginDataHandle.getLoadingDataHandle().deleteAll();
    }
}