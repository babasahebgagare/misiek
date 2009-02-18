package main;

import javax.swing.JTextArea;

public class MenusHandle {

    private static JTextArea memo = null;

    public static JTextArea getMemo() {
        return memo;
    }

    public static void setMemo(JTextArea memo) {
        MenusHandle.memo = memo;
    }
}
