package projector;

import java.util.Collection;
import main.DataHandle;
import structs.model.GroupNode;
import structs.model.Interaction;
import structs.model.PPINetwork;
import structs.model.PPINetworkProjection;
import structs.model.Protein;
import structs.model.ProteinProjection;
import utils.MemoLogger;

public class ProjectorNetwork {

    static void projectProteinsToUpOnNetwork(Collection<Protein> selectedProteins, PPINetwork networkAbove, PPINetwork networkBelow) {
        String projectionID = createProjectionID(selectedProteins, networkAbove, networkBelow);

        PPINetworkProjection projection = DataHandle.createProjectionNetwork(projectionID, networkBelow);

        for (Protein proteinBelow : selectedProteins) {

            Collection<Protein> proteinProjections = proteinBelow.getProjects().getProjectorMapUp().get(networkAbove.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = createProteinProjectionID(proteinAbove);
                    MemoLogger.log("PROJ: " + projectionID);
                    ProteinProjection proteinProjection = DataHandle.createProteinProjection(ProteinProjectionID, proteinBelow, proteinAbove, projection);
                }
            }
        }
    /*
    for (Interaction interaction : motherNetwork.getInteractions().values()) {
    projectInteraction(interaction, network, projection);
    
    }*/

    }

    private static String createProjectionID(Collection<Protein> selectedProteins, PPINetwork network, PPINetwork motherNetwork) {
        return "PROJECTION_" + motherNetwork.getID() + "_ON_" + network.getID();
    }

    private static String createProteinProjectionID(Protein protein) {
        return  protein.getID();
    }

    private static String createProteinProjectionInteractionID(String InteractionID) {
        return "PROJECTION_" + InteractionID;
    }

    private static String createProteinProjectionID(String proteinID) {
        return  proteinID;
    }

    private static String createGroupNodeID(Protein protein) {
        return "GROUP_NODE" + protein.getID();
    }

    private static String createGroupNodeID(String proteinID) {
        return "GROUP_NODE" + proteinID;
    }

    private static String createGroupNodeInteractionID(String interactionID) {
        return "PROJECTION_" + interactionID;
    }

    public static void projectProteinsToDownOnNetwork(Collection<Protein> selectedProteins, PPINetwork network, PPINetwork motherNetwork) {

        String projectionID = createProjectionID(selectedProteins, network, motherNetwork);

        PPINetworkProjection projection = DataHandle.createProjectionNetwork(projectionID, motherNetwork);

        for (Protein protein : selectedProteins) {
            projectProtein(protein, network, projection);   //TODO

        }
        for (Interaction interaction : motherNetwork.getInteractions().values()) {
            projectInteraction(interaction, network, projection);

        }

        for (Interaction interaction : network.getInteractions().values()) {
            projectProteinInteraction(interaction, projection);
        }

    }

    private static void projectProteinInteraction(Interaction interaction, PPINetworkProjection projection) {
        String proteinInteractionID = createProteinProjectionInteractionID(interaction.getID());
        String proteinInteractionSourceID = createProteinProjectionID(interaction.getSourceID());
        String proteinInteractionTargetID = createProteinProjectionID(interaction.getTargetID());

        Interaction proteinProjectionInteraction = new Interaction(proteinInteractionID, proteinInteractionSourceID, proteinInteractionTargetID, interaction.getProbability());
        projection.addProteinProjectionInteraction(proteinProjectionInteraction);
    }

    private static void projectInteraction(Interaction interaction, PPINetwork network, PPINetworkProjection projection) {

        String groupNodeInteractionID = createGroupNodeInteractionID(interaction.getID());
        String groupNodeInteractionSourceID = createGroupNodeID(interaction.getSourceID());
        String groupNodeInteractionTargetID = createGroupNodeID(interaction.getTargetID());

        Interaction groupNodeInteraction = new Interaction(groupNodeInteractionID, groupNodeInteractionSourceID, groupNodeInteractionTargetID, interaction.getProbability());
        projection.addGroupNodeInteraction(groupNodeInteraction);
    }

    private static void projectProtein(Protein protein, PPINetwork network, PPINetworkProjection projection) {

        String groupNodeID = createGroupNodeID(protein);
        GroupNode node = DataHandle.createGroupNode(groupNodeID, protein);
        projection.addGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(network.getID());

        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = createProteinProjectionID(proteinProject);
                ProteinProjection proteinProjection = DataHandle.createProteinProjection(ProteinProjectionID, protein, proteinProject, projection);
                node.addProteinInside(proteinProjection);
            }
        }
    }
}