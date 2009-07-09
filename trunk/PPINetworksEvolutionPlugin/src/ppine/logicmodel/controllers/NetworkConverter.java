package ppine.logicmodel.controllers;

import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoPPINetwork;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.utils.IDCreator;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(SpeciesTreeNode network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

        CytoPPINetwork cytoPPINetwork = cdh.createCytoNetwork(newCytoNetworkID, network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }

    /*  static CytoPPINetworkExperiments convertPPINetworkExp(SpeciesTreeNode network) {
    CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

    String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

    CytoPPINetworkExperiments cytoPPINetwork = cdh.createCytoNetworkExperiments(newCytoNetworkID, network);

    ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
    return cytoPPINetwork;
    }*/
}
