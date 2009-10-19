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

package ppine.cytolisteners;

import cytoscape.Cytoscape;
import cytoscape.ding.DingNetworkView;
import cytoscape.view.CyNetworkView;
import giny.view.GraphViewChangeEvent;
import giny.view.GraphViewChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import ppine.main.PluginDataHandle;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;

public class CytoListeners {

    public static void createNetworkViewListener(CyNetworkView cyNetworkView) {

        cyNetworkView.addGraphViewChangeListener(new GraphViewChangeListener() {

            public void graphViewChanged(GraphViewChangeEvent event) {
                if (event.getType() == GraphViewChangeEvent.NODES_SELECTED_TYPE) {
//                    System.out.println("SELECTED");
                } else if (event.getType() == GraphViewChangeEvent.NODES_UNSELECTED_TYPE) {
//                    System.out.println("UNSELECTED");
                } else {
//                    System.out.println("EVENT");
                }
            }
        });
    }

    public static void createNetworksListener() {
        Cytoscape.getSwingPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String PropertyName = evt.getPropertyName();

                if (PropertyName.equals("NETWORK_VIEW_CREATED")) {
                //    MemoLogger.log("Network view created: " + evt.getNewValue().toString());
                //    CyNetworkView cyNetworkView = (CyNetworkView) evt.getNewValue();
                //     createNetworkViewListener(cyNetworkView);
                //        cyNetworkView.addNodeContextMenuListener((NodeContextMenuListener) new NodePopupMenuListener());
                } else if (PropertyName.equals("NETWORK_VIEW_DESTROYED")) {
                //    String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();

               //     MemoLogger.log("Network view destroyed: " + networkName);
                } else if (PropertyName.equals(Cytoscape.NETWORK_CREATED)) {
               //     MemoLogger.log("Network created: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_DESTROYED)) {
                    //  System.out.println(evt.getNewValue().toString());

                    //  String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();
                    String networkID = evt.getNewValue().toString();
                    //   System.out.println("DELETED: " + networkName + " " + networkID);
                    CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();
                    CytoAbstractPPINetwork netOrNull = cdh.tryFindNetworkByCytoID(networkID);
                    if (netOrNull != null) {
                        cdh.cytoNetworkViewDeleted(networkID, netOrNull.getID());
                    }
             //       MemoLogger.log("Network deleted: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_LOADED)) {
             //       MemoLogger.log("Network loaded: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_SAVED)) {
             //       MemoLogger.log("Network saved: " + evt.getNewValue().toString());
                }
            }
        });

    }

    public static void createListeners() {
        createNetworksListener();
    }
}
