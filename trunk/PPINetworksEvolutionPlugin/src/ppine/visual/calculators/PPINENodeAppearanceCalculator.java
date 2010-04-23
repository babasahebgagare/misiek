/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
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

import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.VisualPropertyType;
import cytoscape.visual.calculators.Calculator;
import java.util.List;

public class PPINENodeAppearanceCalculator extends NodeAppearanceCalculator {

    Calculator defaultCalc = null;
    Calculator calcmy = null;

    public PPINENodeAppearanceCalculator(Calculator defaultCalc) {
        this.defaultCalc = defaultCalc;
        calcmy = new PPINEBasicNodeCalculator("PPINENodeCalculator", defaultCalc.getMappings().get(0), defaultCalc.getVisualPropertyType());
    }

    @Override
    public Calculator getCalculator(VisualPropertyType type) {
        //PassThroughMapping m = new PassThroughMapping("", AbstractCalculator.ID);
        if (calcmy.getVisualPropertyType().equals(type)) {
            return calcmy;
        } else {
            return super.getCalculator(type);
        }
    }

    @Override
    public List<Calculator> getCalculators() {
        List<Calculator> calcsl = super.getCalculators();
        calcsl.add(calcmy);
        // PassThroughMapping m = new PassThroughMapping("", AbstractCalculator.ID);
        // Calculator calcmy = new PPINEBasicNodeCalculator("aaa", m, type);

        // calcs.add(calcmy);
        return calcsl;
    }

    /*@Override
    public void calculateNodeAppearance(NodeAppearance appr, Node node, CyNetwork cyNetwork) {
    System.out.println("stary!");
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


    //      System.out.println("projected protein");
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
    //         System.out.println("common protein");

    appr.set(VisualPropertyType.NODE_TOOLTIP, "Family: " + family.getFamilyID());
    }

    appr.set(VisualPropertyType.NODE_LABEL, protein.getID());
    appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ROUND_RECT);
    //      System.out.println("Setting visual styles");
    }
    }

    // appr.applyBypass(node, new LinkedList<VisualPropertyType>());
    }

    private Color tryGetManuallyChangedColor(NodeAppearance appr, Node node) {
    //   appr.applyBypass(node, new LinkedList<VisualPropertyType>());
    Color bypasstmp = (Color) appr.get(VisualPropertyType.NODE_FILL_COLOR);
    Color bypasscolor = new Color(bypasstmp.getRed(), bypasstmp.getGreen(), bypasstmp.getBlue());
    if (bypasscolor.equals(Color.WHITE)) {
    return null;
    } else {
    return bypasscolor;
    }
    }*/
}
