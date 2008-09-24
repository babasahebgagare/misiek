package converter;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import utils.Messenger;

public class SimpleCytoNetwork {

    public static void createNetwork() {

        CyNetwork cyNetwork = Cytoscape.createNetwork("TEST", true);

        CyNode node = Cytoscape.getCyNode("NODE", true);
        //  CyEdge edge = Cytoscape.getCyEdge(arg0, arg1, arg2, arg3);
        CyEdge edge = Cytoscape.getCyEdge("NODE", "EDGE", "NODE", "aaa");
        cyNetwork.addNode(node.getRootGraphIndex());
        cyNetwork.addEdge(edge.getRootGraphIndex());
        Messenger.Message(cyNetwork.getIdentifier() + node.getRootGraphIndex() + edge.getRootGraphIndex() + edge.getIdentifier());

    }
}
