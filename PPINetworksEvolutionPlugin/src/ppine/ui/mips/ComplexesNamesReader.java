package ppine.ui.mips;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.TreeSet;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesNamesReader {

    private Collection<String> complexesNames = new TreeSet<String>();
    private FileInputStream fis = null;
    private BufferedInputStream bis = null;
    private DataInputStream dis = null;
    private BufferedReader br = null;

    public Collection<String> readUserNames(File file) throws IOException {

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));

        while (br.ready()) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");

            if (tokens.length > 0) {
                complexesNames.add(tokens[0]);
            }
        }

        br.close();
        dis.close();
        bis.close();
        fis.close();
        ComplexesLogger.log("NAMES SIZE: " + complexesNames.size());
        return complexesNames;
    }

    public Collection<String> readAllNames(File file) throws IOException {

        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        br = new BufferedReader(new InputStreamReader(dis));

        while (br.ready()) {
            String line = br.readLine();
            line = line.toUpperCase();
            String[] tokens = line.split("[|]");

            if (tokens.length >= 2) {
                String complexName = tokens[1];

                if (complexName.length() >= 3) {
                //    System.out.println(complexName);
                    String subs = complexName.substring(0, 3);
                  //  System.out.println(subs);
                    if (!subs.equals("550")) {
                        complexesNames.add(complexName);
                    }
                }
            }
        }

        br.close();
        dis.close();
        bis.close();
        fis.close();
        ComplexesLogger.log("NAMES SIZE: " + complexesNames.size());
        return complexesNames;
    }

    public Collection<String> getNamesMapping() {
        return complexesNames;
    }
}
