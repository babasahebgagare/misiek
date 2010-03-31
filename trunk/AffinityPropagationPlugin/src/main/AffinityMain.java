/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of affinity propagation
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
import panel.AffinityButtonsPanel;
import panel.AffinityChooseImplPanel;
import panel.AffinityConnModePanel;
import panel.AffinityGraphModePanel;
import panel.AffinityMainPanel;
import panel.AffinityPanelController;
import panel.AffinityStatsPanelController;

public class AffinityMain extends CytoscapePlugin {

    private static boolean activated = false;
    private static boolean first_use = true;
    private AffinityMainPanel myAff;

    public AffinityMain() {
        //create a new action to respond to menu activation
        APPluginAction action = new APPluginAction();
        //set the preferred menu
        action.setPreferredMenu("Plugins");
        //and add it to the menus
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    private void createMyPanel() {
        AffinityStatsPanelController psc = new AffinityStatsPanelController();
        AffinityPanelController pc = new AffinityPanelController(psc);

        myAff = new AffinityMainPanel();

        JPanel chooseImplPanel = new AffinityChooseImplPanel(pc);
        JPanel connModePanel = new AffinityConnModePanel(pc);
        JPanel graphModePanel = new AffinityGraphModePanel(pc);
        AffinityButtonsPanel actionButtonsPanel = new AffinityButtonsPanel(pc);
        actionButtonsPanel.addChooseImplPanel(chooseImplPanel);
        actionButtonsPanel.addConnModePanel(connModePanel);
        //    actionButtonsPanel.addGraphModePanel(graphModePanel);

        JPanel afpanel = pc.createAffinityPanel();
        JPanel stats = psc.createAffinityStatsPanel();

        myAff.addPanel(afpanel);
        //      myAff.add(chooseImplPanel, 1);
        //      myAff.add(connModePanel, 2);
        myAff.addPanel(graphModePanel);
        myAff.addPanel(actionButtonsPanel);
        myAff.addPanel(stats);

    }

    public class APPluginAction extends CytoscapeAction {

        /**
         * The constructor sets the text that should appear on the menu item.
         */
        public APPluginAction() {
            super("APCluster");
        }

        private void activatePlugin() {
            CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

            leftPanel.add("APCluster", myAff);
            int index = leftPanel.indexOfComponent("APCluster");
            leftPanel.setSelectedIndex(index);
            //System.out.println("affinity propagation");
        }

        private void disactivatePlugin() {
            CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

            leftPanel.remove(myAff);
            //System.out.println("affinity propagation");
        }

        /**
         * This method is called when the user selects the menu item.
         */
        public void actionPerformed(ActionEvent ae) {
            //get the network object; this contains the graph
            if (activated == false) {
                activated = true;
                if (first_use == true) {
                    first_use = false;
                    createMyPanel();
                }
                activatePlugin();
            } else {
                activated = false;
                disactivatePlugin();
            }
        }
    }
}

