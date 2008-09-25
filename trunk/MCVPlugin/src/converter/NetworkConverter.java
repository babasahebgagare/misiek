package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import structs.PPINetwork;

public class NetworkConverter {

    public static void convertPPINetwork(PPINetwork network) {

        if (Cytoscape.getNetwork(network.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork newNet = Cytoscape.createNetwork(network.getID(), true);
            network.setCytoID(newNet.getIdentifier());

            ProteinsConverter.convertNetworkProteins(newNet, network.getProteins().values());
            InteractionsConverter.convertNetworkInteractions(newNet, network.getInteractions().values());
        }

    }
}
