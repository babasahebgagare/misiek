package ppine.logs.errorsloger;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class MCVErrorsLogger {

    private static Integer errorID = Integer.valueOf(0);
    private static Map<Integer, MCVLoggedError> errors = new HashMap<Integer, MCVLoggedError>();

    public static void logMCVError(Exception e, String message, String source) {
        Date date = new Date();
        MCVLoggedError loggedError = new MCVLoggedError(errorID, e, date, message, source);
        errors.put(errorID, loggedError);
        errorID++;
    }

    public static void deleteMCVErrorLogged(Integer id) {
        errors.remove(id);
    }

    public static void deleteAll() {
        errors = new HashMap<Integer, MCVLoggedError>();
        errorID = 0;
    }

    public static Map<Integer, MCVLoggedError> getErrors() {
        return errors;
    }

    public static Collection<MCVLoggedError> getErrorsCollection() {
        return errors.values();
    }
}
