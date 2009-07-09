package ppine.io.readers.tasks;

import ppine.io.AbstractDataReader;
import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import java.util.Map;
import ppine.io.listeners.ExperimentsLoadingErrorsListener;
import ppine.io.listeners.FamiliesLoadingErrorsListener;
import ppine.io.listeners.InteractionsLoadingErrorsListener;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.logicmodel.structs.PPINetwork;

public class TasksDataReader extends AbstractDataReader {

    @Override
    public void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold, InteractionsLoadingErrorsListener errorListener) {

        LoadSpeciesInteractionsTask task = new LoadSpeciesInteractionsTask(filepath, network, treshold);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readFamiliesTree(String filepath, FamiliesLoadingErrorsListener errorListener) {
        LoadTreesTask task = new LoadTreesTask(filepath);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readSpeciesTree(String filepath, SpeciesLoadingErrorsListener errorListeners) {

        LoadSpaciesTask task = new LoadSpaciesTask(filepath);
        task.setErrorListener(errorListeners);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener) {
        LoadAllInteractionsTask task = new LoadAllInteractionsTask(filepath, tresholds);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);

    }

    @Override
    public void readAllExperiments(String filepath, ExperimentsLoadingErrorsListener errorListener) {
        LoadAllExperimentsTask task = new LoadAllExperimentsTask(filepath);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }
}
