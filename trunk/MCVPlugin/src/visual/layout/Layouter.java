package visual.layout;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.view.NodeView;
import java.util.Collection;
import structs.model.CytoGroupNode;
import structs.model.CytoPPINetworkProjection;
import structs.model.CytoProtein;

public class Layouter {

    private static double R = 30.0;  //TODO bad constant

    public static void GroupNodeLayout(CytoGroupNode cytoGroupNode, CyNetworkView cyNetworkView) {
        Collection<CytoProtein> cytoProteins = cytoGroupNode.getContext().getInsideProteins();

        CytoProtein parentProtein = cytoGroupNode.getContext().getMotherProtein();
        CyNetworkView parentNetworkView = Cytoscape.getNetworkView(parentProtein.getCytoNetowork().getCytoID());
        CyNode parentNode = Cytoscape.getCyNode(parentProtein.getCytoID());
        NodeView nodeView = parentNetworkView.getNodeView(parentNode);


        int i = 0;
        int count = cytoProteins.size();

        for (CytoProtein cytoProtein : cytoProteins) {

            CyNode node = Cytoscape.getCyNode(cytoProtein.getCytoID());
            NodeView proteinView = cyNetworkView.getNodeView(node);

            setProteinViewPosition(proteinView, nodeView, i, count);
            i++;
        }
    }

    public static void ProjectionsLayout(CytoPPINetworkProjection projection) {
        CyNetworkView cyNetworkView =Cytoscape.getNetworkView(projection.getCytoID());
        ProjectionLayout(projection, cyNetworkView);
    }

    public static void ProjectionLayout(CytoPPINetworkProjection projection, CyNetworkView cyNetworkView) {
        Collection<CytoGroupNode> cytoGroupNodes = projection.getCytoGroupNodes();

        for (CytoGroupNode cytoGroupNode : cytoGroupNodes) {
            GroupNodeLayout(cytoGroupNode, cyNetworkView);
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
    }
}
