package visual.layout;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.view.NodeView;
import java.util.Collection;
import main.DataHandle;
import structs.model.CytoGroupNode;
import structs.model.ProteinProjection;

public class Layouter {

    private static double R = 30.0;

    public static void AllProjectionsLayout() {
        /*
        Collection<PPINetworkProjection> projections = DataHandle.getProjections().values();
        
        for (PPINetworkProjection projection : projections) {
        ProjectionLayout(projection, Cytoscape.getNetworkView(projection.getCytoID()));
        }
         */
    }
    /*
    public static void ProjectionLayout(PPINetworkProjection projection, CyNetworkView networkView) {
    Collection<GroupNode> groupNodes = projection.getGroupNodes().values();
    
    for (GroupNode groupNode : groupNodes) {
    
    CyNode node = Cytoscape.getCyNode(groupNode.getID());
    GroupNodeLayout(groupNode, networkView.getNodeView(node), networkView);
    }
    }
    
    public static void GroupNodeLayout(GroupNode groupNode, NodeView nodeView, CyNetworkView networkView) {
    Collection<ProteinProjection> proteinProjections = groupNode.getContext().getInsideProteins();
    
    int i = 0;
    int count = proteinProjections.size();
    
    for (ProteinProjection proteinProjection : proteinProjections) {
    
    CyNode node = Cytoscape.getCyNode(proteinProjection.getID());
    NodeView proteinView = networkView.getNodeView(node);
    
    setProteinViewPosition(proteinView, nodeView, i, count);
    i++;
    }
    }
    
    private static void setProteinViewPosition(NodeView proteinView, NodeView nodeView, int i, int count) {
    if (count == 1) {
    proteinView.setXPosition(nodeView.getXPosition());
    proteinView.setYPosition(nodeView.getYPosition());
    } else {
    double arg = 2 * Math.PI * i / count;
    double x_offset = R * Math.cos(arg);
    double y_offset = R * Math.sin(arg);
    proteinView.setXPosition(nodeView.getXPosition() + x_offset);
    proteinView.setYPosition(nodeView.getYPosition() + y_offset);
    }
    }*/
}
