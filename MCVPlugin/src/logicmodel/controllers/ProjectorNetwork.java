package logicmodel.controllers;

import java.util.Collection;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoGroupNode;
import viewmodel.structs.CytoPPINetworkProjectionToDown;
import viewmodel.structs.CytoPPINetworkProjectionToUp;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.CytoProteinProjection;
import logicmodel.structs.PPINetwork;
import logicmodel.structs.Protein;
import main.PluginDataHandle;
import utils.IDCreator;

public class ProjectorNetwork {

    public static CytoPPINetworkProjectionToDown projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjectionToDown projection = cdh.createCytoProjectionToDown(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);

        }
        return projection;
    }

    public static CytoPPINetworkProjectionToUp projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
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