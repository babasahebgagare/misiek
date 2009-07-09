package ppine.ui;

import ppine.ui.familycolors.SpeciesFamilyColorPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;

public class PluginMenusHandle {

    private static JTextArea memo = null;
    private static JTree tree = null;
    private static JButton doProjectionButton = null;
    private static JButton showNetworkButton = null;
    private static JButton showLoadedInteractionsButton = null;
    private static JButton newDataButton = null;
    private static JButton deleteDataButton = null;
    private static JButton updateDataButton = null;
    private static JPanel mcvPanel = null;
    private static SpeciesFamilyColorPanel familiesColorListPanel = null;

    public static SpeciesFamilyColorPanel getFamiliesColorListPanel() {
        return familiesColorListPanel;
    }

    public static JButton getDeleteDataButton() {
        return deleteDataButton;
    }

    public static void setDeleteDataButton(JButton deleteDataButton) {
        PluginMenusHandle.deleteDataButton = deleteDataButton;
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

    public static void setFamiliesColorListPanel(SpeciesFamilyColorPanel familiesColorListPanel) {
        PluginMenusHandle.familiesColorListPanel = familiesColorListPanel;
    }

    public static void initButtonsState() {
        getShowNetworkButton().setEnabled(false);
        getDoProjectionButton().setEnabled(false);
        getShowLoadedInteractionsButton().setEnabled(false);
        getDoProjectionButton().setEnabled(false);
        getNewDataButton().setEnabled(true);
        getUpdateDataButton().setEnabled(false);
    }

    public static void refreshUIafterDeleteData() {
        PluginMenusHandle.getUpdateDataButton().setEnabled(false);
        PluginMenusHandle.getShowNetworkButton().setEnabled(false);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getDoProjectionButton().setEnabled(false);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(false);
        PluginMenusHandle.getDeleteDataButton().setEnabled(false);
    }

    public static void refreshUIafterProteinsLoading() {
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
        PluginMenusHandle.getShowNetworkButton().setEnabled(true);
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }

    public static void refreshUIafterSpeciesLoading() {
        PluginMenusHandle.getNewDataButton().setEnabled(true);
        PluginMenusHandle.getUpdateDataButton().setEnabled(true);
        PluginMenusHandle.getDeleteDataButton().setEnabled(true);
    }
}
