package IO;

import structs.model.PPINetwork;

public abstract class AbstractDataReader {

    String filepath;

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public abstract void readSpacies();

    public abstract void readTrees();

    public void readInteractions() {
        readInteractions(1.0);
    }

    public void readInteractions(PPINetwork network) {
        readInteractions(network, 1.0);
    }

    public abstract void readInteractions(double treshold);

    public abstract void readInteractions(PPINetwork network, double treshold);
}
