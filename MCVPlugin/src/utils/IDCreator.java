package utils;

import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoProtein;
import structs.model.PPINetwork;
import structs.model.Protein;

public class IDCreator {

    private static int networkID = 0;

    public static String createNetworkProjectionID(PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        networkID++;
        return "PROJ_" + cytoNetworkSource.getID() + "_ON_" + networkTarget.getID() + "_" + String.valueOf(networkID);
    }

    public static String createProteinProjectionID(Protein targetProteinProject, CytoAbstractPPINetwork cytoProjection) {
        return targetProteinProject.getID() + ":" + cytoProjection.getID();
    }

    public static String createInteractionProjectionID(String interactionID, CytoAbstractPPINetwork cytoProjection) {
        return "PROJ_" + interactionID + ":" + cytoProjection.getID();
    }

    public static String createGroupNodeID(CytoProtein cytoProtein) {
        return "GROUP_NODE" + cytoProtein.getCytoID();
    }

    public static String createProteinProjectionID(String ProteinID, CytoAbstractPPINetwork cytoNetwork) {
        return ProteinID + ":" + cytoNetwork.getID();
    }
}
