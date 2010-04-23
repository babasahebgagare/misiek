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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.io.parsers.rootparser;

import java.util.Collection;
import java.util.HashSet;
import ppine.io.exceptions.SpeciesTreeFormatException;
import ppine.io.parsers.SpeciesParserStruct;
import ppine.logicmodel.controllers.DataHandle;
import ppine.main.PluginDataHandle;

public class RootSpaciesParser {

    public static void readSpaciesString(String treeString, String parent) throws SpeciesTreeFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        SpeciesParserStruct struct = extractNodeName(treeString);
        dh.createPPINetwork(struct.getNodeName(), parent);

        if (struct.getSubNodes() == null) {
        } else {
            for (String subNode : struct.getSubNodes()) {
                readSpaciesString(subNode, struct.getNodeName());
            }
        }
    }

    private static SpeciesParserStruct extractNodeName(String treeString) throws SpeciesTreeFormatException {
        SpeciesParserStruct struct = new SpeciesParserStruct();

        int lastBracket = treeString.lastIndexOf(")");
        if (lastBracket == -1) {
            struct.setNodeName(treeString);
            struct.setSubNodes(null);
        } else {
            struct.setNodeName(treeString.substring(lastBracket + 1));
            struct.setSubNodes(extractSubNodes(treeString.substring(1, lastBracket)));
        }

        return struct;
    }

    private static Collection<String> extractSubNodes(String substring) throws SpeciesTreeFormatException {
        Collection<String> ret = new HashSet<String>();
        String speciesName;

        int count = 0;
        int lastIndex = 0;

        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == '(') {
                count++;
            } else if (substring.charAt(i) == ')') {
                count--;
            } else if ((substring.charAt(i) == ',') && (count == 0)) {
                speciesName = substring.substring(lastIndex, i).trim();
                ret.add(speciesName);
                lastIndex = i + 1;
            }
        }
        if (count != 0) {
            throw new SpeciesTreeFormatException("Parsing tree node, problem with brackets: " + substring, lastIndex);
        }
        speciesName = substring.substring(lastIndex, substring.length()).trim();
        ret.add(speciesName);

        return ret;
    }
}
