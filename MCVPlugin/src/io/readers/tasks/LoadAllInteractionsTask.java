package io.readers.tasks;

import io.parsers.defaultparser.DefaultInteractionsParser;
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
import logicmodel.controllers.DataHandle;
import main.PluginDataHandle;
import utils.IDCreator;
import utils.MemoLogger;

public class LoadAllInteractionsTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private double treshold;
    private File file;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;
    private int created = 0;
    private int all = 0;

    LoadAllInteractionsTask(String intpath, double treshold) {
        this.treshold = treshold;
        this.file = new File(intpath);
        this.max = file.length();
    }

    public void run() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        try {
            myThread = Thread.currentThread();
            taskMonitor.setStatus("Ładowanie interakcji");
            taskMonitor.setPercentCompleted(-1);
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));
            taskMonitor.setPercentCompleted(0);
            while (br.ready()) {
                all++;
                String SourceID = DefaultInteractionsParser.readWord(br);
                String TargetID = DefaultInteractionsParser.readWord(br);
                String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);
                Double Probability = Double.parseDouble(DefaultInteractionsParser.readWord(br));
                if (Probability.doubleValue() >= treshold) {
                    created++;
                    dh.createInteraction(EdgeID, SourceID, TargetID, Probability);
                }
                current = fis.getChannel().position();
                float percent = current * 100 / (float) max;
                taskMonitor.setPercentCompleted(Math.round(percent));
            }
            br.close();
            dis.close();
            bis.close();
            fis.close();
            MemoLogger.log("Załadowano: " + Math.round((double) created * 100 / (double) all) + "% powyżej progu");
            taskMonitor.setPercentCompleted(100);
        } catch (IOException ex) {
            Logger.getLogger(LoadAllInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
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
                //TODO
                ((JTask) taskMonitor).setDone();
            } catch (IOException ex) {
                Logger.getLogger(LoadAllInteractionsTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Czytanie interakcji z odcięciem: " + treshold + " dla wszystkich sieci...";
    }
}
