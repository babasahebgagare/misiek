package viewmodel.controllers;

import controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import main.PluginDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork cyNetwork = Cytoscape.createNetwork(cytoNetwork.getID(), true);
            cytoNetwork.setCytoID(cyNetwork.getIdentifier());
            cdh.addNetworkIDMapping(cyNetwork.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(cyNetwork, cytoNetwork.getCytoProteins());
            //CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

            CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
            InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork, 0.0);

            CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
            CytoVisualHandle.applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
            CytoVisualHandle.setDefaultCenter(cyNetworkView);
        }
    }
}
