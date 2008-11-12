package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import structs.model.CytoProtein;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(CyNetwork newNet, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {

            CyNode node = Cytoscape.getCyNode(cytoProtein.getCytoID(), true);
            newNet.addNode(node.getRootGraphIndex());
        }
    }
}
