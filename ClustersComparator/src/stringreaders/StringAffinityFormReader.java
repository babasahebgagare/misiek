package stringreaders;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class StringAffinityFormReader extends StringClusteringReader {

    /**
     *
     * @return
     */
    @Override
    public Map<String, String> readClustering() {
        if (filename == null) {
            return null;
        }
        Map<String, String> clustering = new TreeMap<String, String>();

        Scanner scanner = null;

        try {
            File inputSim = new File(filename);
            scanner = new Scanner(inputSim);
            Integer ex = Integer.valueOf(0);

            while (scanner.hasNextInt()) {
                ex++;
                String cen = String.valueOf(scanner.nextInt());
                clustering.put(String.valueOf(ex), cen);
            }
        } catch (IOException ex) {
            System.out.println("ERROR duging reading");
        } finally {
            scanner.close();
        }

        return clustering;
    }
}
