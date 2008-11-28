package IO;

import structs.model.PPINetwork;

public interface DataReaderInterface {

    public void readSpacies(String filepath);

    public void readTrees(String filepath);

    public void readInteractions(String filepath);

    public void readInteractions(String filepath, double treshold);

    public void readInteractions(String filepath, PPINetwork network, double treshold);
}
