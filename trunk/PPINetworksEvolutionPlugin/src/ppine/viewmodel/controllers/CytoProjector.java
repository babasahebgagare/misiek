package ppine.viewmodel.controllers;

import ppine.logicmodel.controllers.ProjectorNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoPPINetworkProjection;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.utils.Messenger;
import ppine.visual.layout.Layouter;

public class CytoProjector {

    private static CytoPPINetworkProjection projectNetwork(Collection<CytoProtein> selectedProteins, CytoAbstractPPINetwork motherCytoNetwork, SpeciesTreeNode network) {
        SpeciesTreeNode motherNetwork = motherCytoNetwork.getNetwork();

        switch (network.getContext().getHierarchy().getNetworkPosition(motherNetwork)) {
            case ABOVE:
                CytoPPINetworkProjectionToDown down = ProjectorNetwork.projectProteinsToDownOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(down);
                Layouter.getInstance().projectionToDownLayout(down);
                break;
            case BELOW:
                CytoPPINetworkProjectionToUp up = ProjectorNetwork.projectProteinsToUpOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(up);
                Layouter.getInstance().projectionToUpLayout(up);
                break;
            case THIS_NETWORK:
                Messenger.message("YOU TRYING PROJECT ON THE SAME LEVEL");
                break;
            case NEIGHBOUR:
                Messenger.message("NEIGHBOUR");
                break;
            default:
                Messenger.message("DEFAULT");
        }
        return null;
    }

    public static void projectSelected(Collection<CytoProtein> selectedProteins, Collection<SpeciesTreeNode> networks) {

        CytoAbstractPPINetwork motherCytoNetwork = selectedProteins.iterator().next().getCytoNetwork();

        for (SpeciesTreeNode network : networks) {
            projectNetwork(selectedProteins, motherCytoNetwork, network);


        }
    }

    public static Collection<CytoProtein> getSelectedProteins() {
        @SuppressWarnings("unchecked")
        Set<CyNode> cyNodes = Cytoscape.getCurrentNetwork().getSelectedNodes();
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        CytoAbstractPPINetwork currCytoNetwork = cdh.tryFindNetworkByCytoID(PPINetworkCytoID);
        Collection<CytoProtein> ret = new HashSet<CytoProtein>();

        if (currCytoNetwork != null) {
            for (CyNode node : cyNodes) {
                ret.add(currCytoNetwork.getCytoProtein(node.getIdentifier()));
            }
        }
        return ret;
    }
}
