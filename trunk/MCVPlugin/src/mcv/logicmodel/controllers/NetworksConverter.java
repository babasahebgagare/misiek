package mcv.logicmodel.controllers;

import mcv.viewmodel.controllers.CytoNetworkConverter;
import java.util.Collection;
import mcv.viewmodel.structs.CytoPPINetwork;
import mcv.logicmodel.structs.PPINetwork;

public class NetworksConverter {

    public static void convertNetworks(Collection<PPINetwork> networks) {
        for (PPINetwork network : networks) {
            CytoPPINetwork cytoNetwork = NetworkConverter.convertPPINetwork(network);
            CytoNetworkConverter.convertCytoNetwork(cytoNetwork);
        }
    }
}
