package converter;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.data.Semantics;
import java.util.Collection;
import structs.model.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(CyNetwork cyNetwork, Collection<CytoInteraction> cytoInteractions) {
        for (CytoInteraction cytoInteraction : cytoInteractions) {

            CyEdge edge = Cytoscape.getCyEdge(cytoInteraction.getSource().getCytoID(), cytoInteraction.getCytoID(), cytoInteraction.getTarget().getCytoID(), Semantics.INTERACTION);
            edge.setIdentifier(cytoInteraction.getCytoID());
            cyNetwork.addEdge(edge.getRootGraphIndex());
        }
    }
}
