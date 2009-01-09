package converter;

import java.util.Collection;
import main.DataHandle;
import structs.model.PPINetwork;

public class NetworksConverter {

    public static void convertAllNetworks() {
        Collection<PPINetwork> networks = DataHandle.getNetworks().values();
        for (PPINetwork network : networks) {
            NetworkConverter.convertPPINetwork(network);
        }
    }

    public static void convertNetworks(Collection<PPINetwork> networks) {
        for (PPINetwork network : networks) {
            NetworkConverter.convertPPINetwork(network);
        }
    }
}
