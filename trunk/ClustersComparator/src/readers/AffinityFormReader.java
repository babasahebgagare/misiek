package readers;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffinityFormReader extends ClusteringReader {

    /**
     *
     * @return
     */
    @Override
    public Map<Integer, Integer> readClustering() {
        if (filename == null) {
            return null;
        }
        Map<Integer, Integer> clustering = new TreeMap<Integer, Integer>();

        Scanner scanner = null;

        try {
            File inputSim = new File(filename);
            scanner = new Scanner(inputSim);
            Integer ex = Integer.valueOf(0);

            while (scanner.hasNextInt()) {
                ex++;
                Integer cen = Integer.valueOf(scanner.nextInt());
                clustering.put(ex, cen);
            }
        } catch (IOException ex) {
            System.out.println("ERROR duging reading");
        } finally {
            scanner.close();
        }

        return clustering;
    }
}
