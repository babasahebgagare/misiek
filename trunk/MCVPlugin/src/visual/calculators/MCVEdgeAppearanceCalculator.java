package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import main.DataHandle;
import structs.PPINetwork;
import structs.Interaction;

public class MCVEdgeAppearanceCalculator extends EdgeAppearanceCalculator {

    @Override
    public void calculateEdgeAppearance(EdgeAppearance appr, Edge edge, CyNetwork cyNetwork) {
        super.calculateEdgeAppearance(appr, edge, cyNetwork);

        PPINetwork network = DataHandle.findNetworkByCytoID(cyNetwork.getIdentifier());
        if (network != null) {
            Interaction interaction = network.getInteraction(edge.getIdentifier());

            appr.set(VisualPropertyType.EDGE_LINE_WIDTH, interaction.getProbability() * 5); //TODO - BAD CONST

        }
    }
}
