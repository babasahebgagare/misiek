/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.layout.CyLayoutAlgorithm;
import cytoscape.layout.CyLayouts;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.VisualStyle;

public class CytoVisualHandle {

    public static void applyCyLayoutAlgorithm(CyNetwork cyNetwork, CyNetworkView cyNetworkView) {
        Cytoscape.getVisualMappingManager().setNetworkView(cyNetworkView);
        CyLayoutAlgorithm layout = CyLayouts.getDefaultLayout();
        layout.doLayout(cyNetworkView);
        cyNetworkView.setZoom(0.7);
    }

    public static void applyVisualStyleForNetwork(CyNetworkView cyNetworkView) {
        VisualStyle MCVStyle = Cytoscape.getVisualMappingManager().getCalculatorCatalog().getVisualStyle("MCVStyle");
        cyNetworkView.applyVizmapper(MCVStyle);
        Cytoscape.getVisualMappingManager().setVisualStyle(MCVStyle);

    }
}
