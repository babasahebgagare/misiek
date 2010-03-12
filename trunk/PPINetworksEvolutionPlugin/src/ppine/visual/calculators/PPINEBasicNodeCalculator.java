/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppine.visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.visual.Appearance;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import cytoscape.visual.calculators.BasicCalculator;
import cytoscape.visual.mappings.ObjectMapping;
import giny.model.Node;
import java.awt.Color;
import java.util.Properties;
import ppine.logicmodel.structs.Family;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoProtein;
import ppine.viewmodel.structs.CytoProteinProjection;

/**
 *
 * @author misiek
 */
public class PPINEBasicNodeCalculator extends BasicCalculator {

    public PPINEBasicNodeCalculator(String name, Properties props, String baseKey, VisualPropertyType type) {
        super(name, props, baseKey, type);
    }

    public PPINEBasicNodeCalculator(String name, ObjectMapping m, VisualPropertyType type) {
        super(name, m, type);
    }

    @Override
    public void apply(Appearance appr, Node node, CyNetwork cyNetwork) {
       // System.out.println("nowy");
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        //  System.out.println("calc...");
        if (cdh == null) {
            return;
        }
        //  System.out.println("calculator");
        CytoAbstractPPINetwork cytoNetworkOrNull = cdh.tryFindNetworkByCytoID(cyNetwork.getIdentifier());
        if (cytoNetworkOrNull != null) {
            CytoProtein cytoProteinOrNull = cdh.tryGetCytoProteinByIndex(node.getRootGraphIndex());
            if (cytoProteinOrNull != null) {
                Protein protein = cytoProteinOrNull.getProtein();
                Family family = protein.getFamily();

                if (cytoProteinOrNull.getClass().equals(CytoProteinProjection.class)) {
                    /* projected protein */

               //     System.out.println("projected protein");
                    CytoProteinProjection cytoProteinProjection = (CytoProteinProjection) cytoProteinOrNull;
                    CytoProtein cytoMotherProtein = cytoProteinProjection.tryGetCytoMotherProtein();
                    if (cytoMotherProtein != null) {
                        Color manColorOrNull = tryGetManuallyChangedColor((NodeAppearance) appr, node);
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
                    /* typical protein */
                    Color manColor = tryGetManuallyChangedColor((NodeAppearance) appr, node);
                    if (manColor == null) {
                        appr.set(VisualPropertyType.NODE_FILL_COLOR, family.getColor());
                    } else {
                        appr.set(VisualPropertyType.NODE_FILL_COLOR, manColor);
                    }
//                    System.out.println("common protein");

                    appr.set(VisualPropertyType.NODE_TOOLTIP, "Family: " + family.getFamilyID());
                }

                appr.set(VisualPropertyType.NODE_LABEL, protein.getID());
                appr.set(VisualPropertyType.NODE_SHAPE, NodeShape.ROUND_RECT);
           //     System.out.println("Setting visual styles");
            }
        }

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
    }
}
