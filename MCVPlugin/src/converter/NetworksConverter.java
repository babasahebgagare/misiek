package converter;

import java.util.Collection;
import structs.model.CytoPPINetwork;
import structs.model.PPINetwork;

public class NetworksConverter {

    public static void convertNetworks(Collection<PPINetwork> networks) {
        for (PPINetwork network : networks) {
            CytoPPINetwork cytoNetwork = NetworkConverter.convertPPINetwork(network);
            CytoNetworkConverter.convertCytoNetwork(cytoNetwork);
        }
    }
}
