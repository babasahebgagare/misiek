package tester;

import cytoscape.Cytoscape;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.controls.DragControl;
import prefuse.controls.PanControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Graph;
import prefuse.data.Node;
import prefuse.data.Schema;
import prefuse.data.io.DataIOException;
import prefuse.data.io.GraphMLReader;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import utils.Messenger;

public class PrefuseTester extends Display {

    public static final String LABEL = "label";
    /** Node table schema used for generated Graphs */
    public static final Schema LABEL_SCHEMA = new Schema();
    public static ArrayList<Integer> pamiec = new ArrayList<Integer>();
    public static Node nodeTest;
    

    static {
        LABEL_SCHEMA.addColumn(LABEL, String.class, "");
    }

    public static Graph getStar(int n) {
        Graph g = new Graph();
        g.getNodeTable().addColumns(LABEL_SCHEMA);

        Node r = g.addNode();
        //r.set("name", "name");
       // r.setString("node", "name");
        nodeTest = r;
      ///  r.setString("node", "gender");

        for (int i = 1; i <= n; ++i) {
            Node nn = g.addNode();
            nn.setString(LABEL, String.valueOf(i));
            g.addEdge(r, nn);
        }
        return g;
    }

    public static void test() {
        final JDesktopPane cytoDesk = Cytoscape.getDesktop().getNetworkViewManager().getDesktopPane();


        Graph graph = null;

                //getStar(10);

        // add the graph to the visualization as the data group "graph"
// nodes and edges are accessible as "graph.nodes" and "graph.edges"
        Visualization vis = new Visualization();
      //  try {
            graph= getStar(10);
            //graph = new GraphMLReader().readGraph("http://prefuse.org/doc/manual/introduction/example/socialnet.xml");
   //     } catch (DataIOException ex) {
   //         Messenger.Message("blad");
   //     }
        vis.addGraph("graph", graph);

     //   NodeItem nodeItem = (NodeItem) vis.getVisualItem("graph.nodes", nodeTest);

    //    if (nodeItem == null) {
   //         Messenger.Message("null");
   //     }
    //    nodeItem.setVisible(true);

     //   nodeItem.setSize(40.0);
     //   nodeItem.setShape(Constants.SHAPE_ELLIPSE);
        Display display = new Display(vis);
        display.setSize(720, 500); // set display size

        LabelRenderer r = new LabelRenderer();
        r.setRoundedCorner(8, 8); // round the corners
        //MyRenderer myRenderer = new MyRenderer();

        //    Messenger.Message("2");
// create a new default renderer factory
// return our name label renderer as the default for all non-EdgeItems
// includes straight line edges for EdgeItems by default
        DefaultRendererFactory fr = new DefaultRendererFactory(r);
        vis.setRendererFactory(fr);
// create our nominal color palette
// pink for females, baby blue for males
        int[] palette = new int[]{
            ColorLib.rgb(255, 180, 180), ColorLib.rgb(190, 190, 255)
        };
// map nominal data values to colors using our provided palette
        DataColorAction fill = new DataColorAction("graph.nodes", "gender",
                Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
// use black for node text
        ColorAction text = new ColorAction("graph.nodes",
                VisualItem.TEXTCOLOR, ColorLib.gray(0));
// use light grey for edges
        ColorAction edges = new ColorAction("graph.edges",
                VisualItem.STROKECOLOR, ColorLib.gray(200));

// create an action list containing all color assignments
        ActionList color = new ActionList();
        color.add(fill);
        color.add(text);
        color.add(edges);
// create an action list with an animated layout
// the INFINITY parameter tells the action list to run indefinitely
        ActionList layout = new ActionList(Activity.INFINITY);
        layout.add(new ForceDirectedLayout("graph"));
        layout.add(new RepaintAction());

// add the actions to the visualization
        vis.putAction("color", color);
        vis.putAction("layout", layout);
// create a new Display that pull from our Visualization


        display.addControlListener(new DragControl()); // drag items around

        display.addControlListener(new PanControl());  // pan with background left-drag

        display.addControlListener(new ZoomControl()); // zoom with vertical right-drag
// create a new window to hold the visualization

        //  Messenger.Message("sasas");
        JInternalFrame frame = new JInternalFrame("prefuse example");
// ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.pack();           // layout components in window

        frame.setVisible(true); // show the window

        cytoDesk.add(frame);

        vis.run("color");  // assign the colors

        vis.run("layout"); // start up the animated layout
    //   JInternalFrame palette = new JInternalFrame("Palette", true, true, true,
    //           true);

    }
}
