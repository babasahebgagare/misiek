package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import structs.PPINetwork;
import utils.Messenger;

public class NetworkConverter {

    public static void convertPPINetwork(PPINetwork network) {
        
        if(Cytoscape.getNetwork(network.getID())==null) {
            Messenger.Message("null");
        }
        if(Cytoscape.getNetwork(network.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork newNet = Cytoscape.createNetwork(network.getID(), true);
            network.setCytoID(newNet.getIdentifier());
        }
        
    }
    
}
