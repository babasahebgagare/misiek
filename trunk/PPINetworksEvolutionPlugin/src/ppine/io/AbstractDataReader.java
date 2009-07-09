package ppine.io;

import ppine.io.listeners.ExperimentsLoadingErrorsListener;
import ppine.io.readers.tasks.TasksDataReader;
import java.util.Map;
import ppine.io.listeners.FamiliesLoadingErrorsListener;
import ppine.io.listeners.InteractionsLoadingErrorsListener;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.logicmodel.structs.PPINetwork;

public abstract class AbstractDataReader {

    private static AbstractDataReader reader = new TasksDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readAllExperiments(String filepath, ExperimentsLoadingErrorsListener errorListener);

    public abstract void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold, InteractionsLoadingErrorsListener errorListener);

    public abstract void readAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener);

    public abstract void readSpeciesTree(String filepath, SpeciesLoadingErrorsListener errorListener);

    public abstract void readFamiliesTree(String filepath, FamiliesLoadingErrorsListener errorListener);
}
