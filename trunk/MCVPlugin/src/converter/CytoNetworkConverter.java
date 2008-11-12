package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork newNet = Cytoscape.createNetwork(cytoNetwork.getID(), true);
            cytoNetwork.setCytoID(newNet.getIdentifier());
            CytoDataHandle.addNetworkIDMapping(newNet.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(newNet, cytoNetwork.getCytoProteins());
            CytoInteractionsConverter.convertCytoNetworkInteractions(newNet, cytoNetwork.getCytoInteractions());
        }
    }
}
