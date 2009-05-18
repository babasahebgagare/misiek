package mcv.logicmodel.controllers;

import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import java.util.Map;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import mcv.logicmodel.structs.Family;
import mcv.logicmodel.structs.Interaction;
import mcv.logicmodel.structs.PPINetwork;
import mcv.logicmodel.structs.Protein;

public class DataHandle {

    private Map<String, PPINetwork> networks = new HashMap<String, PPINetwork>();
    private Map<String, Family> families = new HashMap<String, Family>();
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

    public void createProtein(String ProteinID, String ParentProteinID, String NetworkID, String FamilyID) {
        if (ParentProteinID != null) {
            PPINetwork network = networks.get(NetworkID);
            PPINetwork ParentNetwork = network.getContext().tryGetParentNetwork();
            Protein ParentProtein = ParentNetwork.getProteins().get(ParentProteinID);
            while (ParentProtein == null && ParentNetwork.getContext().tryGetParentNetwork() != null) {
                ParentNetwork = ParentNetwork.getContext().tryGetParentNetwork();
                ParentProtein = ParentNetwork.getProteins().get(ParentProteinID);
            }
            Family family = families.get(FamilyID);
            network.addProtein(ProteinID, ParentProtein, family);
        } else {
            createRootProtein(ProteinID, NetworkID, FamilyID);
        }
    }

    public void createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        PPINetwork network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        network.addRootProtein(ProteinID, family);
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

    public void setRootNetwork(PPINetwork rootNetwork) {
        this.rootNetwork = rootNetwork;
    }
}
