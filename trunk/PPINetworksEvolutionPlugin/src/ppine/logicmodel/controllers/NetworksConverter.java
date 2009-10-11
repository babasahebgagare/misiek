package ppine.logicmodel.controllers;

import ppine.viewmodel.controllers.CytoNetworkConverter;
import java.util.Collection;
import ppine.viewmodel.structs.CytoPPINetwork;
import ppine.logicmodel.structs.SpeciesTreeNode;

public class NetworksConverter {

    public static void convertNetworks(Collection<SpeciesTreeNode> networks) {
        for (SpeciesTreeNode network : networks) {
            //      if (network instanceof PPINetwork) {
            CytoPPINetwork cytoNetwork = NetworkConverter.convertPPINetwork(network);
            //System.out.println("NETWORK CONVERTED");
            CytoNetworkConverter.convertCytoNetwork(cytoNetwork);
            //System.out.println("CYTO NETWORK CONVERTED");
        //   } else {
        // CytoPPINetworkExperiments cytoNetworkExp = NetworkConverter.convertPPINetworkExp(network);
        // CytoNetworkConverter.convertCytoNetwork(cytoNetworkExp);
        //   }
        }
    }
}
