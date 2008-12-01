package converter;

import main.CytoDataHandle;
import structs.model.CytoPPINetwork;
import structs.model.PPINetwork;

public class NetworkConverter {

    public static CytoPPINetwork convertPPINetwork(PPINetwork network) {

        CytoPPINetwork cytoPPINetwork = CytoDataHandle.createCytoNetwork(network.getID(), network);

        ProteinsConverter.convertNetworkProteins(cytoPPINetwork, network.getProteins().values());
        return cytoPPINetwork;
    }
}
