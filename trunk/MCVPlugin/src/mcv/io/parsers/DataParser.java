package mcv.io.parsers;

import mcv.io.parsers.rootparser.RootDataParser;
import java.io.BufferedReader;
import mcv.io.exceptions.SpeciesTreeFormatException;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;

public abstract class DataParser {

    private static DataParser parser = new RootDataParser();

    public static DataParser getInstance() {
        return parser;
    }

    public abstract void readSpaciesString(String treeString) throws SpeciesTreeFormatException;

    public abstract void readInteractions(BufferedReader br, CytoAbstractPPINetwork cytoNetwork, double treshold);

    public abstract void readAllTreeString(String treeString);
}
