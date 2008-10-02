package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import structs.ProteinProjection;

class ProteinProjectionsConverter {

    static void convertProteinProjections(CyNetwork cyNetwork, Collection<ProteinProjection> proteinProjections) {
        for (ProteinProjection proteinProjection : proteinProjections) {

            CyNode node = Cytoscape.getCyNode(proteinProjection.getID(), true);
            cyNetwork.addNode(node.getRootGraphIndex());
        }
    }
}
