package ppine.cytolisteners;

import cytoscape.Cytoscape;
import cytoscape.ding.DingNetworkView;
import cytoscape.view.CyNetworkView;
import ding.view.NodeContextMenuListener;
import giny.view.GraphViewChangeEvent;
import giny.view.GraphViewChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import ppine.main.PluginDataHandle;
import ppine.utils.MemoLogger;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.visual.menus.NodePopupMenuListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class CytoListeners {

    public static void createNetworkViewListener(CyNetworkView cyNetworkView) {

        cyNetworkView.addGraphViewChangeListener(new GraphViewChangeListener() {

            public void graphViewChanged(GraphViewChangeEvent event) {
                if (event.getType() == GraphViewChangeEvent.NODES_SELECTED_TYPE) {
                    System.out.println("SELECTED");
                } else if (event.getType() == GraphViewChangeEvent.NODES_UNSELECTED_TYPE) {
                    System.out.println("UNSELECTED");
                } else {
                    System.out.println("EVENT");
                }
            }
        });
    }

    public static void createNetworksListener() {
        Cytoscape.getSwingPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String PropertyName = evt.getPropertyName();

                if (PropertyName.equals("NETWORK_VIEW_CREATED")) {
                    MemoLogger.log("Network view created: " + evt.getNewValue().toString());
                //    CyNetworkView cyNetworkView = (CyNetworkView) evt.getNewValue();
               //     createNetworkViewListener(cyNetworkView);
            //        cyNetworkView.addNodeContextMenuListener((NodeContextMenuListener) new NodePopupMenuListener());
                } else if (PropertyName.equals("NETWORK_VIEW_DESTROYED")) {
                    String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();

                    MemoLogger.log("Network view destroyed: " + networkName);
                } else if (PropertyName.equals(Cytoscape.NETWORK_CREATED)) {
                    MemoLogger.log("Network created: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_DESTROYED)) {
                    System.out.println(evt.getNewValue().toString());

                    //  String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();
                    String networkID = evt.getNewValue().toString();
                    //   System.out.println("DELETED: " + networkName + " " + networkID);
                    CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();
                    CytoAbstractPPINetwork netOrNull = cdh.tryFindNetworkByCytoID(networkID);
                    if (netOrNull != null) {
                        cdh.cytoNetworkViewDeleted(networkID, netOrNull.getID());
                    }
                    MemoLogger.log("Network deleted: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_LOADED)) {
                    MemoLogger.log("Network loaded: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_SAVED)) {
                    MemoLogger.log("Network saved: " + evt.getNewValue().toString());
                }
            }
        });

    }

    public static void createListeners() {
        createNetworksListener();
    }
}
