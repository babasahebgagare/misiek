package mcv.io.readers.tasks;

import mcv.io.parsers.rootparser.RootInteractionsParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.InteractionParserStruct;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.utils.IDCreator;

public class LoadSpeciesInteractionsTask extends MCVLoadTask {

    private Double treshold;
    private PPINetwork network;
    private int lineNumber = 0;

    LoadSpeciesInteractionsTask(String filepath, PPINetwork network, Double treshold) {
        super(filepath);
        this.network = network;
        this.treshold = treshold;
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        taskMonitor.setStatus("Interactions loading for: " + network.getID());
        taskMonitor.setPercentCompleted(-1);

        try {
            openStreams();
            taskMonitor.setPercentCompleted(0);
            reading();
            taskMonitor.setPercentCompleted(100);
            doneActionPerformed();
        } catch (InteractionsFileFormatException ex) {
            sendErrorEvent(ex, "Line number: " + String.valueOf(lineNumber), "LoadSpeciesInteractionsTask.run()");
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
        return "Loading interactions with treshold: " + String.valueOf(treshold);
    }

    private void reading() throws IOException, InteractionsFileFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        int count = 0;
        float percent = 0;
        float last_percent = 0;
        long current;

        while (br.ready()) {
            lineNumber++;
            InteractionParserStruct interaction = null;

            String line = br.readLine();
            interaction = RootInteractionsParser.readInteraction(line);

            String SourceID = interaction.getFrom();
            String TargetID = interaction.getTo();
            Double probability = interaction.getSim();

            if (treshold == null || probability > treshold) {
                if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                    String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);
                    dh.createInteraction(EdgeID, SourceID, TargetID, probability, network);
                    count++;
                }
            }

            current = fis.getChannel().position();
            percent = current * 100 / (float) max;
            if (percent > last_percent + 1) {
                last_percent = percent;
                taskMonitor.setPercentCompleted(Math.round(percent));
                taskMonitor.setStatus("Interactions loading for: " + network.getID() + "  " + count);
            }
        }
    }
}
