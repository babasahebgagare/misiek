package mcv.utils;

import mcv.io.parsers.ExperimentParserStruct;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;
import mcv.viewmodel.structs.CytoProtein;
import mcv.logicmodel.structs.SpeciesTreeNode;
import mcv.logicmodel.structs.Protein;

public class IDCreator {

    private static int networkID = 0;

    public static String createExpInteractionID(ExperimentParserStruct interaction) {
        return interaction.getFrom() + "_" + interaction.getTo() + "_" + interaction.getExpID();
    }

    public static String createExpNetworkID(String speciesName) {
        return speciesName + "_EXP";
    }

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

    public static String createNetworkProjectionID(SpeciesTreeNode networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
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
