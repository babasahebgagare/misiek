package logicmodel.controllers;

import viewmodel.controllers.CytoNetworkConverter;
import java.util.Collection;
import viewmodel.structs.CytoPPINetwork;
import logicmodel.structs.PPINetwork;

public class NetworksConverter {

    public static void convertNetworks(Collection<PPINetwork> networks) {
        for (PPINetwork network : networks) {
            CytoPPINetwork cytoNetwork = NetworkConverter.convertPPINetwork(network);
            CytoNetworkConverter.convertCytoNetwork(cytoNetwork);
        }
    }
}
