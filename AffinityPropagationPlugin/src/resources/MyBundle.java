package resources;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class MyBundle {

    private static Locale locale = null;

    public static void setLocale(String lang) {
        locale = new Locale(lang);
    }

    public static String getMessage(String bundle) {
        if (locale == null) {
            return ResourceBundle.getBundle("resources/lang").getString(bundle);
        }
        return ResourceBundle.getBundle("resources/lang", locale).getString(bundle);
    }
}
