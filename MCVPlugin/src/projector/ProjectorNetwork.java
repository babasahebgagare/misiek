package projector;

import java.util.Collection;
import main.DataHandle;
import structs.GroupNode;
import structs.Interaction;
import structs.PPINetwork;
import structs.PPINetworkProjection;
import structs.Protein;
import structs.ProteinProjection;

public class ProjectorNetwork {

    private static String createProjectionID(Collection<Protein> selectedProteins, PPINetwork network, PPINetwork motherNetwork) {
        return "PROJECTION_" + motherNetwork.getID() + "_ON_" + network.getID();
    }

    private static String createProteinProjectionID(Protein protein) {
        return "PROJECTION_" + protein.getID();
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

    public static void projectProteinsOnNetwork(Collection<Protein> selectedProteins, PPINetwork network, PPINetwork motherNetwork) {

        String projectionID = createProjectionID(selectedProteins, network, motherNetwork);

        PPINetworkProjection projection = DataHandle.createProjectionNetwork(projectionID, motherNetwork);

        for (Protein protein : selectedProteins) {
            projectProtein(protein, network, projection);   //TODO

        }
        for (Interaction interaction : motherNetwork.getInteractions().values()) {
            projectInteraction(interaction, network, projection);

        }

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

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMap().get(network.getID());

        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = createProteinProjectionID(proteinProject);
                ProteinProjection proteinProjection = DataHandle.createProteinProjection(ProteinProjectionID, proteinProject, projection);
                node.addProteinInside(proteinProjection);
            }
        }
    }
}