package mcv.ui;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;

public class PluginMenusHandle {

    private static JTextArea memo = null;
    private static JTree tree = null;
    private static JList familiesList = null;
    private static JButton doProjectionButton = null;
    private static JButton showNetworkButton = null;
    private static JButton showLoadedInteractionsButton = null;
    private static JButton newDataButton = null;
    private static JButton updateDataButton = null;
    private static JPanel mcvPanel = null;

    public static JList getFamiliesList() {
        return familiesList;
    }

    public static void setFamiliesList(JList familiesList) {
        PluginMenusHandle.familiesList = familiesList;
    }

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

    public static JButton getDoProjectionButton() {
        return doProjectionButton;
    }

    public static void setDoProjectionButton(JButton doProjectionButton) {
        PluginMenusHandle.doProjectionButton = doProjectionButton;
    }

    public static JButton getShowNetworkButton() {
        return showNetworkButton;
    }

    public static void setShowNetowrkButton(JButton showNetworkButton) {
        PluginMenusHandle.showNetworkButton = showNetworkButton;
    }

    public static JButton getShowLoadedInteractionsButton() {
        return showLoadedInteractionsButton;
    }

    public static void setShowLoadedInteractionsButton(JButton showLoadedInteractionsButton) {
        PluginMenusHandle.showLoadedInteractionsButton = showLoadedInteractionsButton;
    }

    public static JButton getNewDataButton() {
        return newDataButton;
    }

    public static void setNewDataButton(JButton newDataButton) {
        PluginMenusHandle.newDataButton = newDataButton;
    }

    public static JButton getUpdateDataButton() {
        return updateDataButton;
    }

    public static void setUpdateDataButton(JButton updateDataButton) {
        PluginMenusHandle.updateDataButton = updateDataButton;
    }

    public static JPanel getMcvPanel() {
        return mcvPanel;
    }

    public static void setMcvPanel(JPanel mcvPanel) {
        PluginMenusHandle.mcvPanel = mcvPanel;
    }
}
