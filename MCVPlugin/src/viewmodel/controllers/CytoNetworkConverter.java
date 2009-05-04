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

            CyNetwork cyNetwork = createCytoscapeNetwork(cytoNetwork);
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

    private static CyNetwork createCytoscapeNetwork(CytoAbstractPPINetwork cytoNetwork) {
        CytoAbstractPPINetwork motherOrNull = cytoNetwork.tryGetMother();
        if (motherOrNull != null) {
            String parentID = cytoNetwork.getNetwork().getID();
            System.out.println("PARENT:" + parentID);
            CytoAbstractPPINetwork cytoParentOrNull = PluginDataHandle.getCytoDataHandle().getCytoNetwork(parentID);
            if (cytoParentOrNull != null) {
                CyNetwork parentNetwork = Cytoscape.getNetwork(cytoParentOrNull.getCytoID());
                System.out.println("PARENT:" + parentID);
                return Cytoscape.createNetwork(cytoNetwork.getID(), parentNetwork, true);
            } 
        }
        return Cytoscape.createNetwork(cytoNetwork.getID(), true);
    }
}
