package complexescomparing;

import javax.swing.JTextArea;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class Logger {

    private static JTextArea textArea;

    public static void setTextArea(JTextArea area) {
        textArea = area;
    }

    public static void log(Object msg) {
        textArea.append(msg.toString() + "\n");
        System.out.println(msg.toString());
    }
}
