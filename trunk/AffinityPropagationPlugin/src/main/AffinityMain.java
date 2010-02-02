/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of Affinity Propagation
 * algorithm as Cytoscape plugin.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * APGraphClusteringPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */


package main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
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
     //   JPanel graphModePanel = new AffinityGraphModePanel(pc);
        JPanel actionButtonsPanel = new AffinityButtonsPanel(pc);

        JPanel afpanel = pc.createAffinityPanel();
        JPanel stats = psc.createAffinityStatsPanel();

        myAff.add(afpanel, 0);
        myAff.add(chooseImplPanel, 1);
        myAff.add(connModePanel, 2);
    //    myAff.add(graphModePanel);
        myAff.add(actionButtonsPanel, 3);
        myAff.add(stats, 4);

        leftPanel.add("APGraphClustringPlugin", myAff);
        //System.out.println("Affinity propagation");
    }
}

