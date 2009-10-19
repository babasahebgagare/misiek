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
import cytoscape.Cytoscape;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import java.awt.Color;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoProtein;
import ppine.logicmodel.structs.Family;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.structs.CytoProteinProjection;

public class PPINENodeAppearanceCalculator extends NodeAppearanceCalculator {

    @Override
    public void calculateNodeAppearance(NodeAppearance appr, Node node, CyNetwork cyNetwork) {
        super.calculateNodeAppearance(appr, node, cyNetwork);
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        if (cdh == null) {
            return;
        }

        CytoAbstractPPINetwork cytoNetworkOrNull = cdh.tryFindNetworkByCytoID(cyNetwork.getIdentifier());
        if (cytoNetworkOrNull != null) {
            CytoProtein cytoProteinOrNull = cdh.tryGetCytoProteinByIndex(node.getRootGraphIndex());
            if (cytoProteinOrNull != null) {
                Protein protein = cytoProteinOrNull.getProtein();
                Family family = protein.getFamily();

                if (cytoProteinOrNull.getClass().equals(CytoProteinProjection.class)) {
                    CytoProteinProjection cytoProteinProjection = (CytoProteinProjection) cytoProteinOrNull;
                    CytoProtein cytoMotherProtein = cytoProteinProjection.tryGetCytoMotherProtein();
                    if (cytoMotherProtein != null) {
                        Color manColorOrNull = tryGetManuallyChangedColor(appr, node);
                        if (manColorOrNull == null) {

                            int index = cytoMotherProtein.getIndex();
                            Node motherNode = Cytoscape.getRootGraph().getNode(index);
                            NodeAppearance app = Cytoscape.getVisualMappingManager().getVisualStyle().getNodeAppearanceCalculator().calculateNodeAppearance(motherNode, cyNetwork);
                            Color fillColor = (Color) app.get(VisualPropertyType.NODE_FILL_COLOR);
                            appr.set(VisualPropertyType.NODE_FILL_COLOR, fillColor);

                        } else {
                            appr.set(VisualPropertyType.NODE_FILL_COLOR, manColorOrNull);
                        }

                        Protein parent = protein.getContext().tryGetParentProtein();
                        if (parent != null) {
                            appr.set(VisualPropertyType.NODE_TOOLTIP, "Family: " + family.getFamilyID() + ", ancestor: " + parent.getID());
                        } else {
                            appr.set(VisualPropertyType.NODE_TOOLTIP, "Family: " + family.getFamilyID());
                        }
                    } else {
                    }
                } else {

                    Color manColor = tryGetManuallyChangedColor(appr, node);
                    if (manColor == null) {
                        appr.set(VisualPropertyType.NODE_FILL_COLOR, family.getColor());
                    } else {
                        appr.set(VisualPropertyType.NODE_FILL_COLOR, manColor);
                    }

                    appr.set(VisualPropertyType.NODE_TOOLTIP, "Family: " + family.getFamilyID());
                }

                appr.set(VisualPropertyType.NODE_LABEL, protein.getID());
                appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ROUND_RECT);
            }
        }
        appr.applyBypass(node);
    }

    private Color tryGetManuallyChangedColor(NodeAppearance appr, Node node) {
        appr.applyBypass(node);
        Color bypasstmp = (Color) appr.get(VisualPropertyType.NODE_FILL_COLOR);
        Color bypasscolor = new Color(bypasstmp.getRed(), bypasstmp.getGreen(), bypasstmp.getBlue());
        if (bypasscolor.equals(Color.WHITE)) {
            return null;
        } else {
            return bypasscolor;
        }
    }
}
