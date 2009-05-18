package mcv.io.readers.tasks;

import mcv.io.exceptions.SpeciesTreeFormatException;
import mcv.io.parsers.DataParser;
import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

public class LoadSpaciesTask implements Task {

    private TaskMonitor taskMonitor = null;
    private ErrorListener errorListener = null;
    private Thread myThread = null;
    private String filepath;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public LoadSpaciesTask(String sppath) {
        this.filepath = sppath;
    }

    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void run() {
        System.out.println("RUNNING: " + filepath);
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Species tree is loading...");
            taskMonitor.setPercentCompleted(-1);
            fis = new FileInputStream(filepath);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            String treeString = br.readLine();
            try {
                DataParser.getInstance().readSpeciesString(treeString);
            } catch (SpeciesTreeFormatException ex) {
                if (errorListener != null) {
                    errorListener.errorOccured(new ErrorEvent(ex, this));
                }
            }
            taskMonitor.setPercentCompleted(100);
            br.close();
            dis.close();
            bis.close();
            fis.close();
        } catch (IOException ex) {
            if (errorListener != null) {
                errorListener.errorOccured(new ErrorEvent(ex, this));
            }
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
        return "Species tree is loading...";
    }
}
