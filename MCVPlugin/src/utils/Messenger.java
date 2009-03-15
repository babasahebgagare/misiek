package utils;

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
}
