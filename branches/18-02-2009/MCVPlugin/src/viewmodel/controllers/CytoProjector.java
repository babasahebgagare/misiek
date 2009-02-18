package viewmodel.controllers;

import logicmodel.controllers.ProjectorNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoPPINetworkProjection;
import viewmodel.structs.CytoPPINetworkProjectionToDown;
import viewmodel.structs.CytoPPINetworkProjectionToUp;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.PPINetwork;
import utils.Messenger;
import visual.layout.Layouter;

public class CytoProjector {

    private static CytoPPINetworkProjection projectNetwork(Collection<CytoProtein> selectedProteins, CytoAbstractPPINetwork motherCytoNetwork, PPINetwork network) {
        PPINetwork motherNetwork = motherCytoNetwork.getNetwork();

        switch (network.getContext().getHierarchy().getNetworkPosition(motherNetwork)) {
            case ABOVE:
                CytoPPINetworkProjectionToDown down = ProjectorNetwork.projectProteinsToDownOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(down);
                //  Layouter.getInstance().ProjectionToDownLayout(down);
                break;
            case BELOW:
                CytoPPINetworkProjectionToUp up = ProjectorNetwork.projectProteinsToUpOnNetwork(selectedProteins, network, motherCytoNetwork);
                CytoNetworkConverter.convertCytoNetwork(up);
                //     Layouter.getInstance().ProjectionToUpLayout(up);
                break;
            case NEIGHBOUR:
                Messenger.Message("NEIGHBOUR");
                break;
            default:
                Messenger.Message("DEFAULT");
        }
        return null;
    }

    public static void projectSelected(Collection<CytoProtein> selectedProteins, Collection<PPINetwork> networks) {
        System.out.println("aaaa" + selectedProteins.size());
        Iterator<CytoProtein> it = selectedProteins.iterator();

        CytoAbstractPPINetwork motherCytoNetwork = it.next().getCytoNetowork();
        System.out.println("ddsdssd");
        for (PPINetwork network : networks) {
            projectNetwork(selectedProteins, motherCytoNetwork, network);


        }
    }

    public static Collection<CytoProtein> getSelectedProteins() {
        Set<CyNode> cyNodes = Cytoscape.getCurrentNetwork().getSelectedNodes();
        String PPINetworkCytoID = Cytoscape.getCurrentNetwork().getIdentifier();
        CytoAbstractPPINetwork currCytoNetwork = CytoDataHandle.findNetworkByCytoID(PPINetworkCytoID);
        Collection<CytoProtein> ret = new HashSet<CytoProtein>();

        if (currCytoNetwork != null) {
            for (CyNode node : cyNodes) {
                ret.add(currCytoNetwork.getCytoProtein(node.getIdentifier()));
            }
        }
        return ret;
    }
}
