package logicmodel.controllers;

import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoPPINetwork;
import logicmodel.structs.PPINetwork;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(PPINetwork network) {

        CytoPPINetwork cytoPPINetwork = CytoDataHandle.createCytoNetwork(network.getID(), network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
