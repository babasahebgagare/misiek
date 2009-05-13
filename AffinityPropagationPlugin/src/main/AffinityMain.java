package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import panel.AffinityButtonsPanel;
import panel.AffinityChooseImplPanel;
import panel.AffinityConnModePanel;
import panel.AffinityMainPanel;
import panel.AffinityPanelController;
import panel.AffinityStatsPanelController;

public class AffinityMain extends CytoscapePlugin {

    public AffinityMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        AffinityStatsPanelController psc = new AffinityStatsPanelController();
        AffinityPanelController pc = new AffinityPanelController(psc);

        JPanel myAff = new AffinityMainPanel();
        myAff.setLayout(new VerticalLayout());

        JPanel chooseImplPanel = new AffinityChooseImplPanel(pc);
        JPanel connModePanel = new AffinityConnModePanel(pc);
        JPanel actionButtonsPanel = new AffinityButtonsPanel(pc);

        JPanel afpanel = pc.createAffinityPanel();
        JPanel stats = psc.createAffinityStatsPanel();

        myAff.add(afpanel);
        myAff.add(chooseImplPanel);
        myAff.add(connModePanel);
        myAff.add(actionButtonsPanel);
        myAff.add(stats);

        leftPanel.add("Affinity panel", myAff);
        System.out.println("Affinity propagation");
    }
}

