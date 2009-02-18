package viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Edge;
import java.util.Collection;
import viewmodel.structs.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(CyNetwork cyNetwork, Collection<CytoInteraction> cytoInteractions) {
        for (CytoInteraction cytoInteraction : cytoInteractions) {
            int rootID = Cytoscape.getRootGraph().createEdge(cytoInteraction.getSource().getIndex(), cytoInteraction.getTarget().getIndex());

            Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            edge.setIdentifier(cytoInteraction.getCytoID());
            cytoInteraction.setIndex(rootID);
            cyNetwork.addEdge(rootID);
            CytoDataHandle.addCytoInteractionMapping(rootID, cytoInteraction);
        }
    }
}
