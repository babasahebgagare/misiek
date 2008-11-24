package projector;

import java.util.Collection;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoInteraction;
import structs.model.CytoPPINetworkProjection;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;
import structs.model.Interaction;
import structs.model.PPINetwork;
import structs.model.Protein;

public class ProjectorNetwork {

    private static String createProjectionID(PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        return "PROJECTION_" + networkTarget.getID() + cytoNetworkSource.getID();
    }

    private static String createProjectionID(Collection<CytoProtein> selectedProteins, CytoAbstractPPINetwork network, CytoAbstractPPINetwork motherNetwork) {
        return "PROJECTION_" + motherNetwork.getID() + "_ON_" + network.getID();
    }

    private static String createProteinProjectionID(Protein protein) {
        return "PROJECTION_" + protein.getID();
    }

    private static String createProteinProjectionInteractionID(String InteractionID) {
        return "PROJECTION_" + InteractionID;
    }

    private static String createProteinProjectionID(String proteinID) {
        return "PROJECTION_" + proteinID;
    }

    private static String createGroupNodeID(CytoProtein cytoProtein) {
        return "GROUP_NODE" + cytoProtein.getCytoID();
    }

    private static String createGroupNodeID(String proteinID) {
        return "GROUP_NODE" + proteinID;
    }

    private static String createGroupNodeInteractionID(String interactionID) {
        return "PROJECTION_" + interactionID;
    }

    static CytoPPINetworkProjection projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = createProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjection projection = CytoDataHandle.createCytoProjectionNetwork(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);   //TODO

        }
        for (Interaction interaction : networkTarget.getInteractions().values()) {
            projectCytoInteraction(interaction, projection);
        }
        /*
        for (Interaction interaction : network.getInteractions().values()) {
        projectProteinInteraction(interaction, projection);
        }
         */

        return projection;
    }

    static CytoPPINetworkProjection projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = createProjectionID(networkTarget, cytoNetworkSource);
        CytoPPINetworkProjection projection = CytoDataHandle.createCytoProjectionNetwork(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProteinBelow : selectedProteins) {
            Protein protein = cytoProteinBelow.getProtein();
            Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapUp().get(networkTarget.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = createProteinProjectionID(proteinAbove);
                    CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinAbove, projection);
                }
            }
        }

        for (Interaction interaction : networkTarget.getInteractions().values()) {
            projectCytoInteraction(interaction, projection);
        }

        return projection;
    }

    private static void projectCytoInteraction(Interaction interaction, CytoPPINetworkProjection projection) {
        String proteinInteractionID = createProteinProjectionInteractionID(interaction.getID());
        String proteinInteractionSourceID = createProteinProjectionID(interaction.getSourceID());
        String proteinInteractionTargetID = createProteinProjectionID(interaction.getTargetID());

        CytoProtein source = projection.getCytoProtein(proteinInteractionSourceID);
        CytoProtein target = projection.getCytoProtein(proteinInteractionTargetID);

        CytoInteraction CytoProjectionInteraction = new CytoInteraction(proteinInteractionID, interaction, source, target, projection);
        projection.addCytoInteraction(CytoProjectionInteraction);
    }

    private static void projectCytoProtein(CytoProtein cytoProtein, CytoPPINetworkProjection projection) {

        Protein protein = cytoProtein.getProtein();

        //   String groupNodeID = createGroupNodeID(cytoProtein);
        //   CytoGroupNode node = CytoDataHandle.createCytoGroupNode(groupNodeID, cytoProtein);
        //     projection.addCytoGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(projection.getNetwork().getID());
        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = createProteinProjectionID(proteinProject);
                CytoProteinProjection proteinProjection = CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinProject, projection);
            //       node.addCytoProteinInside(proteinProjection);
            }

        }
    }
}