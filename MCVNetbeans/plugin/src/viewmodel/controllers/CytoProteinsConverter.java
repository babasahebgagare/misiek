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

    static void convertCytoNetworkProteins(EnvNetwork newNet, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {
            EnvNode node = EnvInterface.getInstance().createNode(newNet, cytoProtein.getProtein().getID());
//            int rootID = Cytoscape.getRootGraph().createNode();
 //           Node node = Cytoscape.getRootGraph().getNode(rootID);
//            node.setIdentifier(cytoProtein.getCytoID());
            cytoProtein.setIndex(node.getIndex());
        //    newNet.addNode(rootID);
            CytoDataHandle.addCytoProteinMapping(node.getIndex(), cytoProtein);
        }
    }
}
