package complexescomparing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesProteinsReader {

    private Complex proteinsComplex = new Complex("my_complex");
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public Complex readProteins(File file) throws IOException {

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));

        while (br.ready()) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            if (tokens.length > 0) {
                proteinsComplex.addProtein(tokens[0].toUpperCase());
            }
        }

        br.close();
        dis.close();
        bis.close();
        fis.close();
        Logger.log("COMPLEX SIZE " + proteinsComplex.size());
        return proteinsComplex;
    }

    public Complex getNamesMapping() {
        return proteinsComplex;
    }
}
