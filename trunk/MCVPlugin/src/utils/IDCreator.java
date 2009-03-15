package utils;

import viewmodel.structs.CytoAbstractPPINetwork;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.PPINetwork;
import logicmodel.structs.Protein;

public class IDCreator {

    private static int networkID = 0;

    public static String createInteractionID(String SourceID, String TargetID) {
        return SourceID + "-" + TargetID;
    }

    public static String createNetCytoNetworkID(String ID) {
        System.out.println(ID);
        if (!CytoUtil.cytoNetworkExist(ID)) {
            return ID;
        }
        boolean ok = false;
        int sufix = 1;
        while (!ok) {
            String tmpID = ID.concat("_" + String.valueOf(sufix));
            if (!CytoUtil.cytoNetworkExist(tmpID)) {
                ok = true;
                return tmpID;
            }
            sufix++;
        }
        return ID;
    }

    public static String createNetworkProjectionID(PPINetwork networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        networkID++;
        return "PROJ_" + cytoNetworkSource.getID() + "_ON_" + networkTarget.getID() + "_" + String.valueOf(networkID);
    }

    public static String createProteinProjectionID(Protein targetProteinProject, CytoAbstractPPINetwork cytoProjection) {
        //   return targetProteinProject.getID() + ":" + cytoProjection.getID();
        return targetProteinProject.getID();
    }

    public static String createInteractionProjectionID(String interactionID, CytoAbstractPPINetwork cytoProjection) {
        //   return interactionID + ":" + cytoProjection.getID();
        return interactionID;
    }

    public static String createGroupNodeID(CytoProtein cytoProtein) {
        return "GROUP_NODE" + cytoProtein.getCytoID();
    }

    public static String createProteinProjectionID(String ProteinID, CytoAbstractPPINetwork cytoNetwork) {
        //   return ProteinID + ":" + cytoNetwork.getID();
        return ProteinID;
    }
}
