package main;

import java.util.HashMap;
import java.util.Map;
import structs.PPINetwork;

public class DataHandle {

    private static DataHandle instance = new DataHandle();
    private Map<String, PPINetwork> networks = new HashMap<String, PPINetwork>();

    public static DataHandle getInstance() {
        return instance;
    }

    public void createRootPPINetwork(String NetworkID) {
        if (!networks.containsKey(NetworkID)) {
            PPINetwork net = new PPINetwork(NetworkID, null);
            networks.put(NetworkID, net);
        }
    }

    public void createPPINetwork(String NetworkID, String ParentNetworkID) {
        if (!networks.containsKey(NetworkID)) {
            PPINetwork net = new PPINetwork(NetworkID, ParentNetworkID);
            networks.put(NetworkID, net);
        }
    }

    public Map<String, PPINetwork> getNetworks() {
        return networks;
    }

    public void setNetworks(Map<String, PPINetwork> networks) {
        this.networks = networks;
    }
}
