package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import java.awt.Color;
import main.DataHandle;
import structs.Family;
import structs.GroupNode;
import structs.PPINetworkProjection;
import structs.ProteinProjection;

public class MCVNodeProjectionedAppearanceCalculator extends NodeAppearanceCalculator {

    @Override
    public void calculateNodeAppearance(NodeAppearance appr, Node node, CyNetwork cyNetwork) {
        super.calculateNodeAppearance(appr, node, cyNetwork);

        PPINetworkProjection projection = DataHandle.findProjectionByCytoID(cyNetwork.getIdentifier());
        if (projection != null) {
            GroupNode groupNode = projection.getGroupNode(node.getIdentifier());
            if (groupNode != null) {
                appr.set(VisualPropertyType.NODE_LABEL, groupNode.getID());
                appr.set(VisualPropertyType.NODE_FILL_COLOR, new Color(10, 150, 10));   // TODO bad constanse

                appr.set(VisualPropertyType.NODE_SIZE, 100);
                appr.set(VisualPropertyType.NODE_OPACITY, 0.5);
            }
            ProteinProjection proteinProjection = projection.getProteinProjection(node.getIdentifier());
            if (proteinProjection != null) {
                Family family = proteinProjection.getContext().getMotherProtein().getFamily();
                appr.set(VisualPropertyType.NODE_LABEL, proteinProjection.getID());
                appr.set(VisualPropertyType.NODE_FILL_COLOR, family.getColor());
            }
        }
    }
}
