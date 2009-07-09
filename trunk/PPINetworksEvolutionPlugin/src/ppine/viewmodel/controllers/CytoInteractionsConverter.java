package mcv.viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Edge;
import java.util.Collection;
import mcv.main.PluginDataHandle;
import mcv.viewmodel.structs.CytoExpInteraction;
import mcv.viewmodel.structs.CytoInteraction;

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
            if (cytoExpInteraction == null) {
                System.out.println("CYTO NULL");
            }
            if (cytoExpInteraction.getSource() == null) {
                System.out.println("CYTO SOURCE NULL");
            }

            if (cytoExpInteraction.getTarget() == null) {
                System.out.println("CYTO TARGET NULL");
            }
            int rootID = Cytoscape.getRootGraph().createEdge(cytoExpInteraction.getSource().getIndex(), cytoExpInteraction.getTarget().getIndex());

            Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            edge.setIdentifier(cytoExpInteraction.getCytoID());
            cytoExpInteraction.setIndex(rootID);
            cyNetwork.addEdge(rootID);
            cdh.addCytoExpInteractionMapping(rootID, cytoExpInteraction);
        }
    }
}
