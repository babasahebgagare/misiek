package main;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import mappers.IDMapper;
import structs.Family;
import structs.PPINetwork;

public class DataHandle {

    private static IDMapper networkIDMapper = new IDMapper();
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

    public static void createFamily(String FamilyID, Color color) {
        if (!families.containsKey(FamilyID)) {
            Family fam = new Family(FamilyID, color);
            families.put(FamilyID, fam);
        }
    }

    public static void createProtein(String ProteinID, String ParentProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        network.addProtein(ProteinID, ParentProteinID, family);
    }

    public static void createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        network.addRootProtein(ProteinID, family);
    }

    public static void createInteraction(String ID, String NetworkID, String SourceID, String TargetID, Double Probability) {
        PPINetwork network = networks.get(NetworkID);

        network.addInteraction(ID, SourceID, TargetID, Probability);
    }

    public static PPINetwork findNetworkByCytoID(String PPINetworkCytoID) {

        String PPINetworkID = networkIDMapper.getIDByCytoID(PPINetworkCytoID);
        return networks.get(PPINetworkID);
    }

    public static void addNetworkIDMapping(String CytoID, String ID) {
        networkIDMapper.addMapping(CytoID, ID);
    }

    public static Map<String, PPINetwork> getNetworks() {
        return networks;
    }

    public static void setNetworks(Map<String, PPINetwork> nets) {
        networks = nets;
    }
}
