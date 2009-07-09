package ppine.io.exceptions;

import java.text.ParseException;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class FamiliesTreeFormatException extends ParseException {

    public FamiliesTreeFormatException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
