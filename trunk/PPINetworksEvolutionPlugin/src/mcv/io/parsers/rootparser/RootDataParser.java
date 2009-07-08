package mcv.io.parsers.rootparser;

import mcv.io.exceptions.FamiliesTreeFormatException;
import mcv.io.exceptions.SpeciesTreeFormatException;
import mcv.io.parsers.DataParser;
import mcv.logicmodel.controllers.DataHandle;
import mcv.main.PluginDataHandle;

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
