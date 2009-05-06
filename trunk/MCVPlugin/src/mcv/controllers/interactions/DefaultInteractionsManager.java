package mcv.controllers.interactions;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import mcv.viewmodel.controllers.CytoInteractionsConverter;
import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;
import mcv.logicmodel.structs.Interaction;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.viewmodel.controllers.CytoVisualHandle;

public class DefaultInteractionsManager extends InteractionsManager {

    @Override
    public void loadInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        PPINetwork network = cytoNetwork.getNetwork();
        System.out.println(network.getInteractions().size());

        for (Interaction interaction : network.getInteractions().values()) {

            //     if (interaction.getProbability().doubleValue() >= treshold) {
            cdh.createCytoInteraction(interaction, cytoNetwork);
        //    }
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

        CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());
        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
    }

    @Override
    public void loadAndShowInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        deleteViewInteracions(cytoNetwork);
        loadInteractionsFromModel(cytoNetwork);
        showInteractions(cytoNetwork);
    }
}
