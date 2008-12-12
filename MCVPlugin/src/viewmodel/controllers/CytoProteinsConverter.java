package viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Node;
import java.util.Collection;
import viewmodel.controllers.CytoDataHandle;
import logicmodel.structs.CytoProtein;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(CyNetwork newNet, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {
            int rootID = Cytoscape.getRootGraph().createNode();
            Node node = Cytoscape.getRootGraph().getNode(rootID);
            node.setIdentifier(cytoProtein.getCytoID());
            cytoProtein.setIndex(rootID);
            newNet.addNode(rootID);
            CytoDataHandle.addCytoProteinMapping(rootID, cytoProtein);
        }
    }
}
