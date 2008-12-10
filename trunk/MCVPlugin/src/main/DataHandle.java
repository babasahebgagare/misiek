package main;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.util.Collection;
import structs.model.Family;
import structs.model.Interaction;
import structs.model.PPINetwork;
import structs.model.Protein;

public class DataHandle {

    private static Map<String, PPINetwork> networks = new HashMap<String, PPINetwork>();
    private static Map<String, Family> families = new HashMap<String, Family>();
    private static PPINetwork rootNetwork;

    private static void createInteaction(String EdgeID, String SourceID, String TargetID, Double Probability, PPINetwork network) {
        Protein source = network.getProtein(SourceID);
        Protein target = network.getProtein(TargetID);

        Interaction interaction = new Interaction(source, target, Probability, EdgeID, network);
        network.addInteraction(interaction);
    }

    public static void createInteraction(String EdgeID, String SourceID, String TargetID, Double Probability) {
        for (PPINetwork network : networks.values()) {

            if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                createInteaction(EdgeID, SourceID, TargetID, Probability, network);
            }
        }
    }

    public static void createRootPPINetwork(String NetworkID) {
        PPINetwork net = new PPINetwork(NetworkID, null);
        networks.put(NetworkID, net);
        rootNetwork = net;
    }

    public static void createPPINetwork(String NetworkID, String ParentNetworkID) {
        //   if (!networks.containsKey(NetworkID)) {
        PPINetwork ParentNetwork = networks.get(ParentNetworkID);
        PPINetwork net = new PPINetwork(NetworkID, ParentNetwork);
        ParentNetwork.getContext().addChild(net);
        networks.put(NetworkID, net);
    }

    public static void createFamily(String FamilyID, Color color) {
        Family fam = new Family(FamilyID, color);
        families.put(FamilyID, fam);
    }

    public static void createProtein(String ProteinID, String ParentProteinID, String NetworkID, String FamilyID) {
        if (ParentProteinID != null) {
            PPINetwork network = networks.get(NetworkID);
            PPINetwork ParentNetwork = network.getContext().getParentNetwork();
            Protein ParentProtein = ParentNetwork.getProteins().get(ParentProteinID);
            while (ParentProtein == null && ParentNetwork.getContext().getParentNetwork() != null) {
                ParentNetwork = ParentNetwork.getContext().getParentNetwork();
                ParentProtein = ParentNetwork.getProteins().get(ParentProteinID);
            }
            Family family = families.get(FamilyID);
            network.addProtein(ProteinID, ParentProtein, family);
        } else {
            createRootProtein(ProteinID, NetworkID, FamilyID);
        }
    }

    public static void createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        network.addRootProtein(ProteinID, family);
    }

    public static Map<String, PPINetwork> getNetworks() {
        return networks;
    }

    public static void setNetworks(Map<String, PPINetwork> nets) {
        networks = nets;
    }

    public static Collection<Family> getFamilies() {
        return families.values();
    }

    public static Family getFamily(String ID) {
        return families.get(ID);
    }

    public static Collection<String> getFamiliesKeys() {
        return families.keySet();
    }

    public static void setFamilies(Map<String, Family> families) {
        DataHandle.families = families;
    }

    public static PPINetwork getRootNetwork() {
        return rootNetwork;
    }

    public static void setRootNetwork(PPINetwork rootNetwork) {
        DataHandle.rootNetwork = rootNetwork;
    }
}
