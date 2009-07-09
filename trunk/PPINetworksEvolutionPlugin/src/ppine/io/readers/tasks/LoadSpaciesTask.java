package ppine.io.readers.tasks;

import java.io.FileNotFoundException;
import ppine.io.exceptions.SpeciesTreeFormatException;
import ppine.io.parsers.DataParser;
import java.io.IOException;

public class LoadSpaciesTask extends PPINELoadTask {

    private int lineNumber = 0;

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
            sendErrorEvent(ex, "Line number: " + String.valueOf(lineNumber), "SpeciesTreeFormatException.run()");
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
        lineNumber++;
        DataParser.getInstance().readSpeciesString(treeString);
    }
}
