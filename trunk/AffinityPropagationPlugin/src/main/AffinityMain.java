package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import panel.AffinityPanelController;
import panel.AffinityStatsPanelController;

public class AffinityMain extends CytoscapePlugin {

    public AffinityMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        JPanel myAff = new JPanel();
        myAff.setLayout(new FlowLayout());

        AffinityStatsPanelController psc = new AffinityStatsPanelController();
        AffinityPanelController pc = new AffinityPanelController(psc);

        JPanel afpanel = pc.createAffinityPanel();
        JPanel stats = psc.createAffinityStatsPanel();
        myAff.add(afpanel);
        myAff.add(stats);

        leftPanel.add("Affinity panel", myAff);
        System.out.println("Affinity propagation");
    }
}

