package visual.calculators;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.visual.NodeAppearance;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.NodeShape;
import cytoscape.visual.VisualPropertyType;
import giny.model.Node;
import java.awt.Color;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoProtein;
import logicmodel.structs.Family;
import logicmodel.structs.Protein;
import main.PluginDataHandle;
import viewmodel.structs.CytoProteinProjection;

public class MCVNodeAppearanceCalculator extends NodeAppearanceCalculator {

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
                            appr.set(VisualPropertyType.NODE_TOOLTIP, "Rodzina: " + family.getFamilyID() + ", przodek: " + parent.getID());
                        } else {
                            appr.set(VisualPropertyType.NODE_TOOLTIP, "Rodzina: " + family.getFamilyID());
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
