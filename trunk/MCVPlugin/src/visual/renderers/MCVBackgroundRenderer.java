package visual.renderers;

import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ding.view.DGraphView;
import ding.view.DingCanvas;
import structs.view.GroupNodeView;

public class MCVBackgroundRenderer {

    public static void backgroundRender() {
        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        backgroundRenderNetwork(cyNetworkView);
    }

    public static void backgroundRenderNetwork(CyNetworkView cyNetworkView) {
        DGraphView dNetworkView = ((DGraphView) cyNetworkView);
        DingCanvas backgroungCanvas = dNetworkView.getCanvas(DGraphView.Canvas.BACKGROUND_CANVAS);

        backgroundRenderCanvas(backgroungCanvas);
    }

    public static void backgroundRenderCanvas(DingCanvas backgroungCanvas) {
        renderGroupNodes(backgroungCanvas);
    }

    private static void renderGroupNodes(DingCanvas backgroungCanvas) {
        GroupNodeView a = new GroupNodeView(60, 60, 10, 10);

        backgroungCanvas.removeAll();
        backgroungCanvas.add(a);
    }
}
