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
            CyNetwork newNet = Cytoscape.createNetwork(cytoNetwork.getID(), true);
            cytoNetwork.setCytoID(newNet.getIdentifier());
            CytoDataHandle.addNetworkIDMapping(newNet.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(newNet, cytoNetwork.getCytoProteins());
            CytoInteractionsConverter.convertCytoNetworkInteractions(newNet, cytoNetwork.getCytoInteractions());

            applyVisualStyleForNetwork(newNet);
            applyCyLayoutAlgorithm(newNet);
        }
    }

    private static void applyCyLayoutAlgorithm(CyNetwork cyNetwork) {
        CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
        Cytoscape.getVisualMappingManager().setNetworkView(cyNetworkView);
        CyLayoutAlgorithm layout = CyLayouts.getDefaultLayout();
        layout.doLayout(cyNetworkView);
        cyNetworkView.setZoom(0.7);
    }

    private static void applyVisualStyleForNetwork(CyNetwork cyNetwork) {
        VisualStyle MCVStyle = Cytoscape.getVisualMappingManager().getCalculatorCatalog().getVisualStyle("MCVStyle");
        Cytoscape.getVisualMappingManager().setVisualStyle(MCVStyle);
    }
}
