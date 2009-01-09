package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoInteraction;
import structs.model.Interaction;

public class MCVEdgeAppearanceCalculator extends EdgeAppearanceCalculator {

    @Override
    public void calculateEdgeAppearance(EdgeAppearance appr, Edge edge, CyNetwork cyNetwork) {
        super.calculateEdgeAppearance(appr, edge, cyNetwork);

        CytoAbstractPPINetwork cytoNetwork = CytoDataHandle.findNetworkByCytoID(cyNetwork.getIdentifier());
        if (cytoNetwork != null) {
            CytoInteraction cytoInteraction = cytoNetwork.getCytoInteraction(edge.getIdentifier());

            Interaction interaction = cytoInteraction.getInteraction();
            appr.set(VisualPropertyType.EDGE_LINE_WIDTH, calculateCytoInteractionWidth(interaction.getProbability().doubleValue())); //TODO - BAD CONST
            appr.set(VisualPropertyType.EDGE_TOOLTIP, String.valueOf(interaction.getProbability().doubleValue()));

        }
    }

    private double calculateCytoInteractionWidth(double probability) {
        return probability*probability*5;
    }
}
