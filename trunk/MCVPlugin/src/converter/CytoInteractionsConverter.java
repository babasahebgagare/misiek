package converter;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.data.Semantics;
import java.util.Collection;
import structs.model.CytoInteraction;
import structs.model.Interaction;

class CytoInteractionsConverter {

    static void convertCytoNetworkInteractions(CyNetwork cyNetwork, Collection<CytoInteraction> cytoInteractions) {
        for (CytoInteraction cytoInteraction : cytoInteractions) {

            Interaction interaction = cytoInteraction.getInteraction();

            CyEdge edge = Cytoscape.getCyEdge(interaction.getSourceID(), interaction.getID(), interaction.getTargetID(), Semantics.INTERACTION);
            edge.setIdentifier(interaction.getID());
            cyNetwork.addEdge(edge.getRootGraphIndex());
        }
    }
}
