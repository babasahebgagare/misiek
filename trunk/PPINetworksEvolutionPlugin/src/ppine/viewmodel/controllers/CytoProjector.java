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

import ppine.logicmodel.controllers.ProjectorNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoPPINetworkProjection;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.utils.Messenger;
import ppine.visual.layout.Layouter;

public class CytoProjector {

    private static CytoPPINetworkProjection projectNetwork(Collection<CytoProtein> selectedProteins, CytoAbstractPPINetwork motherCytoNetwork, SpeciesTreeNode network) {
        SpeciesTreeNode motherNetwork = motherCytoNetwork.getNetwork();

        switch (network.getContext().getHierarchy().getNetworkPosition(motherNetwork)) {
            case ABOVE:
                CytoPPINetworkProjectionToDown down = ProjectorNetwork.projectProteinsToDownOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(down);
                Layouter.getInstance().projectionToDownLayout(down);
                break;
            case BELOW:
                CytoPPINetworkProjectionToUp up = ProjectorNetwork.projectProteinsToUpOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(up);
                Layouter.getInstance().projectionToUpLayout(up);
                break;
            case THIS_NETWORK:
                Messenger.message("You can't map proteins on the same network");
                break;
            case NEIGHBOUR:
                Messenger.message("You can't map proteins on neighbour network");
                break;
            default:
                Messenger.message("DEFAULT");
        }
        return null;
    }

    public static void projectSelected(Collection<CytoProtein> selectedProteins, Collection<SpeciesTreeNode> networks) {

        CytoAbstractPPINetwork motherCytoNetwork = selectedProteins.iterator().next().getCytoNetwork();

        for (SpeciesTreeNode network : networks) {
            projectNetwork(selectedProteins, motherCytoNetwork, network);


        }
    }

    public static Collection<CytoProtein> getSelectedProteins() {
        @SuppressWarnings("unchecked")
        Set<CyNode> cyNodes = Cytoscape.getCurrentNetwork().getSelectedNodes();
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        CytoAbstractPPINetwork currCytoNetwork = cdh.tryFindNetworkByCytoID(PPINetworkCytoID);
        Collection<CytoProtein> ret = new HashSet<CytoProtein>();

        if (currCytoNetwork != null) {
            for (CyNode node : cyNodes) {
                ret.add(currCytoNetwork.getCytoProtein(node.getIdentifier()));
            }
        }
        return ret;
    }
}
