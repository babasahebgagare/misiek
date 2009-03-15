/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
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
        for (PPINetwork network : PluginDataHandle.getDataHandle().getNetworks().values()) {
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
