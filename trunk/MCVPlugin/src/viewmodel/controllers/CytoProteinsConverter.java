package viewmodel.controllers;

import envinterface.abstractenv.EnvInterface;
import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNode;
import java.util.Collection;
import logicmodel.structs.CytoProtein;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(EnvNetwork envNetwork, Collection<CytoProtein> cytoProteins) {
        for (CytoProtein cytoProtein : cytoProteins) {
            //  int rootID = Cytoscape.getRootGraph().createNode();
            EnvNode node = EnvInterface.getInstance().createNode(envNetwork, cytoProtein.getProtein().getID());
            cytoProtein.setIndex(node.getRootID());
            //     Node node = Cytoscape.getRootGraph().getNode(rootID);
            //     node.setIdentifier(cytoProtein.getCytoID());
            //     cytoProtein.setIndex(rootID);
            //     newNet.addNode(rootID);
            CytoDataHandle.addCytoProteinMapping(node.getRootID(), cytoProtein);
        }
    }
}
