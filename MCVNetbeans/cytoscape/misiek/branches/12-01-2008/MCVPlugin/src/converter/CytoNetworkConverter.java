package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.layout.CyLayoutAlgorithm;
import cytoscape.layout.CyLayouts;
import cytoscape.view.CyNetworkView;
import cytoscape.visual.VisualStyle;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork cyNetwork = Cytoscape.createNetwork(cytoNetwork.getID(), true);
            cytoNetwork.setCytoID(cyNetwork.getIdentifier());
            CytoDataHandle.addNetworkIDMapping(cyNetwork.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(cyNetwork, cytoNetwork.getCytoProteins());
            CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

            CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);

            applyVisualStyleForNetwork(cyNetwork, cyNetworkView);
            applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
        }
    }

    private static void applyCyLayoutAlgorithm(CyNetwork cyNetwork, CyNetworkView cyNetworkView) {
        Cytoscape.getVisualMappingManager().setNetworkView(cyNetworkView);
        CyLayoutAlgorithm layout = CyLayouts.getDefaultLayout();
        layout.doLayout(cyNetworkView);
        cyNetworkView.setZoom(0.7);
    }

    static void applyVisualStyleForNetwork(CyNetwork cyNetwork, CyNetworkView cyNetworkView) {
        VisualStyle MCVStyle = Cytoscape.getVisualMappingManager().getCalculatorCatalog().getVisualStyle("MCVStyle");
        Cytoscape.getVisualMappingManager().setVisualStyle(MCVStyle);
    }
}
