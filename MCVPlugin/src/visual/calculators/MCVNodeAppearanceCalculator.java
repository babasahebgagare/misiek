package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoProtein;
import structs.model.Family;
import structs.model.Protein;

public class MCVNodeAppearanceCalculator extends NodeAppearanceCalculator {

    @Override
    public void calculateNodeAppearance(NodeAppearance appr, Node node, CyNetwork cyNetwork) {
        super.calculateNodeAppearance(appr, node, cyNetwork);

        CytoAbstractPPINetwork cytoNetwork = CytoDataHandle.findNetworkByCytoID(cyNetwork.getIdentifier());
        if (cytoNetwork != null) {
            CytoProtein cytoProtein = CytoDataHandle.getCytoProteinByIndex(node.getRootGraphIndex());
            if (cytoProtein != null) {
                Protein protein = cytoProtein.getProtein();
                if (protein != null) {
                    Family family = protein.getFamily();
                    appr.set(VisualPropertyType.NODE_LABEL, protein.getID());
                    appr.set(VisualPropertyType.NODE_FILL_COLOR, family.getColor());
                    appr.set(VisualPropertyType.NODE_TOOLTIP, family.getFamilyID());
                    appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ROUND_RECT);
                }
            }
        }
    }
}
