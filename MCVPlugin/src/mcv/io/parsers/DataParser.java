package mcv.io.parsers;

import mcv.io.parsers.rootparser.RootDataParser;
import mcv.io.exceptions.FamiliesTreeFormatException;
import mcv.io.exceptions.SpeciesTreeFormatException;

public abstract class DataParser {

    private static DataParser parser = new RootDataParser();

    public static DataParser getInstance() {
        return parser;
    }

    public abstract void readSpeciesString(String treeString) throws SpeciesTreeFormatException;

    public abstract void readFamiliesTreeString(String treeString) throws FamiliesTreeFormatException;
}
