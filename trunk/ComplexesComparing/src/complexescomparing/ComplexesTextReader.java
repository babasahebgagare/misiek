package complexescomparing;

import java.io.IOException;
import javax.swing.JTextArea;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesTextReader {

    private Complex proteinsComplex = new Complex("my_complex");

    public Complex readProteins(JTextArea area) throws IOException {

        String text = area.getText();
        String[] lines = text.split("[\n]");
        int count = lines.length;
        int i = 0;


        System.out.println(count);
        while (i < count) {
            String line = lines[i];
            String[] tokens = line.split("\\s+");
            System.out.println(line);

            if (tokens.length > 0) {
                proteinsComplex.addProtein(tokens[0].toUpperCase());
            }
            i++;
        }

        return proteinsComplex;
    }

    public Complex getNamesMapping() {
        return proteinsComplex;
    }
}

