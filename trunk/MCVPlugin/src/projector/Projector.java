package projector;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import main.DataHandle;
import structs.PPINetwork;
import structs.Protein;

public class Projector {

    public static void projectAllSelected(Collection<PPINetwork> networks) {
        Collection<Protein> selectedProteins = getSelectedProteins();
        PPINetwork motherNetwork = DataHandle.findNetworkByCytoID(Cytoscape.getCurrentNetwork().getIdentifier());

        for (PPINetwork network : networks) {
            ProjectorNetwork.projectProteinsOnNetwork(selectedProteins, network, motherNetwork);
        }
    }

    public static Collection<Protein> getSelectedProteins() {
        Set<CyNode> cyNodes = Cytoscape.getCurrentNetwork().getSelectedNodes();
        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        PPINetwork currNetwork = DataHandle.findNetworkByCytoID(PPINetworkCytoID);

        Collection<Protein> ret = new HashSet<Protein>();

        for (CyNode node : cyNodes) {
            ret.add(currNetwork.getProtein(node.getIdentifier()));
        }

        return ret;
    }
}
