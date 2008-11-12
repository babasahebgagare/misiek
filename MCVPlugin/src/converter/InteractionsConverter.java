package converter;

import java.util.Collection;
import structs.model.CytoInteraction;
import structs.model.CytoPPINetwork;
import structs.model.Interaction;

public class InteractionsConverter {

    static void convertNetworkInteractions(CytoPPINetwork cytoPPINetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {
            CytoInteraction cytoInteraction = new CytoInteraction(interaction.getID(), interaction);
            cytoPPINetwork.addCytoInteraction(cytoInteraction);
        }

    }
}
