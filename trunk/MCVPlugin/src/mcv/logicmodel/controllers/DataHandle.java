package mcv.logicmodel.controllers;

import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import java.util.Map;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import mcv.logicmodel.structs.Family;
import mcv.logicmodel.structs.Interaction;
import mcv.logicmodel.structs.PPINetwork;
import mcv.logicmodel.structs.Protein;

public class DataHandle {

    private Map<String, PPINetwork> networks = new HashMap<String, PPINetwork>();
    private Map<String, Family> families = new TreeMap<String, Family>();
    private PPINetwork rootNetwork;

    public PPINetwork tryFindPPINetworkByProteinID(String sourceID) {
        for (PPINetwork network : networks.values()) {
            if (network.containsProtein(sourceID)) {
                return network;
            }
        }
        return null;
    }

    private void addInteractionProbabilityAttribute(String cannonName, Double probability) {
        String attrName = "Probability";
        final CyAttributes edgeAttrs = Cytoscape.getEdgeAttributes();
        edgeAttrs.setAttribute(cannonName, attrName, probability);
    }

    public void createInteraction(String EdgeID, String SourceID, String TargetID, Double Probability, PPINetwork network) {
        Protein source = network.getProtein(SourceID);
        Protein target = network.getProtein(TargetID);
        addInteractionProbabilityAttribute(EdgeID, Probability);

        Interaction interaction = new Interaction(source, target, Probability, EdgeID, network);
        network.addInteraction(interaction);
    }

    public void createInteraction(String EdgeID, String SourceID, String TargetID, Double Probability) {
        for (PPINetwork network : networks.values()) {

            if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                createInteraction(EdgeID, SourceID, TargetID, Probability, network);
            }
        }
    }

    public void createRootPPINetwork(String NetworkID) {
        PPINetwork net = new PPINetwork(NetworkID, null);
        networks.put(NetworkID, net);
        rootNetwork = net;
    }

    public void createPPINetwork(String NetworkID, String ParentNetworkID) {
        //  if (!networks.containsKey(NetworkID)) {
        PPINetwork ParentNetwork = networks.get(ParentNetworkID);
        PPINetwork net = new PPINetwork(NetworkID, ParentNetwork);
        ParentNetwork.getContext().addChild(net);
        networks.put(NetworkID, net);
    }

    public void createFamily(String FamilyID, Color color) {
        Family fam = new Family(FamilyID, color);
        families.put(FamilyID, fam);
    }

    public Protein createProtein(String ProteinID, String parentProteinID, String NetworkID, String FamilyID) {
        Protein protein;
        if (parentProteinID != null) {
            PPINetwork network = networks.get(NetworkID);
            PPINetwork parentNetwork = network.getContext().tryGetParentNetwork();

            Protein parentProtein = parentNetwork.getProtein(parentProteinID);
            Family family = families.get(FamilyID);
            protein = network.addProtein(ProteinID, parentProtein, family);
        } else {
            protein = createRootProtein(ProteinID, NetworkID, FamilyID);
        }
        return protein;
    }

    public Protein createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        Protein protein = network.addRootProtein(ProteinID, family);
        return protein;
    }

    public Map<String, PPINetwork> getNetworks() {
        return networks;
    }

    public void setNetworks(Map<String, PPINetwork> nets) {
        networks = nets;
    }

    public Collection<Family> getFamilies() {
        return families.values();
    }

    public Family getFamily(String ID) {
        return families.get(ID);
    }

    public Collection<String> getFamiliesKeys() {
        return families.keySet();
    }

    public void setFamilies(Map<String, Family> families) {
        this.families = families;
    }

    public PPINetwork getRootNetwork() {
        return rootNetwork;
    }

    public PPINetwork getNetwork(String networkID) {
        return networks.get(networkID);
    }

    public void setRootNetwork(PPINetwork rootNetwork) {
        this.rootNetwork = rootNetwork;
    }
    /*
    private Protein findParentProtein(String ParentProteinID, PPINetwork network) throws FamiliesTreeFormatException {
    try {
    PPINetwork ParentNetwork = network.getContext().tryGetParentNetwork();
    Protein ParentProtein = null;
    while (ParentProtein == null && ParentNetwork != null) {
    ParentProtein = ParentNetwork.getProteins().get(ParentProteinID);
    ParentNetwork = ParentNetwork.getContext().tryGetParentNetwork();
    }
    return ParentProtein;
    } catch (Exception e) {
    throw new FamiliesTreeFormatException(ParentProteinID, 555);
    }
    }*/
}
