package affinitymain;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.smart.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import listeners.ConsoleIterationListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class RunAlgorithm {

    private String filepath;
    private AffinityPropagationAlgorithm<String> af = new SmartPropagationAlgorithm();
    private double lambda;
    private int iterations;
    private double preferences;

    public RunAlgorithm(String filepath, double lambda, int iterations, double preferences) {
        this.filepath = filepath;
        this.lambda = lambda;
        this.iterations = iterations;
        this.preferences = preferences;
    }

    public void setParemeters() {
        af.setLambda(lambda);
        af.setIterations(iterations);
        af.setConvits(null);
        af.setConnectingMode(AffinityPropagationAlgorithm.WEIGHET_MODE);
        af.init();

        Collection<InteractionData> ints = new HashSet<InteractionData>();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        try {

            File inputSim = new File(filepath);
            fis = new FileInputStream(inputSim);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            while (br.ready()) {
                String[] line = br.readLine().split(" ");
                if (line.length == 3) {
                    ints.add(new InteractionData(line[0], line[1], Double.valueOf(line[2])));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RunAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
                dis.close();
                bis.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //    af.setN(ints.size());
        for (InteractionData interactionData : ints) {
            af.setSimilarities(interactionData.getFrom(), interactionData.getTo(), interactionData.getSim());
            af.setSimilarities(interactionData.getTo(), interactionData.getFrom(), interactionData.getSim());
        }
        af.setConstPreferences(preferences);
    }

    public void run() {
        af.addIterationListener(new ConsoleIterationListener(iterations));
        Map<String, Cluster<String>> clusters = af.doClusterAssoc();
        System.out.println(clusters.size());
        for (String clustName : clusters.keySet()) {
            System.out.println(clustName);
        }
    }
}
