package visual.layout;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.view.NodeView;
import java.util.Collection;
import main.DataHandle;
import structs.GroupNode;
import structs.PPINetworkProjection;
import structs.ProteinProjection;

public class Layouter {

    public static void AllProjectionsLayout() {

        Collection<PPINetworkProjection> projections = DataHandle.getProjections().values();

        for (PPINetworkProjection projection : projections) {
            ProjectionLayout(projection, Cytoscape.getNetworkView(projection.getCytoID()));
        }
    }

    public static void ProjectionLayout(PPINetworkProjection projection, CyNetworkView networkView) {
        Collection<GroupNode> groupNodes = projection.getGroupNodes().values();

        for (GroupNode groupNode : groupNodes) {

            CyNode node = Cytoscape.getCyNode(groupNode.getID());
            GroupNodeLayout(groupNode, networkView.getNodeView(node), networkView);
        }
    }

    public static void GroupNodeLayout(GroupNode groupNode, NodeView nodeView, CyNetworkView networkView) {
        Collection<ProteinProjection> proteinProjections = groupNode.getContext().getInsideProteins();

        for (ProteinProjection proteinProjection : proteinProjections) {

            CyNode node = Cytoscape.getCyNode(proteinProjection.getID());
            NodeView proteinView = networkView.getNodeView(node);
            proteinView.setXPosition(nodeView.getXPosition());
            proteinView.setYPosition(nodeView.getYPosition());
        }
    }
}
