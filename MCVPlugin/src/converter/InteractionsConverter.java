package converter;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.data.Semantics;
import java.util.Collection;
import structs.Interaction;

public class InteractionsConverter {

    static void convertNetworkInteractions(CyNetwork cyNetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {

            CyEdge edge = Cytoscape.getCyEdge(interaction.getSourceID(), interaction.getID(), interaction.getTargetID(), Semantics.INTERACTION);
            edge.setIdentifier(interaction.getID());
            cyNetwork.addEdge(edge.getRootGraphIndex());
        }
    }
}
