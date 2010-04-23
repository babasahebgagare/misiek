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

package ppine.utils;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;

public class Stats {

    private static void printCytoscape() {
        System.out.println("----CYTOSCPAE----");
        for (CyNetwork cyNetwork : Cytoscape.getNetworkSet()) {
            System.out.println(cyNetwork.getTitle() + ": " + cyNetwork.getIdentifier());
        }

    }

    public static void printStats() {
        System.out.println("------------MODEL------------");
        printCytoscape();
        printPlugin();
        System.out.println("------------VIEW------------");
        printCytoscapeView();
        printPluginView();
        System.out.println("-----------INTERACTIONS--------");
        printCytoscapeInt();
        printPluginInt();

    }

    private static void printCytoscapeInt() {
        System.out.println("----CYTOSCPAE INT----");
        System.out.println(Cytoscape.getCyEdgesList().size());
    }

    private static void printCytoscapeView() {
        System.out.println("----CYTOSCPAE VIEW----");
        for (CyNetworkView cyNetworkView : Cytoscape.getNetworkViewMap().values()) {
            System.out.println(cyNetworkView.getTitle() + ": " + cyNetworkView.getIdentifier());
        }

    }

    private static void printPlugin() {
        System.out.println("----PLUGIN----");
        for (SpeciesTreeNode network : PluginDataHandle.getDataHandle().getNetworks().values()) {
            System.out.println(network.getID());
        }
    }

    private static void printPluginInt() {
        System.out.println("----PLUGIN INT-----");
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();
        System.out.println(cdh.getCytoInteractions().size());
    }

    private static void printPluginView() {
        System.out.println("----PLUGIN VIEW----");
        for (CytoAbstractPPINetwork network : PluginDataHandle.getCytoDataHandle().getCytoPPINetworks()) {
            System.out.println(network.getID());
        }

    }
}
