package viewmodel.controllers;

import envinterface.EnvEdge;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import envinterface.EnvNode;
import java.util.Collection;
import utils.IDCreator;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoInteraction;

public class CytoInteractionsConverter {

    public static void convertCytoNetworkInteractions(EnvNetwork network, Collection<CytoInteraction> cytoInteractions) {
        for (CytoInteraction cytoInteraction : cytoInteractions) {
            //     int rootID = Cytoscape.getRootGraph().createEdge(cytoInteraction.getSource().getIndex(), cytoInteraction.getTarget().getIndex());

            EnvNode source = EnvInterface.getInstance().getNode(cytoInteraction.getSource().getIndex());
            EnvNode target = EnvInterface.getInstance().getNode(cytoInteraction.getTarget().getIndex());
            EnvEdge edge = EnvInterface.getInstance().createEdge(network, IDCreator.createInteractionID(source.getID(), target.getID()), source, target);

            //     Edge edge = Cytoscape.getRootGraph().getEdge(rootID);
            //     edge.setIdentifier(cytoInteraction.getCytoID());
            cytoInteraction.setIndex(edge.getRootID());
            //    cyNetwork.addEdge(rootID);
            CytoDataHandle.addCytoInteractionMapping(edge.getRootID(), cytoInteraction);
        }
    }
}
