package setscomparator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class SetsReader {

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Collection<Integer> loadSet() {
        Collection<Integer> set = new TreeSet<Integer>();
        Scanner scanner = null;
        try {

            File inputSim = new File(filename);
            scanner = new Scanner(inputSim);

            while (scanner.hasNextInt()) {
                Integer val = Integer.valueOf(scanner.nextInt());
                set.add(val);
            }
        } catch (IOException ex) {
            System.out.println("ERROR");
        } finally {
            scanner.close();
        }
        return set;
    }
}
