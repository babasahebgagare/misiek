/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcv.io.parsers.rootparser;

import mcv.io.exceptions.ExperimentsFileFormatException;
import mcv.io.exceptions.InteractionsFileFormatException;
import mcv.io.parsers.ExperimentParserStruct;
import mcv.io.parsers.InteractionParserStruct;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class RootExperimentsParser {

    public static ExperimentParserStruct readExperiment(String line) throws ExperimentsFileFormatException {

        String[] tokens = line.split("\\s+");
        if (tokens.length != 4) {
            throw new ExperimentsFileFormatException(line, 0);
        }

        return new ExperimentParserStruct(tokens[0], tokens[1], tokens[3], tokens[2]);
    }
}
