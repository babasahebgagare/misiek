package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import panel.AffinityPanelController;
import panel.AffinityStatsPanel;

public class AffinityMain extends CytoscapePlugin {

    public AffinityMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        AffinityPanelController pc = new AffinityPanelController();
        JPanel myAff = new JPanel();

        myAff.setLayout(new FlowLayout());
        JPanel afpanel = pc.createAffinityPanel();
        JPanel stats = new AffinityStatsPanel();
        myAff.add(afpanel);
        myAff.add(stats);


        leftPanel.add("Affinity", myAff);
        System.out.println("Affinity propagation");
    }
}

