package mcv.io.readers.tasks;

import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.rootparser.RootInteractionsParser;
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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mcv.io.parsers.InteractionParserStruct;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.utils.IDCreator;
import mcv.utils.MemoLogger;
import org.jdesktop.swingx.error.ErrorListener;

public class LoadAllInteractionsTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private Map<String, Double> tresholds;
    private String filepath;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;
    private int created = 0;
    private int all = 0;
    private ErrorListener errorListener = null;

    LoadAllInteractionsTask(String intpath, Map<String, Double> tresholds) {
        this.tresholds = tresholds;
        this.filepath = intpath;
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
            taskMonitor.setStatus("Interactions loading...");
            taskMonitor.setPercentCompleted(-1);
            File file = new File(filepath);
            max = file.length();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            taskMonitor.setPercentCompleted(0);
            while (br.ready()) {
                all++;
                InteractionParserStruct interaction = null;
                try {
                    interaction = RootInteractionsParser.readInteraction(br);

                    String SourceID = interaction.getFrom();
                    String TargetID = interaction.getTo();
                    Double probability = interaction.getSim();
                    String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);
                    PPINetwork netOrNull = dh.tryFindPPINetworkByProteinID(SourceID);

                    if (netOrNull != null) {
                        if (tresholds.containsKey(netOrNull.getID())) {
                            Double treshold = tresholds.get(netOrNull.getID());

                            if (treshold == null || probability > treshold) {
                                created++;
                                dh.createInteraction(EdgeID, SourceID, TargetID, probability);
                            }
                        }
                    }
                } catch (InteractionsFileFormatException ex) {
                }
                current = fis.getChannel().position();
                percent = current * 100 / (float) max;
                if (percent > last_percent + 1) {
                    last_percent = percent;
                    taskMonitor.setPercentCompleted(Math.round(percent));
                }
            }
            MemoLogger.log("Loaded: " + Math.round((double) created * 100 / (double) all) + "% up than treshold");
            taskMonitor.setPercentCompleted(100);
        } catch (IOException ex) {
            Logger.getLogger(LoadAllInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                dis.close();
                bis.close();
                fis.close();

            } catch (IOException ex) {
                Logger.getLogger(LoadAllInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
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
        return "Loading interactions with tresholds...";
    }
}
