package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import structs.model.Protein;

public class ProteinsConverter {

    static void convertNetworkProteins(CyNetwork cyNetwork, Collection<Protein> proteins) {

        for (Protein protein : proteins) {
            
            CyNode node = Cytoscape.getCyNode(protein.getID(), true);
            cyNetwork.addNode(node.getRootGraphIndex());
        }
    }
}
