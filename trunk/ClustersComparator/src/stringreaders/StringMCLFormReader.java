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
public class StringMCLFormReader extends StringClusteringReader {

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

            while (scanner.hasNextInt()) {
                String line = scanner.nextLine();
                String[] exs = line.split("\\s+");
                if (exs.length > 0) {
                    String cen = exs[0];

                    for (String examplar : exs) {
                        String ex = String.valueOf(examplar);
                        clustering.put(ex, cen);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR duging reading");
        } finally {
            scanner.close();
        }

        return clustering;

    }
}
