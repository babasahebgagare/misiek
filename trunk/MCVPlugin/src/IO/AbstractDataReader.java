package IO;

import IO.readers.tasks.TasksDataReader;
import structs.model.CytoAbstractPPINetwork;

public abstract class AbstractDataReader {

    String filepath;
    private static AbstractDataReader reader = new TasksDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readAllInteractions(double treshold);

    public void readAllInteractions() {
        readAllInteractions(1.0);
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
