package ppine.controllers.interactions;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ppine.logicmodel.structs.ExpInteraction;
import ppine.viewmodel.controllers.CytoInteractionsConverter;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.logicmodel.structs.Interaction;
import ppine.logicmodel.structs.PPINetwork;
import ppine.logicmodel.structs.PPINetworkExp;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoVisualHandle;

public class DefaultInteractionsManager extends InteractionsManager {

    @Override
    public void loadInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        SpeciesTreeNode network = cytoNetwork.getNetwork();
        //System.out.println(network.getInteractions().size());
        if (network instanceof PPINetwork) {

            PPINetwork networkPPI = (PPINetwork) network;

            for (Interaction interaction : networkPPI.getInteractions().values()) {
                cdh.createCytoInteraction(interaction, cytoNetwork);
            }
        } else if (network instanceof PPINetworkExp) {
            PPINetworkExp networkExp = (PPINetworkExp) network;
            for (ExpInteraction expInteraction : networkExp.getInteractions().values()) {
                cdh.createCytoExpInteraction(expInteraction, cytoNetwork);
            }
        }
    }

    @Override
    public void deleteViewInteracions(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        cdh.deleteCytoscapeInteractions(cytoNetwork);
        cytoNetwork.deleteCytoInteractions();
    }

    @Override
    public void showInteractions(CytoAbstractPPINetwork cytoNetwork) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(cytoNetwork.getCytoID());
        CyNetwork cyNetwork = cyNetworkView.getNetwork();

        CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions(), cytoNetwork.getCytoExpInteractions());
        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
    }

    @Override
    public void loadAndShowInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        deleteViewInteracions(cytoNetwork);
        loadInteractionsFromModel(cytoNetwork);
        showInteractions(cytoNetwork);
    }
}
