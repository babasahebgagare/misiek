package tester;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.ding.DingNetworkView;
import cytoscape.view.CyNetworkView;
import cytoscape.view.CyNodeView;
import cytoscape.visual.TestNodeView;
import ding.view.DGraphView;
import ding.view.DNodeView;
import ding.view.DingCanvas;
import ding.view.InnerCanvas;
import giny.view.NodeView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.sound.sampled.Line;
import utils.Messenger;

public class TestCanvas {

    public static void test() {

        CyNetworkView cyNetworkView = Cytoscape.getCurrentNetworkView();
        DGraphView dNetworkView = (DGraphView) cyNetworkView;
        //        InnerCanvas canvas = dNetworkView.getCanvas();
        //   dNetworkView.setBackgroundPaint(new Paint() {})
        DingCanvas BACKGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.BACKGROUND_CANVAS);
        //   DingCanvas FOREGROUNDCanvas = dNetworkView.getCanvas(DGraphView.Canvas.FOREGROUND_CANVAS);
        //   DingCanvas NETWORKCanvas = dNetworkView.getCanvas(DGraphView.Canvas.NETWORK_CANVAS);

        TestPaintAbs a = new TestPaintAbs();
        if (a == null) {
            Messenger.Message("error" + cyNetworkView.getIdentifier() + ": " + cyNetworkView.getTitle());
        } else {
            Messenger.Message("ok" + cyNetworkView.getIdentifier() + ": " + cyNetworkView.getTitle());
        }
        //  FOREGROUNDCanvas.add(a);
        BACKGROUNDCanvas.setBackground(Color.WHITE);
        BACKGROUNDCanvas.add("aa", a);
        //   a.updateUI();
        //  BACKGROUNDCanvas.updateUI();
        //   BACKGROUNDCanvas.paintImmediately(0, 0, 300, 300);
        //     BACKGROUNDCanvas.repaint();
        //  FOREGROUNDCanvas.add(a);
        //   NETWORKCanvas.add(a);
        // cyNetworkView.redrawGraph(true, true);
        cyNetworkView.updateView();
        Messenger.Message("DONE");
    }
}