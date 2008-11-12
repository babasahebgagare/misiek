package converter;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import main.DataHandle;
import structs.model.CytoPPINetworkProjection;

class ProjectionConverter {

    static void convertPPINetworkProjection(CytoPPINetworkProjection projection) {
     /*   if (Cytoscape.getNetwork(projection.getCytoID()) == Cytoscape.getNullNetwork()) {
            CyNetwork newNet = Cytoscape.createNetwork(projection.getID(), true);
            projection.setCytoID(newNet.getIdentifier());
            DataHandle.addProjectionIDMapping(newNet.getIdentifier(), projection.getID());

            ProteinProjectionsConverter.convertProteinProjections(newNet, projection.getProteinProjections().values());
            GroupNodesConverter.convertGroupNodes(newNet, projection.getGroupNodes().values());
            InteractionsConverter.convertNetworkInteractions(newNet, projection.getGroupNodeInteractions().values());
            InteractionsConverter.convertNetworkInteractions(newNet, projection.getProteinProjectionInteractions().values());
        }*/
    }
}
