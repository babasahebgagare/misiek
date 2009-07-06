package mcv.logicmodel.controllers;

import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.viewmodel.structs.CytoPPINetwork;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.main.PluginDataHandle;
import mcv.utils.IDCreator;
import mcv.viewmodel.structs.CytoPPINetworkExperiments;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(SpeciesTreeNode network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

        CytoPPINetwork cytoPPINetwork = cdh.createCytoNetwork(newCytoNetworkID, network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }

    static CytoPPINetworkExperiments convertPPINetworkExp(SpeciesTreeNode network) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String newCytoNetworkID = IDCreator.createNetCytoNetworkID(network.getID());

        CytoPPINetworkExperiments cytoPPINetwork = cdh.createCytoNetworkExperiments(newCytoNetworkID, network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
