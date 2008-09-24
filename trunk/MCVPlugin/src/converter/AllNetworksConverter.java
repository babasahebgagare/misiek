package converter;

import java.util.Collection;
import main.DataHandle;
import structs.PPINetwork;

public class AllNetworksConverter {

    public static void convertAllNetworks() {

        Collection<PPINetwork> networks = DataHandle.getNetworks().values();

        for (PPINetwork network : networks) {

            NetworkConverter.convertPPINetwork(network);
        }

    }
}
