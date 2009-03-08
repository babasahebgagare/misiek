package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import panel.AffinityPanelController;

public class AffinityMain extends CytoscapePlugin {

    public AffinityMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        AffinityPanelController pc = new AffinityPanelController();
        JPanel afpanel = pc.createAffinityPanel();
        leftPanel.add("Affinity", afpanel);
        System.out.println("Affinity propagation");
    }
}

