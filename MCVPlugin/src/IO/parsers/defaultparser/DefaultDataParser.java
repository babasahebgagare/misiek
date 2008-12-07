package IO.parsers.defaultparser;

import IO.parsers.DataParser;
import java.io.BufferedReader;
import java.io.IOException;
import org.openide.util.Exceptions;
import structs.model.CytoAbstractPPINetwork;

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
            System.out.println("ERROR during interactions parsing");
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public void readAllTreeString(String treeString) {
        DefaultFamiliesTreeParser.readAllTreeString(treeString);
    }
}
