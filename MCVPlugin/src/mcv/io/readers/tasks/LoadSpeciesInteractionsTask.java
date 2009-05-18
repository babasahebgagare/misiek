package mcv.io.readers.tasks;

import mcv.io.parsers.defaultparser.DefaultInteractionsParser;
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
import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.InteractionParserStruct;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.utils.IDCreator;
import mcv.utils.MemoLogger;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

public class LoadSpeciesInteractionsTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private Double treshold;
    private String filepath;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;
    private int created = 0;
    private int all = 0;
    private int count = 0;
    private PPINetwork network;
    private ErrorListener errorListener = null;

    LoadSpeciesInteractionsTask(String filepath, PPINetwork network, Double treshold) {
        this.network = network;
        this.treshold = treshold;
        this.filepath = filepath;
    }

    public void setErrorListener(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void run() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        float percent = 0;
        float last_percent = 0;
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Interactions loading for: " + network.getID());
            taskMonitor.setPercentCompleted(-1);
            File file = new File(filepath);
            this.max = file.length();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            taskMonitor.setPercentCompleted(0);
            while (br.ready()) {
                all++;
                InteractionParserStruct interaction = null;
                try {
                    interaction = DefaultInteractionsParser.readInteraction(br);

                    String SourceID = interaction.getFrom();
                    String TargetID = interaction.getTo();
                    Double probability = interaction.getSim();
                    String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);

                    if (treshold == null || probability > treshold) {
                        created++;
                        if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                            dh.createInteraction(EdgeID, SourceID, TargetID, probability, network);
                            count++;
                        } else {
                            System.out.println("BLAD: " + TargetID + " " + SourceID);
                        }
                    }

                } catch (InteractionsFileFormatException e) {
                    errorListener.errorOccured(new ErrorEvent(e, "1221221"));
                }
                current = fis.getChannel().position();
                percent = current * 100 / (float) max;
                if (percent > last_percent + 1) {
                    last_percent = percent;
                    taskMonitor.setPercentCompleted(Math.round(percent));
                    taskMonitor.setStatus("Interactions loading for: " + network.getID() + "  " + count);
                }
            }
            MemoLogger.log("Loaded: " + Math.round((double) created * 100 / (double) all) + "% up than treshold");
            taskMonitor.setPercentCompleted(100);
        } catch (IOException ex) {
            Logger.getLogger(LoadSpeciesInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                dis.close();
                bis.close();
                fis.close();

            } catch (IOException ex) {
                Logger.getLogger(LoadSpeciesInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @SuppressWarnings("deprecation")
    public void halt() {

        System.out.println("Stopping...");

        if (myThread != null) {

            myThread.stop();
            //TODO
            ((JTask) taskMonitor).setDone();

        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Loading interactions with treshold: " + String.valueOf(treshold);
    }
}
