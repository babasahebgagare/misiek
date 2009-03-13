package viewmodel.controllers;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import giny.model.Node;
import java.util.Collection;
import logicmodel.structs.CytoProtein;
import main.PluginDataHandle;

class CytoProteinsConverter {

    static void convertCytoNetworkProteins(CyNetwork newNet, Collection<CytoProtein> cytoProteins) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        for (CytoProtein cytoProtein : cytoProteins) {
            int rootID = Cytoscape.getRootGraph().createNode();
            Node node = Cytoscape.getRootGraph().getNode(rootID);
            node.setIdentifier(cytoProtein.getCytoID());
            cytoProtein.setIndex(rootID);
            newNet.addNode(rootID);
            cdh.addCytoProteinMapping(rootID, cytoProtein);
        }
    }
}
