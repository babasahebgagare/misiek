package mcv.io;

import mcv.io.readers.tasks.TasksDataReader;
import java.util.Map;
import mcv.logicmodel.structs.PPINetwork;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;

public abstract class AbstractDataReader {

    String filepath;
    private static AbstractDataReader reader = new TasksDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold);

    public abstract void readAllInteractions(Map<String, Double> tresholds);

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

    public abstract void readSpecies();

    public abstract void readTrees();
}
