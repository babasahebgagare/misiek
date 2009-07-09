package ppine.logs.errorsloger;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PPINEErrorsLogger {

    private static Integer errorID = Integer.valueOf(0);
    private static Map<Integer, PPINELoggedError> errors = new HashMap<Integer, PPINELoggedError>();

    public static void logMCVError(Exception e, String message, String source) {
        Date date = new Date();
        PPINELoggedError loggedError = new PPINELoggedError(errorID, e, date, message, source);
        errors.put(errorID, loggedError);
        errorID++;
    }

    public static void deleteMCVErrorLogged(Integer id) {
        errors.remove(id);
    }

    public static void deleteAll() {
        errors = new HashMap<Integer, PPINELoggedError>();
        errorID = 0;
    }

    public static Map<Integer, PPINELoggedError> getErrors() {
        return errors;
    }

    public static Collection<PPINELoggedError> getErrorsCollection() {
        return errors.values();
    }
}
