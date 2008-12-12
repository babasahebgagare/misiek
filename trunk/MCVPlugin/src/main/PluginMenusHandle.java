package main;

import javax.swing.JTextArea;
import javax.swing.JTree;

public class PluginMenusHandle {

    private static JTextArea memo = null;
    private static JTree tree = null;

    public static JTree getTree() {
        return tree;
    }

    public static void setTree(JTree tree) {
        PluginMenusHandle.tree = tree;
    }

    public static JTextArea getMemo() {
        return memo;
    }

    public static void setMemo(JTextArea memo) {
        PluginMenusHandle.memo = memo;
    }
}
