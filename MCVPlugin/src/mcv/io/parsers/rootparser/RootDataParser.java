package mcv.io.parsers.rootparser;

import mcv.io.parsers.defaultparser.DefaultDataParser;
import mcv.logicmodel.controllers.DataHandle;
import mcv.main.PluginDataHandle;

public class RootDataParser extends DefaultDataParser {

    private static String rootFamilyName = "ROOT";

    @Override
    public void readAllTreeString(String treeString) {
        RootFamiliesTreeParser.readAllTreeString(treeString);
    }

    @Override
    public void readSpaciesString(String treeString) {
        DataHandle dh = PluginDataHandle.getDataHandle();
        dh.createRootPPINetwork(rootFamilyName);
        RootSpaciesParser.readSpaciesString(treeString, rootFamilyName);
    }
}
