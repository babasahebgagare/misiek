package affinitymain;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.matrix.MatrixPropagationAlgorithm;
import algorithm.smart.Cluster;
import algorithm.smart.SmartPropagationAlgorithm;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

    private String inputpath;
    private String outpath;
    private AffinityPropagationAlgorithm<String> af = new SmartPropagationAlgorithm();
    private double lambda;
    private int iterations;
    private double preferences;
    private Collection<String> nodeNames = new HashSet<String>();

    public RunAlgorithm(String inputpath, String outpath, double lambda, int iterations, double preferences) {
        this.inputpath = inputpath;
        this.outpath = outpath;
        this.lambda = lambda;
        this.iterations = iterations;
        this.preferences = preferences;
    }

    public void setParemeters() {
        af.setLambda(lambda);
        af.setIterations(iterations);
        af.setConvits(null);
        af.setConnectingMode(AffinityPropagationAlgorithm.WEIGHET_MODE);
        af.addIterationListener(new ConsoleIterationListener(iterations));


        Collection<InteractionData> ints = new HashSet<InteractionData>();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        try {

            File inputSim = new File(inputpath);
            fis = new FileInputStream(inputSim);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            while (br.ready()) {
                String[] line = br.readLine().split(" ");
                //   System.out.println(line.length);
                if (line.length == 3) {
                    nodeNames.add(line[0]);
                    nodeNames.add(line[1]);
                    ints.add(new InteractionData(line[0], line[1], Double.valueOf(line[2])));
                } else {
                    System.out.println("BLAD WCZYTYWANIA DANYCH");
                    return;
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
        af.setN(nodeNames.size()+1);
        af.init();
        for (InteractionData intData : ints) {
            //     System.out.println(intData.getFrom() + " " + intData.getTo() + " " + intData.getSim());
            af.setSimilarities(intData.getFrom(), intData.getTo(), intData.getSim());
        }
        af.setConstPreferences(preferences);
    }

    public void run() {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedWriter bw = null;
        try {
            File outputCenters = new File(outpath);
            fos = new FileOutputStream(outputCenters);
            bos = new BufferedOutputStream(fos);
            bw = new BufferedWriter(new OutputStreamWriter(bos));
            Map<String, Cluster<String>> clusters = af.doClusterAssoc();
            System.out.println(clusters.size());
            for (String clustName : clusters.keySet()) {
                bw.append(clustName + "\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(RunAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
                bos.close();
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(RunAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
