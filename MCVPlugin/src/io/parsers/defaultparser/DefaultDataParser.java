package io.parsers.defaultparser;

import io.parsers.DataParser;
import java.io.BufferedReader;
import java.io.IOException;
import viewmodel.structs.CytoAbstractPPINetwork;

public class DefaultDataParser extends DataParser {

    @Override
    public void readSpaciesString(String treeString) {
        DefaultSpaciesParser.readSpaciesString(treeString, null);
    }

    @Override
    public void readInteractions(BufferedReader br, CytoAbstractPPINetwork cytoNetwork, double treshold) {
        try {
            DefaultInteractionsParser.readInteractions(br, cytoNetwork, treshold);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void readAllTreeString(String treeString) {
        DefaultFamiliesTreeParser.readAllTreeString(treeString);
    }
}
