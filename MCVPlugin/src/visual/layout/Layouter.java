package visual.layout;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.view.NodeView;
import java.util.Collection;
import structs.model.CytoGroupNode;
import structs.model.CytoPPINetworkProjectionToDown;
import structs.model.CytoPPINetworkProjectionToUp;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;

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

    public static void ProjectionToDownLayout(CytoPPINetworkProjectionToDown projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        ProjectionToDownLayout(projection, cyNetworkView);
    }

    public static void ProjectionToDownLayout(CytoPPINetworkProjectionToDown projection, CyNetworkView cyNetworkView) {
        Collection<CytoGroupNode> cytoGroupNodes = projection.getCytoGroupNodes();

        for (CytoGroupNode cytoGroupNode : cytoGroupNodes) {
            GroupNodeLayout(cytoGroupNode, cyNetworkView);
        }
    }

    public static void ProjectionToUpLayout(CytoPPINetworkProjectionToUp projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        ProjectionToUpLayout(projection, cyNetworkView);
    }

    public static void ProjectionToUpLayout(CytoPPINetworkProjectionToUp projection, CyNetworkView cyNetworkView) {


        for (CytoProteinProjection cytoProteinProjection : projection.getCytoProteinsProjections()) {
            CytoProtein cytoMotherProtein = cytoProteinProjection.getCytoMotherProtein();

            CyNetworkView parentNetworkView = Cytoscape.getNetworkView(cytoMotherProtein.getCytoNetowork().getCytoID());
            CyNode parentNode = Cytoscape.getCyNode(cytoMotherProtein.getCytoID());
            NodeView nodeView = parentNetworkView.getNodeView(parentNode);

            CyNode node = Cytoscape.getCyNode(cytoProteinProjection.getCytoID());
            NodeView proteinView = cyNetworkView.getNodeView(node);
            proteinView.setXPosition(nodeView.getXPosition());
            proteinView.setYPosition(nodeView.getYPosition());
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
