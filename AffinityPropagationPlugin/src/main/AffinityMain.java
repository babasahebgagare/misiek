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
import cytoscape.util.CytoscapeAction;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.VerticalLayout;
import panel.AffinityButtonsPanel;
import panel.AffinityChooseImplPanel;
import panel.AffinityConnModePanel;
import panel.AffinityGraphModePanel;
import panel.AffinityMainPanel;
import panel.AffinityPanelController;
import panel.AffinityStatsPanelController;

public class AffinityMain extends CytoscapePlugin {

    public AffinityMain() {
        //create a new action to respond to menu activation
        APPluginAction action = new APPluginAction();
        //set the preferred menu
        action.setPreferredMenu("Plugins");
        //and add it to the menus
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    public class APPluginAction extends CytoscapeAction {

        /**
         * The constructor sets the text that should appear on the menu item.
         */
        public APPluginAction() {
            super("APGraphClusteringPlugin");
        }

        /**
         * This method is called when the user selects the menu item.
         */
        public void actionPerformed(ActionEvent ae) {
            //get the network object; this contains the graph
            CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

            AffinityStatsPanelController psc = new AffinityStatsPanelController();
            AffinityPanelController pc = new AffinityPanelController(psc);

            JPanel myAff = new AffinityMainPanel();
            myAff.setLayout(new VerticalLayout());

            JPanel chooseImplPanel = new AffinityChooseImplPanel(pc);
            JPanel connModePanel = new AffinityConnModePanel(pc);
            JPanel graphModePanel = new AffinityGraphModePanel(pc);
            AffinityButtonsPanel actionButtonsPanel = new AffinityButtonsPanel(pc);
            actionButtonsPanel.addChooseImplPanel(chooseImplPanel);
            actionButtonsPanel.addConnModePanel(connModePanel);

            JPanel afpanel = pc.createAffinityPanel();
            JPanel stats = psc.createAffinityStatsPanel();

            myAff.add(afpanel);
            //      myAff.add(chooseImplPanel, 1);
            //      myAff.add(connModePanel, 2);
            myAff.add(graphModePanel);
            myAff.add(actionButtonsPanel);
            myAff.add(stats);

            leftPanel.add("APGraphClustringPlugin", myAff);
            //System.out.println("Affinity propagation");
        }
    }
}

