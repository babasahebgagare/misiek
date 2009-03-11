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
import java.io.InputStreamReader;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import utils.IDCreator;

public class LoadInteractionsTask implements Task {

    private TaskMonitor taskMonitor = null;
    private Thread myThread = null;
    private CytoAbstractPPINetwork cytoNetwork = null;
    private double treshold;
    private File file;
    private long max;
    private long current;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public LoadInteractionsTask(String intpath, CytoAbstractPPINetwork cytoNetwork, double treshold) {
        this.cytoNetwork = cytoNetwork;
        this.treshold = treshold;
        this.file = new File(intpath);
        this.max = file.length();
    }

    public void run() {
        myThread = Thread.currentThread();

        taskMonitor.setStatus("Ładowanie interakcji");
        taskMonitor.setPercentCompleted(-1);
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            taskMonitor.setPercentCompleted(0);

            while (br.ready()) {
                try {

                    String SourceID = DefaultInteractionsParser.readWord(br);
                    String TargetID = DefaultInteractionsParser.readWord(br);
                    String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);

                    Double Probability = Double.parseDouble(DefaultInteractionsParser.readWord(br));

                    if (Probability.doubleValue() >= treshold && cytoNetwork.containsCytoProtein(SourceID) && cytoNetwork.containsCytoProtein(TargetID)) {
                        CytoDataHandle.createCytoInteraction(EdgeID, SourceID, TargetID, Probability, cytoNetwork);
                    }

                    current = fis.getChannel().position();
                    float percent = current * 100 / (float) max;
                    taskMonitor.setPercentCompleted(Math.round(percent));

                } catch (Exception ex) {
                    taskMonitor.setException(ex, "Problem podczas ładowania interakcji");
                    System.out.println(ex.getMessage());
                }
            }


            br.close();
            dis.close();
            bis.close();
            fis.close();

            taskMonitor.setPercentCompleted(100);
        } catch (Exception e) {
            taskMonitor.setException(e, "Problem podczas tworzenia lub zamykania strumieni");

            return;
        }
    }

    public void halt() {

        System.out.println("zatrzymywanie działania");

        try {
            if (myThread != null) {
                myThread.interrupt();
                br.close();
                dis.close();
                bis.close();
                fis.close();
                cytoNetwork.deleteCytoInteractions();
                ((JTask) taskMonitor).setDone();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return "Czytanie danych interakcji dla sieci: " + cytoNetwork.getID();
    }
}
