package io.readers.tasks;

import io.parsers.DataParser;
import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoadSpaciesTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private File file;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public LoadSpaciesTask(String sppath) {
        this.file = new File(sppath);

    }

    public void run() {
        myThread = Thread.currentThread();

        taskMonitor.setStatus("Ładowanie drzewa gatunków");
        taskMonitor.setPercentCompleted(-1);
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            try {
                String treeString = br.readLine();
                DataParser.getInstance().readSpaciesString(treeString);
            } catch (Exception ex) {
                taskMonitor.setException(ex, "Problem podczas wczytywania drzewa gatunków");
            }
            taskMonitor.setPercentCompleted(100);
            br.close();
            dis.close();
            bis.close();
            fis.close();

        } catch (Exception e) {
            taskMonitor.setException(e, "Problem podczas tworzenia lub zamykania strumieni");

            return;
        }
    }

    public void halt() {

        System.out.println("zatrzymywanie działania");

        try {
            if (myThread != null) {
                myThread.interrupt();
                br.close();
                dis.close();
                bis.close();
                fis.close();
                ((JTask) taskMonitor).setDone();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Wczytywanie drzewa gatunków";
    }
}
