/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.visual.EdgeAppearance;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import giny.model.Edge;
import java.awt.Color;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoExpInteraction;
import ppine.viewmodel.structs.CytoInteraction;

public class PPINEEdgeAppearanceCalculator extends EdgeAppearanceCalculator {

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

                appr.set(VisualPropertyType.EDGE_LINE_WIDTH, 4.0); //TODO - BAD CONST
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
