package main;

import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MenusHandle {

    private static float progress;
    private static JTextArea memo = null;
    private static JProgressBar progressBar = null;

    public static void setProgress(double p) {
        progress = (float) p;
        progressBar.setValue(Math.round(progress));
    }

    public static void incProgress(double inc) {

        progress += inc;
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                progressBar.setValue(Math.round(progress));
            }
        });
        System.out.println(progress);
    }

    public static JProgressBar getProgressBar() {
        return progressBar;
    }

    public static void setProgressBar(JProgressBar progressBar) {
        MenusHandle.progressBar = progressBar;
    }

    public static JTextArea getMemo() {
        return memo;
    }

    public static void setMemo(JTextArea memo) {
        MenusHandle.memo = memo;
    }
}
