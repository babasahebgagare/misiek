package mcv.io.parsers.defaultparser;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import mcv.io.parsers.ParserStruct;
import mcv.logicmodel.controllers.DataHandle;
import mcv.main.PluginDataHandle;
import mcv.utils.ColorGenerator;

public class DefaultFamiliesTreeParser {

    public static void readAllTreeString(String treeString) {
        DataHandle dh = PluginDataHandle.getDataHandle();
        int lastIndex = treeString.lastIndexOf(")");
        String FamilyName = treeString.substring(lastIndex + 1).trim();
        String tree = treeString.substring(1, lastIndex).trim();

        Color color = ColorGenerator.generateColor(FamilyName);

        dh.createFamily(FamilyName, color);
        readTreeSpaciesString(tree, FamilyName, null);

    }

    private static ParserStruct extractSpaciesName(String treeString) {
        ParserStruct struct = new ParserStruct();

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

    private static void readTreeSpaciesString(String tree, String FamilyName, String parent) {
        DataHandle dh = PluginDataHandle.getDataHandle();
        Collection<String> spaciesInfo = readTreeSpaciesCollection(tree);
        for (String sp : spaciesInfo) {
            ParserStruct struct = extractSpaciesName(sp);

            if (struct.getSubNodes() == null) {
            } else {
                for (String subNode : struct.getSubNodes()) {
                    int lastBracket = subNode.lastIndexOf(")");
                    if (lastBracket != -1) {
                        String proteinName = subNode.substring(lastBracket + 1);
                        String spaciesCollection = subNode.substring(1, lastBracket);

                        dh.createProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                        readTreeSpaciesString(spaciesCollection, FamilyName, proteinName);
                    } else {
                        String proteinName = subNode;
                        dh.createProtein(proteinName, parent, struct.getNodeName(), FamilyName);
                    }
                }
            }
        }
    }
}

