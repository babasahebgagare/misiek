package mcv.ui;

import mcv.ui.dataloading.DataLoaderFrame;
import mcv.controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.dialogs.plugins.TreeNode;
import cytoscape.view.CyNetworkView;
import cytoscape.view.cytopanels.CytoPanel;
import giny.model.Edge;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.swing.SwingConstants;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.controllers.NetworksConverter;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;
import mcv.viewmodel.structs.CytoProtein;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.utils.JTreeModelSpeciesGenerator;
import mcv.utils.Messenger;

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
        PluginMenusHandle.getFamiliesColorListPanel().clean();
    }

    private void deleteTreeDataView() {
        PluginMenusHandle.getTree().setModel(null);
    }

    private void initColorListDataView() {
//        DataHandle dh = PluginDataHandle.getDataHandle();
//        Collection<String> familiesNames = dh.getFamiliesKeys();
        PluginMenusHandle.getFamiliesColorListPanel().refresh();
    }

    private void deleteDataView() {
        deleteTreeDataView();
        deleteColorListDataView();
        PluginMenusHandle.refreshUIafterDeleteData();
    }

    public void initDataView() {
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

        TreePath[] paths = PluginMenusHandle.getTree().getSelectionPaths();
        if (paths != null) {
            for (TreePath path : paths) {
                String PPINetworkID = ((TreeNode) path.getLastPathComponent()).getTitle();
                networks.add(dh.getNetworks().get(PPINetworkID));
            }
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
    public void showSelectedNetworks() {
        Collection<PPINetwork> networks = UIController.getInstance().getSelectedNetworks();
        if (networks.size() > 0) {
            NetworksConverter.convertNetworks(networks);
            PluginMenusHandle.getDoProjectionButton().setEnabled(true);
        } else {
            Messenger.message("Select networks to show in species tree.");
        }
    }

    @Override
    public void showLoadedInteractions() {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        if (cyNetworkView != Cytoscape.getNullNetworkView()) {
            CytoAbstractPPINetwork cytoNetwork = cdh.tryFindNetworkByCytoID(cyNetworkView.getIdentifier());

            InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork);
        } else {
            Messenger.message("You have to open some network view.");
        }
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

    @Override
    public void setMCVActiveTab() {
        CytoPanel panel = Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);
        int index = panel.indexOfComponent(PluginMenusHandle.getMcvPanel());
        panel.setSelectedIndex(index);
    }

}