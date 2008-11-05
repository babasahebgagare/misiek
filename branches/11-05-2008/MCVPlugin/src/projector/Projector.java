package projector;

import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import main.DataHandle;
import structs.model.PPINetwork;
import structs.model.PPINetworkProjection;
import structs.model.Protein;
import utils.Messenger;

public class Projector {

    public static void projectAllSelected(Collection<PPINetwork> networks) {
        Collection<Protein> selectedProteins = getSelectedProteins();
        PPINetwork motherNetwork = selectedProteins.iterator().next().getContext().getNetwork();

        for (PPINetwork network : networks) {
            switch (network.getContext().getHierarchy().getNetworkPosition(motherNetwork)) {
                case ABOVE:
                    ProjectorNetwork.projectProteinsToDownOnNetwork(selectedProteins, network, motherNetwork);
                    break;
                case BELOW:
                    ProjectorNetwork.projectProteinsToUpOnNetwork(selectedProteins, network, motherNetwork);
                    break;
                case NEIGHBOUR:
                    Messenger.Message("NEIGHBOUR");
                    break;
                default:
                    Messenger.Message("DEFAULT");
            }
        }
    }

    public static Collection<Protein> getSelectedProteins() {
        Set<CyNode> cyNodes = Cytoscape.getCurrentNetwork().getSelectedNodes();
        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        PPINetwork currNetwork = DataHandle.findNetworkByCytoID(PPINetworkCytoID);
        Collection<Protein> ret = new HashSet<Protein>();

        if (currNetwork != null) {

            for (CyNode node : cyNodes) {
                ret.add(currNetwork.getProtein(node.getIdentifier()));
            }
        }
        PPINetworkProjection currNetworkProj = DataHandle.findProjectionByCytoID(PPINetworkCytoID);
        if (currNetworkProj != null) {
            for (CyNode node : cyNodes) {
                Messenger.Message(node.getIdentifier());
                Protein protein = currNetworkProj.getProteinProjection(node.getIdentifier()).getProtein();
                if (protein != null) {
                    ret.add(protein);
                }
            }
        }

        return ret;
    }
}
