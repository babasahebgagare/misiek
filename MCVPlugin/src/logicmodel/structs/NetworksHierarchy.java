package logicmodel.structs;

import java.util.Collection;
import java.util.HashSet;

public class NetworksHierarchy {

    private Collection<PPINetwork> networksAbove = new HashSet<PPINetwork>();
    private Collection<PPINetwork> networksBelow = new HashSet<PPINetwork>();
    private PPINetwork thisNetwork;

    public enum NetworkPosition {

        ABOVE, BELOW, NEIGHBOUR, THIS_NETWORK
    }

    public NetworksHierarchy(PPINetwork thisNetwork) {
        this.thisNetwork = thisNetwork;
    }

    public void addNetworkBelow(PPINetwork network) {
        networksBelow.add(network);


    }

    public void addNetworkAbove(PPINetwork network) {
        networksAbove.add(network);
    }

    public Collection<PPINetwork> getNetworksAbove() {
        return networksAbove;
    }

    public void setNetworksAbove(Collection<PPINetwork> networksAbove) {
        this.networksAbove = networksAbove;
    }

    public Collection<PPINetwork> getNetworksBelow() {
        return networksBelow;
    }

    public void setNetworksBelow(Collection<PPINetwork> networksBelow) {
        this.networksBelow = networksBelow;
    }

    public NetworkPosition getNetworkPosition(PPINetwork network) {
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
