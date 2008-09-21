package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import javax.swing.JOptionPane;

public class Main extends CytoscapePlugin {

    public Main() {
        String message = "Hello World!";
        System.out.println(message);
        // use the CytoscapeDesktop as parent for a Swing dialog
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), message);
    }
}

