/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package ppine.main;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.util.CytoscapeAction;
import java.awt.event.ActionEvent;

public class Main extends CytoscapePlugin {

    private static boolean activated = false;
    private static boolean first_use = true;

    public Main() {
        //create a new action to respond to menu activation
        NetworkEvolutionPluginAction action = new NetworkEvolutionPluginAction();
        //set the preferred menu
        action.setPreferredMenu("Plugins");
        //and add it to the menus
        Cytoscape.getDesktop().getCyMenus().addAction(action);
    }

    public class NetworkEvolutionPluginAction extends CytoscapeAction {

        /**
         * The constructor sets the text that should appear on the menu item.
         */
        public NetworkEvolutionPluginAction() {
            super("NetworkEvolution");

        }

        /*     @Override
        public void actionPerformed(ActionEvent ae) {
        PluginInitializator.initAll();

        }*/
        public void actionPerformed(ActionEvent ae) {
            //get the network object; this contains the graph
            if (activated == false) {
                activated = true;
                if (first_use == true) {
                    first_use = false;
                    PluginInitializator.initAll();
                }
                PluginInitializator.activatePlugin();
            } else {
                activated = false;
                PluginInitializator.disactivatePlugin();
            }
        }
    }
}

