package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import main.CytoDataHandle;
import structs.model.CytoProtein;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(CyNetwork newNet, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {

            CyNode node = Cytoscape.getCyNode(cytoProtein.getCytoID(), true);
            node.setIdentifier(cytoProtein.getCytoID());
            newNet.addNode(node.getRootGraphIndex());
            CytoDataHandle.addCytoProteinMapping(node.getRootGraphIndex(), cytoProtein);
        }
    }
}
