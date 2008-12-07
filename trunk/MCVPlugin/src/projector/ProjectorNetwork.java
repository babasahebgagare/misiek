package projector;

import java.util.Collection;
import main.CytoDataHandle;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoGroupNode;
import structs.model.CytoPPINetworkProjectionToDown;
import structs.model.CytoPPINetworkProjectionToUp;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;
import structs.model.PPINetwork;
import structs.model.Protein;
import utils.IDCreator;

public class ProjectorNetwork {

    static CytoPPINetworkProjectionToDown projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjectionToDown projection = CytoDataHandle.createCytoProjectionToDown(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);

        }
        return projection;
    }

    static CytoPPINetworkProjectionToUp projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);
        CytoPPINetworkProjectionToUp projection = CytoDataHandle.createCytoProjectionToUp(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProteinBelow : selectedProteins) {
            Protein protein = cytoProteinBelow.getProtein();
            Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapUp().get(networkTarget.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinAbove, projection);
                    if (!projection.containsCytoProtein(ProteinProjectionID)) {
                        CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinAbove, projection, cytoProteinBelow);
                    }
                }
            }
        }

        return projection;
    }

    private static void projectCytoProtein(CytoProtein cytoProtein, CytoPPINetworkProjectionToDown projection) {

        Protein protein = cytoProtein.getProtein();

        String groupNodeID = IDCreator.createGroupNodeID(cytoProtein);
        CytoGroupNode node = CytoDataHandle.createCytoGroupNode(groupNodeID, cytoProtein);
        projection.addCytoGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(projection.getNetwork().getID());
        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinProject, projection);
                CytoProteinProjection proteinProjection = CytoDataHandle.createCytoProteinProjection(ProteinProjectionID, proteinProject, projection, cytoProtein);
                node.addCytoProteinInside(proteinProjection);
            }

        }
    }
}