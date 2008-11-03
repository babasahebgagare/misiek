package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import main.MenusHandle;

public class MemoLogger {

    private static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    private static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());

    }

    public static void log(String msg) {
        MenusHandle.getMemo().append(now() + ": " + msg + "\n");
    }
}
