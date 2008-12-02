package IO.tasks;

import IO.defaultreader.DefaultTreeParser;
import cytoscape.task.Task;
import cytoscape.task.TaskMonitor;
import cytoscape.task.ui.JTask;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class LoadTreesTask implements Task {

    private TaskMonitor taskMonitor;
    private Thread myThread = null;
    private File file;
    private long max;
    private long current;
    private FileInputStream fis;
    private BufferedInputStream bis;
    private DataInputStream dis;
    private BufferedReader br;

    public LoadTreesTask(String treespath) {
        this.file = new File(treespath);
        this.max = file.length();

    }

    public void run() {
        myThread = Thread.currentThread();

        taskMonitor.setStatus("Ładowanie białek");
        taskMonitor.setPercentCompleted(-1);
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            taskMonitor.setPercentCompleted(0);

            while (br.ready()) {
                try {
                    String line = null;

                    line = br.readLine();

                    if (line != null && !line.equals("")) {
                        String[] families = line.split(";");

                        for (String family : families) {
                            DefaultTreeParser.readAllTreeString(family);
                        }

                        current = fis.getChannel().position();
                        float percent = current * 100 / max;
                        taskMonitor.setPercentCompleted(Math.round(percent));
                    }
                } catch (Exception ex) {
                    taskMonitor.setException(ex, "Problem podczas ładowania drzew genow");
                }
            }

            taskMonitor.setPercentCompleted(100);
            br.close();
            dis.close();
            bis.close();
            fis.close();

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
                ((JTask) taskMonitor).setDone();
            }
        } catch (Exception ex) {
            System.out.println("Problem podczas przerywania wczytywania danych");
        }
    }

    public void setTaskMonitor(TaskMonitor taskMonitor) throws IllegalThreadStateException {
        this.taskMonitor = taskMonitor;
    }

    public String getTitle() {
        return new String("Wczytywanie drzew rodzin białek");
    }
}
