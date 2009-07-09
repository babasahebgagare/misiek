package ppine.io.parsers.rootparser;

import ppine.io.exceptions.FamiliesTreeFormatException;
import ppine.io.exceptions.SpeciesTreeFormatException;
import ppine.io.parsers.DataParser;
import ppine.logicmodel.controllers.DataHandle;
import ppine.main.PluginDataHandle;

public class RootDataParser extends DataParser {

    private static String rootFamilyName = "ROOT";

    @Override
    public void readFamiliesTreeString(String treeString) throws FamiliesTreeFormatException {
        RootFamiliesTreeParser.readAllTreeString(treeString);
    }

    @Override
    public void readSpeciesString(String treeString) throws SpeciesTreeFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        dh.createRootPPINetwork(rootFamilyName);
        RootSpaciesParser.readSpaciesString(treeString, rootFamilyName);
    }
}
