package logicmodel.controllers;

import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoPPINetwork;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(PPINetwork network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        CytoPPINetwork cytoPPINetwork = cdh.createCytoNetwork(network.getID(), network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
