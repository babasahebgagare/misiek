package mcv.logicmodel.controllers;

import mcv.viewmodel.controllers.CytoNetworkConverter;
import java.util.Collection;
import mcv.logicmodel.structs.PPINetwork;
import mcv.viewmodel.structs.CytoPPINetwork;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.viewmodel.structs.CytoPPINetworkExperiments;

public class NetworksConverter {

    public static void convertNetworks(Collection<SpeciesTreeNode> networks) {
        for (SpeciesTreeNode network : networks) {
            if (network instanceof PPINetwork) {
                CytoPPINetwork cytoNetwork = NetworkConverter.convertPPINetwork(network);
                CytoNetworkConverter.convertCytoNetwork(cytoNetwork);
            } else {
                CytoPPINetworkExperiments cytoNetworkExp = NetworkConverter.convertPPINetworkExp(network);
                CytoNetworkConverter.convertCytoNetwork(cytoNetworkExp);
            }
        }
    }
}
