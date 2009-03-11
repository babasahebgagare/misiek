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

public class LoadTreesTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private File file;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public LoadTreesTask(String treespath) {
        this.file = new File(treespath);
        this.max = file.length();

    }

    public void run() {
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Ładowanie białek");
            taskMonitor.setPercentCompleted(-1);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            taskMonitor.setPercentCompleted(0);
            while (br.ready()) {
                String line;
                line = br.readLine();
                if (line != null && !line.equals("")) {
                    String[] families = line.split(";");
                    for (String family : families) {
                        DataParser.getInstance().readAllTreeString(family);
                    }
                    current = fis.getChannel().position();
                    float percent = current * 100 / (float) max;
                    taskMonitor.setPercentCompleted(Math.round(percent));
                }
            }
            taskMonitor.setPercentCompleted(100);
            br.close();
            dis.close();
            bis.close();
            fis.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadTreesTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void halt() {

        System.out.println("zatrzymywanie działania");

        if (myThread != null) {
            try {
                myThread.interrupt();
                br.close();
                dis.close();
                bis.close();
                fis.close();
                ((JTask) taskMonitor).setDone();
            } catch (IOException ex) {
                Logger.getLogger(LoadTreesTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Wczytywanie drzew rodzin białek";
    }
}
