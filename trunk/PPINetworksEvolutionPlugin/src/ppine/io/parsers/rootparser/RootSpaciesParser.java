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
