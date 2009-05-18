package mcv.io.readers.tasks;

import java.io.FileNotFoundException;
import mcv.io.exceptions.SpeciesTreeFormatException;
import mcv.io.parsers.DataParser;
import java.io.IOException;

public class LoadSpaciesTask extends MCVLoadTask {

    public LoadSpaciesTask(String sppath) {
        super(sppath);
    }

    public void run() {
        myThread = Thread.currentThread();
        taskMonitor.setStatus("Species tree is loading...");
        taskMonitor.setPercentCompleted(-1);

        try {

            openStreams();
            reading();
            taskMonitor.setPercentCompleted(100);
            doneActionPerformed();
        } catch (SpeciesTreeFormatException ex) {
            sendErrorEvent(ex);
        } catch (FileNotFoundException ex) {
            sendErrorEvent(ex);
        } catch (IOException ex) {
            sendErrorEvent(ex);
        } finally {
            try {
                closeStreams();
            } catch (IOException ex) {
                sendErrorEvent(ex);
            }
        }
    }

    public String getTitle() {
        return "Species tree is loading...";
    }

    private void reading() throws IOException, SpeciesTreeFormatException {

        String treeString = br.readLine();
        DataParser.getInstance().readSpeciesString(treeString);
    }
}
