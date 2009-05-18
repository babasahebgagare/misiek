package mcv.io.parsers.defaultparser;

import java.io.BufferedReader;
import java.io.IOException;
import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.InteractionParserStruct;

/**
 *
 * @author misiek
 */
public class DefaultInteractionsParser {

    public static InteractionParserStruct readInteraction(BufferedReader br) throws IOException, InteractionsFileFormatException {

        String line = br.readLine();
        String[] tokens = line.split("\\s+");
        if (tokens.length != 3) {
            throw new InteractionsFileFormatException(line, 0);
        }
        Double sim;

        try {
            sim = Double.valueOf(tokens[2]);
        } catch (NumberFormatException e) {
            throw new InteractionsFileFormatException(line, 0);
        }
        return new InteractionParserStruct(tokens[0], tokens[1], sim);
    }
}
