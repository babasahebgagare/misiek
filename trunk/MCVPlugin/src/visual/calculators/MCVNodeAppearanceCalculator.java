package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.Family;
import logicmodel.structs.Protein;

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
                    Protein parent = protein.getContext().getParentProtein();
                    if (parent != null) {
                        appr.set(VisualPropertyType.NODE_TOOLTIP, "Rodzina: " + family.getFamilyID() + ", przodek: " + parent.getID());
                    } else {
                        appr.set(VisualPropertyType.NODE_TOOLTIP, "Rodzina: " + family.getFamilyID());
                    }
                    appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ROUND_RECT);
                }
            }
        }
    }
}
