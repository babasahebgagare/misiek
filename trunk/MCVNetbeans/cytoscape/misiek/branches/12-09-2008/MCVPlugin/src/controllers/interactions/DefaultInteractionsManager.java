package controllers.interactions;

import converter.CytoInteractionsConverter;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import main.CytoDataHandle;
import main.CytoVisualHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.Interaction;
import structs.model.PPINetwork;

public class DefaultInteractionsManager extends InteractionsManager {

    @Override
    public void loadInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork, double treshold) {

        PPINetwork network = cytoNetwork.getNetwork();

        for (Interaction interaction : network.getInteractions().values()) {

            if (interaction.getProbability().doubleValue() >= treshold) {
                CytoDataHandle.createCytoInteraction(interaction, cytoNetwork);
            }
        }
    }

    @Override
    public void deleteViewInteracions(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle.deleteCytoscapeInteractions(cytoNetwork);
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
    public void loadAndShowInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork, double treshold) {
        deleteViewInteracions(cytoNetwork);
        loadInteractionsFromModel(cytoNetwork, treshold);
        showInteractions(cytoNetwork);
    }
}
