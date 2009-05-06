package mcv.logicmodel.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import mcv.logicmodel.structs.PPINetwork;
import mcv.logicmodel.structs.Protein;
import mcv.main.PluginDataHandle;
import mcv.utils.MemoLogger;

public class ProjectorInfoCalculator {

    public static void calculateProjectorInfo() {

        calculateNetworkTreeInfo();
        calculateProteinsInfo();

    }

    private static void addProjectorInfoForNetworks(PPINetwork downNetwork, PPINetwork upNetwork) {
        if (downNetwork != upNetwork) {
            downNetwork.getContext().getHierarchy().addNetworkAbove(upNetwork);
            upNetwork.getContext().getHierarchy().addNetworkBelow(downNetwork);
        }
    }

    private static void addProjectorInfoForProteins(Protein Down, Protein Up) {

        PPINetwork DownNetwork = Down.getContext().getNetwork();
        PPINetwork UpNetwork = Up.getContext().getNetwork();

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

    private static void calculateInfoForNetwork(PPINetwork network) {
        PPINetwork upNetworkOrNull = network;

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
        Collection<PPINetwork> networks = dh.getNetworks().values();

        for (PPINetwork network : networks) {

            Collection<Protein> proteins = network.getProteins().values();

            for (Protein protein : proteins) {
                calculateProjectorInfoForProtein(protein);

            }
        }
    }

    private static void calculateNetworkTreeInfo() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<PPINetwork> networks = dh.getNetworks().values();

        for (PPINetwork network : networks) {
            calculateInfoForNetwork(network);
        }
    }
}
