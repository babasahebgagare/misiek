package ppine.visual.renderers;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ding.view.DGraphView;
import ding.view.DingCanvas;
import giny.view.NodeView;
import ppine.logicmodel.controllers.DataHandle;
import ppine.viewmodel.structs.CytoPPINetworkProjection;
import ppine.viewmodel.structs.CytoGroupNode;

import ppine.logicmodel.structs.Protein;
import ppine.tester.GroupNodeView;

public class MCVBackgroundRenderer {

    public static void backgroundRender() {
        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        if (cyNetworkView != Cytoscape.getNullNetworkView()) {
            backgroundRenderNetwork(cyNetworkView);
        }
    }

    public static void backgroundRenderNetwork(CyNetworkView cyNetworkView) {
        DGraphView dNetworkView = ((DGraphView) cyNetworkView);
        DingCanvas backgroungCanvas = dNetworkView.getCanvas(DGraphView.Canvas.BACKGROUND_CANVAS);

        backgroundRenderCanvas(backgroungCanvas, cyNetworkView);
    }

    public static void backgroundRenderCanvas(DingCanvas backgroungCanvas, CyNetworkView cyNetworkView) {
        renderGroupNodes(backgroungCanvas, cyNetworkView);
    }

    private static void renderGroupNodes(DingCanvas backgroungCanvas, CyNetworkView cyNetworkView) {
        backgroungCanvas.removeAll();
/*
        CytoPPINetworkProjection projection = DataHandle.findProjectionByCytoID(cyNetworkView.getIdentifier());

          for (GroupNode groupNode : projection.getGroupNodes().values()) {
        
        Protein motherProtein = groupNode.getContext().getMotherProtein();
        CyNode parentCyNode = Cytoscape.getCyNode(motherProtein.getID());
        
        CyNetworkView parentNetworkView = Cytoscape.getNetworkView(motherProtein.getContext().getNetwork().getCytoID());
        NodeView parentNodeView = parentNetworkView.getNodeView(parentCyNode);
        
        GroupNodeView groupNodeView = new GroupNodeView((int) parentNodeView.getXPosition(), (int) parentNodeView.getYPosition(), 100, 100, (DGraphView) cyNetworkView);
        groupNodeView.setFillColor(motherProtein.getFamily().getColor().darker().darker());
        
        backgroungCanvas.add(groupNodeView);
        groupNodeView.repaint();
        }*/
        backgroungCanvas.repaint();
        cyNetworkView.updateView();
    }
}
