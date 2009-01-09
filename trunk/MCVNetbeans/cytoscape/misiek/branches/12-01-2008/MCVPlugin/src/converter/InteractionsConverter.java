package converter;

import java.util.Collection;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoInteraction;
import structs.model.CytoProtein;
import structs.model.Interaction;
import utils.IDCreator;

public class InteractionsConverter {

    public static void convertNetworkInteractions(CytoAbstractPPINetwork cytoPPINetwork, Collection<Interaction> interactions) {
        for (Interaction interaction : interactions) {
            String sourceID = IDCreator.createProteinProjectionID(interaction.getSource(), cytoPPINetwork);
            String targetID = IDCreator.createProteinProjectionID(interaction.getTarget(), cytoPPINetwork);

            CytoProtein source = cytoPPINetwork.getCytoProtein(sourceID);
            CytoProtein target = cytoPPINetwork.getCytoProtein(targetID);
            String CytoInteractionID = IDCreator.createInteractionProjectionID(interaction, cytoPPINetwork);

            CytoInteraction cytoInteraction = new CytoInteraction(CytoInteractionID, interaction, source, target, cytoPPINetwork);
            cytoPPINetwork.addCytoInteraction(cytoInteraction);
        }

    }
}
