package mcv.logicmodel.structs;

import java.util.Collection;
import java.util.HashSet;

public class NetworksHierarchy {

    private Collection<SpeciesTreeNode> networksAbove = new HashSet<SpeciesTreeNode>();
    private Collection<SpeciesTreeNode> networksBelow = new HashSet<SpeciesTreeNode>();
    private SpeciesTreeNode thisNetwork;

    public enum NetworkPosition {

        ABOVE, BELOW, NEIGHBOUR, THIS_NETWORK
    }

    public NetworksHierarchy(SpeciesTreeNode thisNetwork) {
        this.thisNetwork = thisNetwork;
    }

    public void addNetworkBelow(SpeciesTreeNode network) {
        networksBelow.add(network);


    }

    public void addNetworkAbove(SpeciesTreeNode network) {
        networksAbove.add(network);
    }

    public Collection<SpeciesTreeNode> getNetworksAbove() {
        return networksAbove;
    }

    public void setNetworksAbove(Collection<SpeciesTreeNode> networksAbove) {
        this.networksAbove = networksAbove;
    }

    public Collection<SpeciesTreeNode> getNetworksBelow() {
        return networksBelow;
    }

    public void setNetworksBelow(Collection<SpeciesTreeNode> networksBelow) {
        this.networksBelow = networksBelow;
    }

    public NetworkPosition getNetworkPosition(SpeciesTreeNode network) {
        if (networksAbove.contains(network)) {
            return NetworkPosition.ABOVE;
        } else if (networksBelow.contains(network)) {
            return NetworkPosition.BELOW;
        } else if (network == thisNetwork) {
            return NetworkPosition.THIS_NETWORK;
        } else {
            return NetworkPosition.NEIGHBOUR;
        }
    }
}
