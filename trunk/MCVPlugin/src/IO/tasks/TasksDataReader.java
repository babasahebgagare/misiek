package IO.tasks;

import IO.AbstractDataReader;
import cytoscape.Cytoscape;
import cytoscape.task.Task;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import structs.model.CytoAbstractPPINetwork;

public class TasksDataReader extends AbstractDataReader {

    @Override
    public void readInteractions(CytoAbstractPPINetwork cytoNetwork, double treshold) {
        String intpath = getFilepath().concat("int");

        Task task = new LoadInteractionsTask(intpath, cytoNetwork, treshold);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);

    }

    @Override
    public void readTrees() {
        String treespath = getFilepath().concat("trees");

        Task task = new LoadTreesTask(treespath);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readSpacies() {
        String spspath = getFilepath().concat("spy");

        Task task = new LoadSpaciesTask(spspath);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readAllInteractions(double treshold) {
        String intpath = getFilepath().concat("int");

        Task task = new LoadAllInteractionsTask(intpath, treshold);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);

    }
}
