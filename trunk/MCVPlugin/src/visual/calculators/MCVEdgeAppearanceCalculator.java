package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import java.awt.Color;
import main.PluginDataHandle;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoInteraction;

public class MCVEdgeAppearanceCalculator extends EdgeAppearanceCalculator {

    @Override
    public void calculateEdgeAppearance(EdgeAppearance appr, Edge edge, CyNetwork cyNetwork) {
        super.calculateEdgeAppearance(appr, edge, cyNetwork);

        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        if (cdh == null) {
            return;
        }

        CytoAbstractPPINetwork cytoNetwork = cdh.tryFindNetworkByCytoID(cyNetwork.getIdentifier());

        if (cytoNetwork != null) {
            CytoInteraction cytoInteraction = cdh.getCytoInteractionByIndex(edge.getRootGraphIndex());
            double probability = cytoInteraction.getProbability().doubleValue();

            appr.set(VisualPropertyType.EDGE_LINE_WIDTH, new Float(probability * 5.0)); //TODO - BAD CONST
            appr.set(VisualPropertyType.EDGE_TOOLTIP, String.valueOf(probability));
            appr.set(VisualPropertyType.EDGE_COLOR, Color.GRAY);

        }
        appr.applyBypass(edge);
    }

    /*private double calculateCytoInteractionWidth(double probability) {
    return probability * probability * 5;
    }*/
}
