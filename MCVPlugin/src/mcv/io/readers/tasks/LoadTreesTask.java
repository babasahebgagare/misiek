package mcv.io.readers.tasks;

import mcv.io.exceptions.FamiliesTreeFormatException;
import mcv.io.parsers.DataParser;
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
import mcv.main.LoadedDataHandle;
import mcv.main.PluginDataHandle;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

public class LoadTreesTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private String filepath;
    private ErrorListener errorListener = null;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public LoadTreesTask(String treespath) {
        this.filepath = treespath;

    }

    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void run() {
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Loading proteins data...");
            taskMonitor.setPercentCompleted(-1);
            File file = new File(filepath);
            max = file.length();
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
                        try {
                            DataParser.getInstance().readFamiliesTreeString(family);
                        } catch (FamiliesTreeFormatException ex) {
                            if (errorListener != null) {
                                errorListener.errorOccured(new ErrorEvent(ex, this));
                            }
                        }
                    }
                    current = fis.getChannel().position();
                    float percent = current * 100 / (float) max;
                    taskMonitor.setPercentCompleted(Math.round(percent));
                }
            }
            PluginDataHandle.getLoadedDataHandle().setProteinsLoaded(true);
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
                Logger.getLogger(LoadTreesTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Loading proteins data...";
    }
}
