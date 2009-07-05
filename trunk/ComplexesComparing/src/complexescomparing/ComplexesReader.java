package complexescomparing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesReader {

    private Map<String, Complex> complexes = new TreeMap<String, Complex>();
    private Collection<String> complexesNames;
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public ComplexesReader(Collection<String> complexesNames) {
        this.complexesNames = complexesNames;
        createComplexes(complexesNames);
    }

    public Map<String, Complex> readComplexes(File file) throws IOException {

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));

        while (br.ready()) {
            String line = br.readLine();
            line = line.toUpperCase();
            String[] tokens = line.split("[|]");

            if (tokens.length >= 2) {
                //    System.out.println("Analysing: " + tokens[0] + " " + tokens[1]);
                for (String name : complexesNames) {
                    if (tokens[1].contains(name)) {
                        Complex complex = complexes.get(name);
                        complex.addProtein(tokens[0]);
                    }
                }
            }
        }

        br.close();
        dis.close();
        bis.close();
        fis.close();
        return complexes;
    }

    public Map<String, Complex> getComplexes() {
        return complexes;
    }

    private void createComplexes(Collection<String> complexesNames) {
        for (String name : complexesNames) {
            Complex complex = new Complex(name);
            complexes.put(name, complex);
        }
    }
}
