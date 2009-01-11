package viewmodel.controllers;

import controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.controllers.CytoVisualHandle;
import viewmodel.structs.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        //  if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {
        //   CyNetwork cyNetwork = Cytoscape.createNetwork(cytoNetwork.getID(), true);
        EnvNetwork envNetwork = EnvInterface.getInstance().createNetwork(cytoNetwork.getID());

        //    cytoNetwork.setCytoID(cyNetwork.getIdentifier());
        CytoDataHandle.addNetworkIDMapping(envNetwork.getID(), cytoNetwork.getID());

        CytoProteinsConverter.convertCytoNetworkProteins(envNetwork, cytoNetwork.getCytoProteins());
        //CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

     /*   CyNetwork cyNetwork = Cytoscape.getNetwork(envNetwork.getID());
        CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
        InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork, 0.0);

        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
        CytoVisualHandle.applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
        CytoVisualHandle.setDefaultCenter(cyNetworkView);*/
    // }
    }
}
