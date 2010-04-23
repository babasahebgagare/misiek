/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package ppine.utils;

import ppine.io.parsers.ExperimentParserStruct;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoProtein;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;

public class IDCreator {

    private static int networkID = 0;

    public static String createExpInteractionID(ExperimentParserStruct interaction) {
        return interaction.getFrom() + " (" + interaction.getExpID() + ") " + interaction.getTo();
    }

    public static String createExpNetworkID(String speciesName) {
        return speciesName + "_EXP";
    }

    public static String createInteractionID(String SourceID, String TargetID, Double prob) {
        return SourceID + " (" + prob + ") " + TargetID;
    }

    public static String createNetCytoNetworkID(String ID) {
        // System.out.println(ID);
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
        return "MAP_" + cytoNetworkSource.getID() + "_ON_" + networkTarget.getID() + "_" + String.valueOf(networkID);
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
