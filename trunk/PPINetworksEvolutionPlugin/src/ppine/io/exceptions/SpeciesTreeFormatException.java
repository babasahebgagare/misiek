package ppine.io.exceptions;

import java.text.ParseException;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class SpeciesTreeFormatException extends ParseException {

    public SpeciesTreeFormatException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
