package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import structs.model.GroupNode;

class GroupNodesConverter {

    static void convertGroupNodes(CyNetwork cyNetwork, Collection<GroupNode> groupNodes) {
        for (GroupNode groupNode : groupNodes) {

            CyNode node = Cytoscape.getCyNode(groupNode.getID(), true);
            cyNetwork.addNode(node.getRootGraphIndex());
        }
    }
}
