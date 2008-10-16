package tester;

import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ding.view.DGraphView;
import ding.view.DingCanvas;
import utils.Messenger;

public class TestCanvas {

    public static void test() {

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        DGraphView dNetworkView = (DGraphView) cyNetworkView;

        DingCanvas BACKGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.BACKGROUND_CANVAS);

        TestPaintAbs a = new TestPaintAbs();
        if (a == null) {
            Messenger.Message("error" + cyNetworkView.getIdentifier() + ": " + cyNetworkView.getTitle());
        } else {
        }
        BACKGROUNDCanvas.add(a);
        cyNetworkView.updateView();
        BACKGROUNDCanvas.repaint();

    }
}