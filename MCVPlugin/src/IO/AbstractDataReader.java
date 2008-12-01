package IO;

import IO.defaultreader.DefaultDataReader;
import structs.model.CytoAbstractPPINetwork;

public abstract class AbstractDataReader {

    String filepath;
    private static AbstractDataReader reader = new DefaultDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readInteractions(CytoAbstractPPINetwork cytoNetwork, double treshold);

    public void readInteractions(CytoAbstractPPINetwork cytoNetwork) {
        readInteractions(cytoNetwork, 1.0);
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public abstract void readSpacies();

    public abstract void readTrees();
}
