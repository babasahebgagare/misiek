package ppine.io.readers.tasks;

import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTask;
import ppine.logs.errorsloger.MCVErrorsLogger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import ppine.utils.Messenger;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public abstract class MCVLoadTask implements Task {

    protected ErrorListener errorListener = null;
    protected ActionListener doneListener = null;
    protected TaskMonitor taskMonitor = null;
    protected String filepath;
    protected Thread myThread = null;
    protected long max;
    protected FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    protected BufferedReader br = null;

    public MCVLoadTask(String filepath) {
        this.filepath = filepath;
    }

    public abstract void run();

    public abstract String getTitle();

    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void setDoneListener(ActionListener doneListener) {
        this.doneListener = doneListener;
    }

    @SuppressWarnings("deprecation")
    public void halt() {
        System.out.println("Stopping...");

        if (myThread != null) {

            try {
                closeStreams();
            } catch (IOException ex) {
                sendErrorEvent(ex);
            }
            myThread.stop();
            ((JTask) taskMonitor).setDone();
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    protected void closeStreams() throws IOException {
        br.close();
        dis.close();
        bis.close();
        fis.close();

    }

    protected void openStreams() throws FileNotFoundException {
        File file = new File(filepath);
        max = file.length();
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));
    }

    protected void sendErrorEvent(Exception ex) {
        sendErrorEvent(ex, null, null);
    }

    protected void sendErrorEvent(Exception ex, String msg) {
        sendErrorEvent(ex, msg, null);
    }

    protected void sendErrorEvent(Exception ex, String msg, String source) {
        if (errorListener != null) {
            errorListener.errorOccured(new ErrorEvent(ex, this));
        } else {
            Messenger.error(ex);
        }
        MCVErrorsLogger.logMCVError(ex, msg, source);
    }

    protected void doneActionPerformed(Object source, int id, String command) {
        if (doneListener != null) {
            doneListener.actionPerformed(new ActionEvent(source, id, command));
        }
    }

    protected void doneActionPerformed() {
        doneActionPerformed(this, 0, "Data loaded");
    }
}
