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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Edge;
import java.util.Collection;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.structs.CytoExpInteraction;
import ppine.viewmodel.structs.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(CyNetwork cyNetwork, Collection<CytoInteraction> cytoInteractions, Collection<CytoExpInteraction> cytoExpInteractions) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        for (CytoInteraction cytoInteraction : cytoInteractions) {
            int rootID = Cytoscape.getRootGraph().createEdge(cytoInteraction.getSource().getIndex(), cytoInteraction.getTarget().getIndex());

            Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            edge.setIdentifier(cytoInteraction.getCytoID());
            cytoInteraction.setIndex(rootID);
            cyNetwork.addEdge(rootID);
            cdh.addCytoInteractionMapping(rootID, cytoInteraction);
        }

        for (CytoExpInteraction cytoExpInteraction : cytoExpInteractions) {
            int rootID = Cytoscape.getRootGraph().createEdge(cytoExpInteraction.getSource().getIndex(), cytoExpInteraction.getTarget().getIndex());

            Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            edge.setIdentifier(cytoExpInteraction.getCytoID());
            cytoExpInteraction.setIndex(rootID);
            cyNetwork.addEdge(rootID);
            cdh.addCytoExpInteractionMapping(rootID, cytoExpInteraction);
        }
    }
}
