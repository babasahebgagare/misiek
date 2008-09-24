package main;

import java.util.HashMap;
import java.util.Map;
import structs.Family;
import structs.PPINetwork;

public class DataHandle {

    private static Map<String, PPINetwork> networks = new HashMap<String, PPINetwork>();
    private static Map<String, Family> families = new HashMap<String, Family>();

    public static void createRootPPINetwork(String NetworkID) {
        if (!networks.containsKey(NetworkID)) {
            PPINetwork net = new PPINetwork(NetworkID, null);
            networks.put(NetworkID, net);
        }
    }

    public static void createPPINetwork(String NetworkID, String ParentNetworkID) {
        if (!networks.containsKey(NetworkID)) {
            PPINetwork net = new PPINetwork(NetworkID, ParentNetworkID);
            networks.put(NetworkID, net);
        }
    }

    public static void createFamily(String FamilyID) {
        if (!families.containsKey(FamilyID)) {
            Family fam = new Family(FamilyID);
            families.put(FamilyID, fam);
        }
    }

    public static void createProtein(String ProteinID, String ParentProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);

        network.addProtein(ProteinID, ParentProteinID, FamilyID);
    }

    public static void createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);

        network.addRootProtein(ProteinID, FamilyID);
    }

    public static Map<String, PPINetwork> getNetworks() {
        return networks;
    }

    public static void setNetworks(Map<String, PPINetwork> nets) {
        networks = nets;
    }
}
