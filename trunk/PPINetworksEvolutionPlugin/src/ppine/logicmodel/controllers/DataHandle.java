/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package ppine.logicmodel.controllers;

import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import java.util.Map;
import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import ppine.io.parsers.ExperimentParserStruct;
import ppine.logicmodel.structs.ExpInteraction;
import ppine.logicmodel.structs.Experiment;
import ppine.logicmodel.structs.Family;
import ppine.logicmodel.structs.Interaction;
import ppine.logicmodel.structs.PPINetwork;
import ppine.logicmodel.structs.PPINetworkExp;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import ppine.utils.ColorGenerator;

public class DataHandle {

    private Map<String, SpeciesTreeNode> networks = new HashMap<String, SpeciesTreeNode>();
    private Map<String, Family> families = new TreeMap<String, Family>();
    private Map<String, Experiment> experiments = new TreeMap<String, Experiment>();
    private SpeciesTreeNode rootNetwork;

    public boolean experimentExist(String expID) {
        return experiments.containsKey(expID);
    }

    public Experiment getExperiment(String expID) {
        return experiments.get(expID);
    }

    public Experiment createExperiment(String expID) {
        Color color = ColorGenerator.generateColor(expID);
        Experiment exp = new Experiment(expID, color);
        experiments.put(expID, exp);
        return exp;
    }

    public PPINetworkExp createExpPPINetwork(String speciesName, String expNetworkName) {
        SpeciesTreeNode parentNetwork = networks.get(speciesName);
        // System.out.println("searching: " + speciesName);
        // if (parentNetwork == null) {
        //     System.out.println("PARENT NULL");
        // }
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
            Experiment exp = getExperiment(expID);
            if (exp == null) {
                exp = createExperiment(expID);
            }
            ExpInteraction expInteraction = new ExpInteraction(exp, source, target, edgeID, netExp);
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

    public Collection<SpeciesTreeNode> tryFindPPINetworkByProteinID(String sourceID, String targetID) {
        Collection<SpeciesTreeNode> nets = new HashSet<SpeciesTreeNode>();
        for (SpeciesTreeNode network : networks.values()) {
            if (network.containsProtein(sourceID) && network.containsProtein(targetID)) {
                nets.add(network);
            }
        }
        return nets;
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
