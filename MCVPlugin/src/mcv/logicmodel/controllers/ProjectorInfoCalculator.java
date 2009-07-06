package mcv.logicmodel.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.logicmodel.structs.Protein;
import mcv.main.PluginDataHandle;
import mcv.utils.MemoLogger;

public class ProjectorInfoCalculator {

    public static void calculateProjectorInfo() {

        calculateNetworkTreeInfo();
        calculateProteinsInfo();

    }

    private static void addProjectorInfoForNetworks(SpeciesTreeNode downNetwork, SpeciesTreeNode upNetwork) {
        if (downNetwork != upNetwork) {
            downNetwork.getContext().getHierarchy().addNetworkAbove(upNetwork);
            upNetwork.getContext().getHierarchy().addNetworkBelow(downNetwork);
        }
    }

    private static void addProjectorInfoForProteins(Protein Down, Protein Up) {

        SpeciesTreeNode DownNetwork = Down.getContext().getNetwork();
        SpeciesTreeNode UpNetwork = Up.getContext().getNetwork();

        Map<String, Collection<Protein>> UpMap = Down.getProjects().getProjectorMapUp();
        Map<String, Collection<Protein>> DownMap = Up.getProjects().getProjectorMapDown();

        if (!UpMap.containsKey(UpNetwork.getID())) {
            UpMap.put(UpNetwork.getID(), new HashSet<Protein>());
        }
        if (!DownMap.containsKey(DownNetwork.getID())) {
            DownMap.put(DownNetwork.getID(), new HashSet<Protein>());
        }

        UpMap.get(UpNetwork.getID()).add(Up);
        DownMap.get(DownNetwork.getID()).add(Down);
    }

    private static void calculateInfoForNetwork(SpeciesTreeNode network) {
        SpeciesTreeNode upNetworkOrNull = network;

        MemoLogger.log("network search: " + network.getID());
        while (upNetworkOrNull != null) {
            addProjectorInfoForNetworks(network, upNetworkOrNull);
            MemoLogger.log("netUp: " + upNetworkOrNull.getID());

            upNetworkOrNull = upNetworkOrNull.getContext().tryGetParentNetwork();
            if (upNetworkOrNull == null) {
                break;
            }
        }
    }

    private static void calculateProjectorInfoForProtein(Protein protein) {
        Protein parentProteinOrNull = protein;

        while (parentProteinOrNull != null) {
            addProjectorInfoForProteins(protein, parentProteinOrNull);

            parentProteinOrNull = parentProteinOrNull.getContext().tryGetParentProtein();
            if (parentProteinOrNull == null) {
                break;
            }

        }
    }

    private static void calculateProteinsInfo() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<SpeciesTreeNode> networks = dh.getNetworks().values();

        for (SpeciesTreeNode network : networks) {

            Collection<Protein> proteins = network.getProteins().values();

            for (Protein protein : proteins) {
                calculateProjectorInfoForProtein(protein);

            }
        }
    }

    private static void calculateNetworkTreeInfo() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<SpeciesTreeNode> networks = dh.getNetworks().values();

        for (SpeciesTreeNode network : networks) {
            calculateInfoForNetwork(network);
        }
    }
}
