package mcv.logicmodel.controllers;

import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.viewmodel.structs.CytoPPINetwork;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.utils.IDCreator;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(PPINetwork network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

        CytoPPINetwork cytoPPINetwork = cdh.createCytoNetwork(newCytoNetworkID, network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
