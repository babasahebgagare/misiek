package converter;

import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import java.util.Collection;
import structs.Interaction;

public class InteractionsConverter {

    static void convertNetworkInteractions(CyNetwork cyNetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {

            CyEdge edge = Cytoscape.getCyEdge(interaction.getSourceID(), interaction.getID(), interaction.getTargetID(), "TEST");
            cyNetwork.addEdge(edge.getRootGraphIndex());

        }
    }
}
