package io.parsers;

import io.parsers.rootparser.RootDataParser;
import java.io.BufferedReader;
import viewmodel.structs.CytoAbstractPPINetwork;

public abstract class DataParser {

    private static DataParser parser = new RootDataParser();

    public static DataParser getInstance() {
        return parser;
    }

    public abstract void readSpaciesString(String treeString);

    public abstract void readInteractions(BufferedReader br, CytoAbstractPPINetwork cytoNetwork, double treshold);

    public abstract void readAllTreeString(String treeString);
}
