package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
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

        }
    }

    private static void applyVisualStyleForNetwork(CyNetwork cyNetwork) {
      //  CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
      //  Cytoscape.getVisualMappingManager().setNetworkView(cyNetworkView);
        VisualStyle MCVStyle = Cytoscape.getVisualMappingManager().getCalculatorCatalog().getVisualStyle("MCVStyle");
        Cytoscape.getVisualMappingManager().setVisualStyle(MCVStyle);
    }
}
