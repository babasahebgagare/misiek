package viewmodel.controllers;

import envinterface.abstractenv.EnvEdge;
import envinterface.abstractenv.EnvInterface;
import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNode;
import java.util.Collection;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(EnvNetwork envNetwork, Collection<CytoInteraction> cytoInteractions) {
        for (CytoInteraction cytoInteraction : cytoInteractions) {
            //int rootID = Cytoscape.getRootGraph().createEdge(cytoInteraction.getSource().getIndex(), cytoInteraction.getTarget().getIndex());

            EnvNode source = EnvInterface.getInstance().getNode(cytoInteraction.getSource().getIndex());
            EnvNode target = EnvInterface.getInstance().getNode(cytoInteraction.getTarget().getIndex());
            EnvEdge edge = EnvInterface.getInstance().createEdge(envNetwork, cytoInteraction.getCytoID(), source, target);

            //  Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            //  edge.setIdentifier(cytoInteraction.getCytoID());
            cytoInteraction.setIndex(edge.getRootID());
            //cyNetwork.addEdge(rootID);
            CytoDataHandle.addCytoInteractionMapping(edge.getRootID(), cytoInteraction);
        }
    }
}
