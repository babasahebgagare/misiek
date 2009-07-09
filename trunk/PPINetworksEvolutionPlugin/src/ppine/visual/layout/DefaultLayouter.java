package ppine.visual.layout;

import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.model.Node;
import giny.view.NodeView;
import java.util.Collection;
import ppine.viewmodel.structs.CytoGroupNode;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.viewmodel.structs.CytoProteinProjection;

public class DefaultLayouter extends Layouter {

    private static double R = 30.0;  //TODO bad constant

    private static void groupNodeLayout(CytoGroupNode cytoGroupNode, CyNetworkView cyNetworkView) {
        Collection<CytoProtein> cytoProteins = cytoGroupNode.getContext().getInsideProteins();

        CytoProtein parentProtein = cytoGroupNode.getContext().getMotherProtein();
        String parentNetworkCytoID = parentProtein.getCytoNetwork().getCytoID();
        CyNetworkView parentNetworkView = Cytoscape.getNetworkView(parentNetworkCytoID);
        Node parentNode = Cytoscape.getRootGraph().getNode(parentProtein.getIndex());
        NodeView parentNodeView = parentNetworkView.getNodeView(parentNode);


        int i = 0;
        int count = cytoProteins.size();

        for (CytoProtein cytoProtein : cytoProteins) {

            Node node = Cytoscape.getRootGraph().getNode(cytoProtein.getIndex());
            NodeView proteinView = cyNetworkView.getNodeView(node);

            setProteinViewPosition(proteinView, parentNodeView, i, count);
            i++;
        }
    }

    @Override
    public void projectionToDownLayout(CytoPPINetworkProjectionToDown projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        System.out.println("getting net: " + cyNetworkView.getTitle());
        projectionToDownLayout(projection, cyNetworkView);
    }

    private void projectionToDownLayout(CytoPPINetworkProjectionToDown projection, CyNetworkView cyNetworkView) {
        Collection<CytoGroupNode> cytoGroupNodes = projection.getCytoGroupNodes();

        for (CytoGroupNode cytoGroupNode : cytoGroupNodes) {
            groupNodeLayout(cytoGroupNode, cyNetworkView);
        }
    }

    @Override
    public void projectionToUpLayout(CytoPPINetworkProjectionToUp projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        projectionToUpLayout(projection, cyNetworkView);
    }

    private void projectionToUpLayout(CytoPPINetworkProjectionToUp projection, CyNetworkView cyNetworkView) {


        for (CytoProtein cytoProteinProjection : projection.getCytoProteins()) {
            CytoProtein cytoMotherProteinOrNull = ((CytoProteinProjection) cytoProteinProjection).tryGetCytoMotherProtein();

            if (cytoMotherProteinOrNull != null) {
                String motherNetworkCytoID = cytoMotherProteinOrNull.getCytoNetwork().getCytoID();
                CyNetworkView parentNetworkView = Cytoscape.getNetworkView(motherNetworkCytoID);
                Node parentNode = Cytoscape.getRootGraph().getNode(cytoMotherProteinOrNull.getIndex());
                NodeView nodeView = parentNetworkView.getNodeView(parentNode);

                Node node = Cytoscape.getRootGraph().getNode(cytoProteinProjection.getIndex());
                NodeView proteinView = cyNetworkView.getNodeView(node);
                proteinView.setXPosition(nodeView.getXPosition());
                proteinView.setYPosition(nodeView.getYPosition());
            }
        }

    }

    private static void setProteinViewPosition(NodeView proteinView, NodeView parentNodeView, int i, int count) {
        if (count == 1) {
            proteinView.setXPosition(parentNodeView.getXPosition());
            proteinView.setYPosition(parentNodeView.getYPosition());
        } else {
            double arg = 2 * Math.PI * i / count;
            double x_offset = R * Math.cos(arg);
            double y_offset = R * Math.sin(arg);
            proteinView.setXPosition(parentNodeView.getXPosition() + x_offset);
            proteinView.setYPosition(parentNodeView.getYPosition() + y_offset);
        }
    }
}
