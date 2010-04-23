/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.controllers.interactions;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import ppine.logicmodel.structs.ExpInteraction;
import ppine.viewmodel.controllers.CytoInteractionsConverter;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.logicmodel.structs.Interaction;
import ppine.logicmodel.structs.PPINetwork;
import ppine.logicmodel.structs.PPINetworkExp;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoVisualHandle;

public class DefaultInteractionsManager extends InteractionsManager {

    @Override
    public void loadInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        SpeciesTreeNode network = cytoNetwork.getNetwork();
        //System.out.println(network.getInteractions().size());
        if (network instanceof PPINetwork) {

            PPINetwork networkPPI = (PPINetwork) network;

            for (Interaction interaction : networkPPI.getInteractions().values()) {
                cdh.createCytoInteraction(interaction, cytoNetwork);
            }
        } else if (network instanceof PPINetworkExp) {
            PPINetworkExp networkExp = (PPINetworkExp) network;
            for (ExpInteraction expInteraction : networkExp.getInteractions().values()) {
                cdh.createCytoExpInteraction(expInteraction, cytoNetwork);
            }
        }
    }

    @Override
    public void deleteViewInteracions(CytoAbstractPPINetwork cytoNetwork) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        cdh.deleteCytoscapeInteractions(cytoNetwork);
        cytoNetwork.deleteCytoInteractions();
    }

    @Override
    public void showInteractions(CytoAbstractPPINetwork cytoNetwork) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(cytoNetwork.getCytoID());
        CyNetwork cyNetwork = cyNetworkView.getNetwork();

        CytoInteractionsConverter.convertCytoNetworkInteractions(cyNetwork, cytoNetwork.getCytoInteractions(), cytoNetwork.getCytoExpInteractions());
        CytoVisualHandle.applyVisualStyleForNetwork(cyNetworkView);
    }

    @Override
    public void loadAndShowInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork) {
        deleteViewInteracions(cytoNetwork);
        loadInteractionsFromModel(cytoNetwork);
        showInteractions(cytoNetwork);
    }
}
