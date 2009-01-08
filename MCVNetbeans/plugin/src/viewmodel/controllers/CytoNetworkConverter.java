package viewmodel.controllers;

import controllers.interactions.InteractionsManager;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.controllers.CytoVisualHandle;
import viewmodel.structs.CytoAbstractPPINetwork;

public class CytoNetworkConverter {
/*
    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        //if (EnvInterface.getInstance().existNetwork(cytoNetwork.getCytoID())) {
        EnvNetwork envNetwork = EnvInterface.getInstance().createNetwork(cytoNetwork.getID());
        cytoNetwork.setCytoID(envNetwork.getID());
        CytoDataHandle.addNetworkIDMapping(cyNetwork.getIdentifier(), cytoNetwork.getID());

        CytoProteinsConverter.convertCytoNetworkProteins(cyNetwork, cytoNetwork.getCytoProteins());
        //CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

        CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
        InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork, 0.0);

        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
        CytoVisualHandle.applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
        CytoVisualHandle.setDefaultCenter(cyNetworkView);
    }
    // }
 */
}
