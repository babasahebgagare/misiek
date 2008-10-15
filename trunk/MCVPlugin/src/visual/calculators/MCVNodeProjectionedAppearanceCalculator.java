package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import giny.view.NodeView;
import java.awt.Color;
import main.DataHandle;
import structs.Family;
import structs.GroupNode;
import structs.PPINetworkProjection;
import structs.Protein;
import structs.ProteinProjection;

public class MCVNodeProjectionedAppearanceCalculator extends NodeAppearanceCalculator {

    @Override
    public void calculateNodeAppearance(NodeAppearance appr, Node node, CyNetwork cyNetwork) {
        super.calculateNodeAppearance(appr, node, cyNetwork);

        PPINetworkProjection projection = DataHandle.findProjectionByCytoID(cyNetwork.getIdentifier());
        if (projection != null) {
            GroupNode groupNode = projection.getGroupNode(node.getIdentifier());
            if (groupNode != null) {
                calculateGroupNodeAppearance(appr, node, groupNode);
                setGroupNodePosition(node, groupNode, cyNetwork);

            }
            ProteinProjection proteinProjection = projection.getProteinProjection(node.getIdentifier());
            if (proteinProjection != null) {
                calculateProjectionedProteinAppearance(appr, proteinProjection);
            }
        }
    }

    private void calculateGroupNodeAppearance(NodeAppearance appr, Node node, GroupNode groupNode) {
        appr.set(VisualPropertyType.NODE_LABEL, groupNode.getID());
        appr.set(VisualPropertyType.NODE_FILL_COLOR, new Color(10, 150, 10));   // TODO bad constanse

        appr.set(VisualPropertyType.NODE_SIZE, 120);

        appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ELLIPSE);
        appr.set(VisualPropertyType.NODE_OPACITY, 0.5);
    }

    private void calculateProjectionedProteinAppearance(NodeAppearance appr, ProteinProjection proteinProjection) {
        Family family = proteinProjection.getProjectionContext().getMotherProtein().getFamily();
        appr.set(VisualPropertyType.NODE_LABEL, proteinProjection.getID());
        appr.set(VisualPropertyType.NODE_FILL_COLOR, family.getColor());
    }

    private void setGroupNodePosition(Node node, GroupNode groupNode, CyNetwork cyNetwork) {
        Protein motherProtein = groupNode.getContext().getMotherProtein();

        CyNode parentCyNode = Cytoscape.getCyNode(motherProtein.getID());
        CyNetworkView parentNetworkView = Cytoscape.getNetworkView(motherProtein.getContext().getNetwork().getCytoID());
        NodeView parentNodeView = parentNetworkView.getNodeView(parentCyNode);

        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(cyNetwork.getIdentifier());

        NodeView nodeView = cyNetworkView.getNodeView(node);

        nodeView.setXPosition(parentNodeView.getXPosition());
        nodeView.setYPosition(parentNodeView.getYPosition());

    }
}
