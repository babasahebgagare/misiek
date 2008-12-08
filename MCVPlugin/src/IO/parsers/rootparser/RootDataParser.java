package io.parsers.rootparser;

import io.parsers.defaultparser.*;
import main.DataHandle;

public class RootDataParser extends DefaultDataParser {

    private static String rootFamilyName = "ROOT";

    @Override
    public void readAllTreeString(String treeString) {
        RootFamiliesTreeParser.readAllTreeString(treeString);
    }

    @Override
    public void readSpaciesString(String treeString) {
        DataHandle.createRootPPINetwork(rootFamilyName);
        RootSpaciesParser.readSpaciesString(treeString, rootFamilyName);
    }
}
