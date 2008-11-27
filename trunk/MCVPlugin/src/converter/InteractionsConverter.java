package converter;

import java.util.Collection;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoInteraction;
import structs.model.CytoProtein;
import structs.model.Interaction;

public class InteractionsConverter {

    static void convertNetworkInteractions(CytoAbstractPPINetwork cytoPPINetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {
            CytoProtein source = cytoPPINetwork.getCytoProtein(interaction.getSource().getID());
            CytoProtein target = cytoPPINetwork.getCytoProtein(interaction.getTarget().getID());

            CytoInteraction cytoInteraction = new CytoInteraction(interaction.getID(), interaction, source, target, cytoPPINetwork);
            cytoPPINetwork.addCytoInteraction(cytoInteraction);
        }

    }
}
