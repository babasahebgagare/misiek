package converter;

import java.util.Collection;
import structs.model.CytoInteraction;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.Interaction;

public class InteractionsConverter {

    static void convertNetworkInteractions(CytoPPINetwork cytoPPINetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {
            CytoProtein source = cytoPPINetwork.getCytoProtein(interaction.getSourceID());
            CytoProtein target = cytoPPINetwork.getCytoProtein(interaction.getTargetID());

            CytoInteraction cytoInteraction = new CytoInteraction(interaction.getID(), interaction, source, target, cytoPPINetwork);
            cytoPPINetwork.addCytoInteraction(cytoInteraction);
        }

    }
}
