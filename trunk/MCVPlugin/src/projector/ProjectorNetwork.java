package projector;

import java.util.Collection;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoGroupNode;
import structs.model.CytoPPINetworkProjection;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;
import structs.model.PPINetwork;
import structs.model.Protein;
import utils.IDCreator;

public class ProjectorNetwork {

    static CytoPPINetworkProjection projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjection projection = CytoDataHandle.createCytoProjectionNetwork(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);   //TODO

        }
        return projection;
    }

    static CytoPPINetworkProjection projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);
        CytoPPINetworkProjection projection = CytoDataHandle.createCytoProjectionNetwork(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProteinBelow : selectedProteins) {
            Protein protein = cytoProteinBelow.getProtein();
            Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapUp().get(networkTarget.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinAbove, projection);
                    CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinAbove, projection);
                }
            }
        }

        return projection;
    }

    private static void projectCytoProtein(CytoProtein cytoProtein, CytoPPINetworkProjection projection) {

        Protein protein = cytoProtein.getProtein();

        String groupNodeID = IDCreator.createGroupNodeID(cytoProtein);
        CytoGroupNode node = CytoDataHandle.createCytoGroupNode(groupNodeID, cytoProtein);
        projection.addCytoGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(projection.getNetwork().getID());
        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinProject, projection);
                CytoProteinProjection proteinProjection = CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinProject, projection);
                node.addCytoProteinInside(proteinProjection);
            }

        }
    }
}