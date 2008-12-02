package IO.tasks;

import IO.defaultreader.*;
import cytoscape.Cytoscape;
import cytoscape.task.Task;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import structs.model.CytoAbstractPPINetwork;

public class TasksDataReader extends DefaultDataReader {

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
}
