package mcv.io.parsers.rootparser;


import java.util.Collection;
import java.util.HashSet;
import mcv.io.parsers.ParserStruct;
import mcv.logicmodel.controllers.DataHandle;
import mcv.main.PluginDataHandle;

public class RootSpaciesParser {

    public static void readSpaciesString(String treeString, String parent) {
        DataHandle dh = PluginDataHandle.getDataHandle();
        ParserStruct struct = extractNodeName(treeString);
        dh.createPPINetwork(struct.getNodeName(), parent);

        if (struct.getSubNodes() == null) {
        } else {
            for (String subNode : struct.getSubNodes()) {
                readSpaciesString(subNode, struct.getNodeName());
            }
        }

    }

    private static ParserStruct extractNodeName(String treeString) {
        ParserStruct struct = new ParserStruct();

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
                lastIndex = i;
            }
        }

        ret.add(substring.substring(lastIndex + 1, substring.length()));

        return ret;
    }
}
