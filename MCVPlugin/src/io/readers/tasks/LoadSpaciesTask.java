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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Gene tree is loading...");
            taskMonitor.setPercentCompleted(-1);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            String treeString = br.readLine();
            DataParser.getInstance().readSpaciesString(treeString);
            taskMonitor.setPercentCompleted(100);
            br.close();
            dis.close();
            bis.close();
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadSpaciesTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void halt() {

        System.out.println("Stopping...");

        if (myThread != null) {
            try {
                myThread.interrupt();
                br.close();
                dis.close();
                bis.close();
                fis.close();
                ((JTask) taskMonitor).setDone();
            } catch (IOException ex) {
                Logger.getLogger(LoadSpaciesTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Gene tree is loading...";
    }
}
