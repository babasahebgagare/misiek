package viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import envinterface.EnvNode;
import java.util.Collection;
import viewmodel.controllers.CytoDataHandle;
import logicmodel.structs.CytoProtein;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(EnvNetwork envNetwork, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {
            //  int rootID = Cytoscape.getRootGraph().createNode();
            EnvNode node = EnvInterface.getInstance().createNode(envNetwork, cytoProtein.getProtein().getID());
            //     Node node = Cytoscape.getRootGraph().getNode(rootID);
            //     node.setIdentifier(cytoProtein.getCytoID());
            //     cytoProtein.setIndex(rootID);
            //     newNet.addNode(rootID);
            CytoDataHandle.addCytoProteinMapping(node.getRootID().intValue(), cytoProtein);
        }
    }
}
