package ppine.io.exceptions;

import java.text.ParseException;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExperimentsFileFormatException extends ParseException {

    public ExperimentsFileFormatException(String s, int errorOffset) {
        super(s, errorOffset);
    }
}
