package mcv.controllers.interactions;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import mcv.logicmodel.structs.ExpInteraction;
import mcv.viewmodel.controllers.CytoInteractionsConverter;
import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;
import mcv.logicmodel.structs.Interaction;
import mcv.logicmodel.structs.PPINetwork;
import mcv.logicmodel.structs.PPINetworkExp;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.main.PluginDataHandle;
import mcv.viewmodel.controllers.CytoVisualHandle;

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
