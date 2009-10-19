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

package ppine.viewmodel.controllers;

import ppine.controllers.interactions.InteractionsManager;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;

public class CytoNetworkConverter {

    public static void convertCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        if (Cytoscape.getNetwork(cytoNetwork.getCytoID()) == Cytoscape.getNullNetwork()) {

            CyNetwork cyNetwork = createCytoscapeNetwork(cytoNetwork);
            cytoNetwork.setCytoID(cyNetwork.getIdentifier());
            cdh.addNetworkIDMapping(cyNetwork.getIdentifier(), cytoNetwork.getID());

            CytoProteinsConverter.convertCytoNetworkProteins(cyNetwork, cytoNetwork.getCytoProteins());
            //CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions());

            CyNetworkView cyNetworkView = Cytoscape.createNetworkView(cyNetwork);
            InteractionsManager.getInstance().loadAndShowInteractionsFromModel(cytoNetwork);

            CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
            CytoVisualHandle.applyCyLayoutAlgorithm(cyNetwork, cyNetworkView);
            CytoVisualHandle.setDefaultCenter(cyNetworkView);
        }
    }

    private static CyNetwork createCytoscapeNetwork(CytoAbstractPPINetwork cytoNetwork) {
        CytoAbstractPPINetwork motherOrNull = cytoNetwork.tryGetMother();
        if (motherOrNull != null) {
            String parentID = cytoNetwork.getNetwork().getID();
            
            CytoAbstractPPINetwork cytoParentOrNull = PluginDataHandle.getCytoDataHandle().getCytoNetwork(parentID);
            if (cytoParentOrNull != null) {
                CyNetwork parentNetwork = Cytoscape.getNetwork(cytoParentOrNull.getCytoID());
            
                return Cytoscape.createNetwork(cytoNetwork.getID(), parentNetwork, true);
            }
        }
        return Cytoscape.createNetwork(cytoNetwork.getID(), true);
    }
}
