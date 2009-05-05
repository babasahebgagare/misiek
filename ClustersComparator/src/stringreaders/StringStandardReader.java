/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class StringStandardReader extends StringClusteringReader {

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

            while (scanner.hasNextInt()) {

                String line = scanner.nextLine();
                String[] tokens = line.split("\\s+");
                if (tokens.length == 2) {
                    String ex = tokens[0];
                    String cen = tokens[1];
                    clustering.put(ex, cen);
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR duging reading");
        } finally {
            scanner.close();
        }

        return clustering;
    }
}
