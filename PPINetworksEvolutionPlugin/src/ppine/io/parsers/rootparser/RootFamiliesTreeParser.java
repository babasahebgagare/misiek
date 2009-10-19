/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.io.parsers.rootparser;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import ppine.io.exceptions.FamiliesTreeFormatException;
import ppine.io.parsers.SpeciesParserStruct;
import ppine.logicmodel.controllers.DataHandle;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.utils.ColorGenerator;

public class RootFamiliesTreeParser {

    private static String rootFamilyName = "ROOT";

    public static void readAllTreeString(String treeString) throws FamiliesTreeFormatException {
        try {
            DataHandle dh = PluginDataHandle.getDataHandle();
            int lastIndex = treeString.lastIndexOf(")");
            String FamilyName = treeString.substring(lastIndex + 1).trim();
            String tree = treeString.substring(1, lastIndex).trim();

            Color color = ColorGenerator.generateColor(FamilyName);

            dh.createFamily(FamilyName, color);
            dh.createProtein(FamilyName, null, rootFamilyName, FamilyName);
            readTreeSpaciesString(tree, FamilyName, FamilyName);
        } catch (Exception e) {
            throw new FamiliesTreeFormatException(e.toString(), 0);
        }
    }

    private static SpeciesParserStruct extractSpaciesName(String treeString) {
        SpeciesParserStruct struct = new SpeciesParserStruct();

        int lastBracket = treeString.lastIndexOf("]");
        if (lastBracket == -1) {
            struct.setNodeName(treeString);
            struct.setSubNodes(null);
        } else {
            struct.setNodeName(treeString.substring(lastBracket + 1));
            struct.setSubNodes(extractSubNodes(treeString.substring(1, lastBracket)));
        }

        return struct;
    }

    private static Collection<String> extractSubNodes(String substring) {
        Collection<String> ret = new HashSet<String>();

        int count = 0;
        int lastIndex = 0;

        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == '(') {
                count++;
            } else if (substring.charAt(i) == ')') {
                count--;
            } else if ((substring.charAt(i) == ',') && (count == 0)) {
                ret.add(substring.substring(lastIndex, i));
                lastIndex = i + 1;
            }
        }

        ret.add(substring.substring(lastIndex, substring.length()));

        return ret;
    }

    private static void helpCreateProtein(String proteinID, String parentProteinID, String networkID, String familyID) throws FamiliesTreeFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();

        SpeciesTreeNode network = dh.getNetwork(networkID);
        SpeciesTreeNode parentNetwork = network.getContext().tryGetParentNetwork();
        Protein parentProtein = parentNetwork.getProtein(parentProteinID);
        if (parentProtein != null) {
            dh.createProtein(proteinID, parentProteinID, networkID, familyID);
        } else {
            Protein newParentProtein = parentNetwork.getProtein(familyID);
            if (newParentProtein == null) {
                newParentProtein = dh.createProtein(familyID, parentProteinID, parentNetwork.getID(), familyID);
            }
            dh.createProtein(proteinID, newParentProtein.getID(), networkID, familyID);
        }

    }

    private static Collection<String> readTreeSpaciesCollection(String substring) {

        Collection<String> ret = new HashSet<String>();
        int count = 0;
        int lastIndex = 0;

        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == '[') {
                count++;
            } else if (substring.charAt(i) == ']') {
                count--;
            } else if ((substring.charAt(i) == ',') && (count == 0)) {
                ret.add(substring.substring(lastIndex, i));
                lastIndex = i + 1;
            }
        }

        ret.add(substring.substring(lastIndex, substring.length()));

        return ret;
    }

    private static void readTreeSpaciesString(String tree, String FamilyName, String parent) throws FamiliesTreeFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<String> spaciesInfo = readTreeSpaciesCollection(tree);
        for (String sp : spaciesInfo) {
            SpeciesParserStruct struct = extractSpaciesName(sp);

            if (struct.getSubNodes() == null) {
            } else {
                for (String subNode : struct.getSubNodes()) {
                    int lastBracket = subNode.lastIndexOf(")");
                    if (lastBracket != -1) {
                        String proteinName = subNode.substring(lastBracket + 1);
                        String spaciesCollection = subNode.substring(1, lastBracket);

                        helpCreateProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                        //   dh.createProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                        readTreeSpaciesString(spaciesCollection, FamilyName, proteinName);
                    } else {
                        String proteinName = subNode;
                        helpCreateProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                    //      dh.createProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                    }
                }
            }
        }
    }
}

