package mcv.viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Edge;
import java.util.Collection;
import mcv.main.PluginDataHandle;
import mcv.viewmodel.structs.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(CyNetwork cyNetwork, Collection<CytoInteraction> cytoInteractions) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        for (CytoInteraction cytoInteraction : cytoInteractions) {
            int rootID = Cytoscape.getRootGraph().createEdge(cytoInteraction.getSource().getIndex(), cytoInteraction.getTarget().getIndex());

            Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            edge.setIdentifier(cytoInteraction.getCytoID());
            cytoInteraction.setIndex(rootID);
            cyNetwork.addEdge(rootID);
            cdh.addCytoInteractionMapping(rootID, cytoInteraction);
        }
    }
}
