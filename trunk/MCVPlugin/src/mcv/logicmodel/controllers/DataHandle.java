package mcv.logicmodel.controllers;

import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import java.util.Map;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeMap;
import mcv.io.parsers.ExperimentParserStruct;
import mcv.logicmodel.structs.ExpInteraction;
import mcv.logicmodel.structs.Family;
import mcv.logicmodel.structs.Interaction;
import mcv.logicmodel.structs.PPINetwork;
import mcv.logicmodel.structs.PPINetworkExp;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.logicmodel.structs.Protein;

public class DataHandle {

    private Map<String, SpeciesTreeNode> networks = new HashMap<String, SpeciesTreeNode>();
    private Map<String, Family> families = new TreeMap<String, Family>();
    private SpeciesTreeNode rootNetwork;

    public PPINetworkExp createExpPPINetwork(String speciesName, String expNetworkName) {
        SpeciesTreeNode parentNetwork = networks.get(speciesName);
        System.out.println("searching: " + speciesName);
        if (parentNetwork == null) {
            System.out.println("PARENT NULL");
        }
        PPINetworkExp net = new PPINetworkExp(expNetworkName, parentNetwork);
        parentNetwork.getContext().addChild(net);
        networks.put(expNetworkName, net);
        return net;
    }

    public void createInteractionExp(PPINetworkExp netExp, String edgeID, ExperimentParserStruct interaction) {
        Protein source = createProteinIfNeeded(netExp, interaction.getFrom(), interaction);
        Protein target = createProteinIfNeeded(netExp, interaction.getTo(), interaction);
        if (source != null && target != null) {
            String expID = interaction.getExpID();
            ExpInteraction expInteraction = new ExpInteraction(expID, source, target, edgeID, netExp);
            netExp.addInteraction(expInteraction);
        }
    }

    public PPINetwork getPPINetwork(String speciesName) {
        SpeciesTreeNode node = networks.get(speciesName);
        if (node == null) {
            return null;
        } else if (node instanceof PPINetwork) {
            return (PPINetwork) node;
        } else {
            return null;
        }
    }

    public SpeciesTreeNode tryFindPPINetworkByProteinID(String sourceID) {
        for (SpeciesTreeNode network : networks.values()) {
            if (network.containsProtein(sourceID)) {
                return network;
            }
        }
        return null;
    }

    public PPINetworkExp tryGetExpPPINetowrk(String expNetworkName) {
        SpeciesTreeNode node = networks.get(expNetworkName);
        if (node instanceof PPINetworkExp) {
            return (PPINetworkExp) node;
        } else {
            return null;
        }
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
        for (SpeciesTreeNode network : networks.values()) {

            if (network instanceof PPINetwork) {
                if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                    createInteraction(EdgeID, SourceID, TargetID, Probability, (PPINetwork) network);
                }
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
        SpeciesTreeNode ParentNetwork = networks.get(ParentNetworkID);
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
            SpeciesTreeNode network = networks.get(NetworkID);
            SpeciesTreeNode parentNetwork = network.getContext().tryGetParentNetwork();

            Protein parentProtein = parentNetwork.getProtein(parentProteinID);
            Family family = families.get(FamilyID);
            protein = network.addProtein(ProteinID, parentProtein, family);
        } else {
            protein = createRootProtein(ProteinID, NetworkID, FamilyID);
        }
        return protein;
    }

    public Protein createRootProtein(String ProteinID, String NetworkID, String FamilyID) {
        SpeciesTreeNode network = networks.get(NetworkID);
        Family family = families.get(FamilyID);
        Protein protein = network.addRootProtein(ProteinID, family);
        return protein;
    }

    public Map<String, SpeciesTreeNode> getNetworks() {
        return networks;
    }

    public void setNetworks(Map<String, SpeciesTreeNode> nets) {
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

    public SpeciesTreeNode getRootNetwork() {
        return rootNetwork;
    }

    public SpeciesTreeNode getNetwork(String networkID) {
        return networks.get(networkID);
    }

    public void setRootNetwork(SpeciesTreeNode rootNetwork) {
        this.rootNetwork = rootNetwork;
    }

    private Protein createProteinIfNeeded(PPINetworkExp netExp, String proteinID, ExperimentParserStruct interaction) {
        System.out.println("IF needed: " + proteinID);
        if (netExp.containsProtein(proteinID)) {
            return netExp.getProtein(proteinID);
        } else {
            PPINetwork network = this.getPPINetwork(interaction.getSpeciesName());
            Protein proteinHelp = network.getProtein(proteinID);
            if (proteinHelp != null) {
                Protein protein = this.createProtein(proteinID, proteinID, netExp.getID(), proteinHelp.getFamily().getFamilyID());
                return protein;
            } else {
                return null;
            }
        }
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
