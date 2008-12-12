package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoInteraction;

public class MCVEdgeAppearanceCalculator extends EdgeAppearanceCalculator {

    @Override
    public void calculateEdgeAppearance(EdgeAppearance appr, Edge edge, CyNetwork cyNetwork) {
        super.calculateEdgeAppearance(appr, edge, cyNetwork);

        CytoAbstractPPINetwork cytoNetwork = CytoDataHandle.findNetworkByCytoID(cyNetwork.getIdentifier());

        if (cytoNetwork != null) {
            CytoInteraction cytoInteraction = CytoDataHandle.getCytoInteractionByIndex(edge.getRootGraphIndex());
            double probability = cytoInteraction.getProbability().doubleValue();

            appr.set(VisualPropertyType.EDGE_LINE_WIDTH, new Float(probability * 5.0)); //TODO - BAD CONST
            appr.set(VisualPropertyType.EDGE_TOOLTIP, String.valueOf(probability));

        }
    }

    private double calculateCytoInteractionWidth(double probability) {
        return probability * probability * 5;
    }
}
