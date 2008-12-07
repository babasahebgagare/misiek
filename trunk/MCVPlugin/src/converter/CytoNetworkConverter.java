package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import main.CytoDataHandle;
import main.CytoVisualHandle;
import structs.model.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork cyNetwork = Cytoscape.createNetwork(cytoNetwork.getID(), true);
            cytoNetwork.setCytoID(cyNetwork.getIdentifier());
            CytoDataHandle.addNetworkIDMapping(cyNetwork.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(cyNetwork, cytoNetwork.getCytoProteins());
            CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

            CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);

            CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
            CytoVisualHandle.applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
            CytoVisualHandle.setDefaultCenter(cyNetworkView);
        }
    }
}
