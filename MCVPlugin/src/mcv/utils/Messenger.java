package mcv.utils;

import cytoscape.Cytoscape;
import javax.swing.JOptionPane;

public class Messenger {

    public static void message(Object message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), message);
    }

    public static void error(Exception ex) {
        System.out.println(ex.getMessage());
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), ex.getMessage());
    }

    public static int confirmWarning(Object message) {
        System.out.println(message);
        return JOptionPane.showConfirmDialog(Cytoscape.getDesktop(), message, "Warning.", JOptionPane.WARNING_MESSAGE);
    }

    public static int confirmInfo(Object message) {
        System.out.println(message);
        return JOptionPane.showConfirmDialog(Cytoscape.getDesktop(), message, "Info.", JOptionPane.INFORMATION_MESSAGE);
    }
}
