package logicmodel.controllers;

import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoPPINetwork;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import utils.IDCreator;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(PPINetwork network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

        CytoPPINetwork cytoPPINetwork = cdh.createCytoNetwork(newCytoNetworkID, network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
