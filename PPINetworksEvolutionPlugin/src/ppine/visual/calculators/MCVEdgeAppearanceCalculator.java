package mcv.visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import java.awt.Color;
import mcv.main.PluginDataHandle;
import mcv.viewmodel.controllers.CytoDataHandle;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;
import mcv.viewmodel.structs.CytoExpInteraction;
import mcv.viewmodel.structs.CytoInteraction;

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
            if (cytoInteraction != null) {
                double probability = cytoInteraction.getProbability().doubleValue();

                appr.set(VisualPropertyType.EDGE_LINE_WIDTH, new Float(probability * 5.0)); //TODO - BAD CONST
                appr.set(VisualPropertyType.EDGE_TOOLTIP, String.valueOf(probability));
                appr.set(VisualPropertyType.EDGE_COLOR, Color.GRAY);
            }

            CytoExpInteraction cytoExpInteraction = cdh.getCytoExpInteractionByIndex(edge.getRootGraphIndex());
            if (cytoExpInteraction != null) {

                appr.set(VisualPropertyType.EDGE_LINE_WIDTH, 5.0); //TODO - BAD CONST
                appr.set(VisualPropertyType.EDGE_TOOLTIP, "Experiment ID: " + cytoExpInteraction.getExp().getExpID());
                appr.set(VisualPropertyType.EDGE_COLOR, cytoExpInteraction.getExp().getColor());
            }
        }
        appr.applyBypass(edge);
    }

    /*private double calculateCytoInteractionWidth(double probability) {
    return probability * probability * 5;
    }*/
}
