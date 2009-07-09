package ppine.logicmodel.controllers;

import java.util.Collection;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoGroupNode;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.viewmodel.structs.CytoProteinProjection;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.utils.IDCreator;

public class ProjectorNetwork {

    public static CytoPPINetworkProjectionToDown projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, SpeciesTreeNode networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjectionToDown projection = cdh.createCytoProjectionToDown(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);

        }
        return projection;
    }

    public static CytoPPINetworkProjectionToUp projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, SpeciesTreeNode networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);
        CytoPPINetworkProjectionToUp projection = cdh.createCytoProjectionToUp(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProteinBelow : selectedProteins) {
            Protein protein = cytoProteinBelow.getProtein();
            Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapUp().get(networkTarget.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinAbove, projection);
                    if (!projection.containsCytoProtein(ProteinProjectionID)) {
                        cdh.createCytoProteinProjection(ProteinProjectionID, proteinAbove, projection, cytoProteinBelow);
                    }
                }
            }
        }

        return projection;
    }

    private static void projectCytoProtein(CytoProtein cytoProtein, CytoPPINetworkProjectionToDown projection) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        Protein protein = cytoProtein.getProtein();

        String groupNodeID = IDCreator.createGroupNodeID(cytoProtein);
        CytoGroupNode node = cdh.createCytoGroupNode(groupNodeID, cytoProtein);
        projection.addCytoGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(projection.getNetwork().getID());
        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinProject, projection);
                CytoProteinProjection proteinProjection = cdh.createCytoProteinProjection(ProteinProjectionID, proteinProject, projection, cytoProtein);
                node.addCytoProteinInside(proteinProjection);
            }

        }
    }
}