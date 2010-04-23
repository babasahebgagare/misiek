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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.logicmodel.controllers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.utils.MemoLogger;

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
