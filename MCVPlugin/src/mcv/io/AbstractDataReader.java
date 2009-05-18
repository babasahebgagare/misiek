package mcv.io;

import mcv.io.readers.tasks.TasksDataReader;
import java.util.Map;
import mcv.io.listeners.FamiliesLoadingErrorsListener;
import mcv.io.listeners.InteractionsLoadingErrorsListener;
import mcv.io.listeners.SpeciesLoadingErrorsListener;
import mcv.logicmodel.structs.PPINetwork;

public abstract class AbstractDataReader {

    private static AbstractDataReader reader = new TasksDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold, InteractionsLoadingErrorsListener errorListener);

    public abstract void readAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener);

    public abstract void readSpeciesTree(String filepath, SpeciesLoadingErrorsListener errorListener);

    public abstract void readFamiliesTree(String filepath, FamiliesLoadingErrorsListener errorListener);
}
