package mcv.io.parsers.rootparser;

import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.InteractionParserStruct;

/**
 *
 * @author misiek
 */
public class RootInteractionsParser {

    /**
     * 
     * @param line
     * @return
     * @throws InteractionsFileFormatException 
     */
    public static InteractionParserStruct readInteraction(String line) throws InteractionsFileFormatException {

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
