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
public class MCLFormReader extends ClusteringReader {

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
            Integer cen = Integer.valueOf(0);

            while (scanner.hasNextInt()) {
                String line = scanner.nextLine();
                String[] exs = line.split("\\s+");

                for (String examplar : exs) {
                    Integer ex = Integer.valueOf(examplar);
                    clustering.put(ex, cen);
                }

                cen++;

            }
        } catch (IOException e) {
            System.out.println("ERROR duging reading");
        } finally {
            scanner.close();
        }

        return clustering;

    }
}
