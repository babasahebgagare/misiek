package projector;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import main.DataHandle;
import structs.PPINetwork;
import structs.Protein;

public class ProjectorTest {

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

    public static void projectAllSelected() {
        Collection<Protein> selectedProteins = getSelectedProteins();

        for (PPINetwork network : DataHandle.getNetworks().values()) {
            projectProteinsOnNetwork(selectedProteins, network);
        }
    }

    private static void projectProteinsOnNetwork(Collection<Protein> selectedProteins, PPINetwork network) {

        CyNetwork newProjNet = Cytoscape.createNetwork("PROJ: " + network.getID(), true);

        for (Protein protein : selectedProteins) {
            newProjNet.addNode(Cytoscape.getCyNode(protein.getID()).getRootGraphIndex());
        }
    }
}
