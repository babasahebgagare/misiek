package projector;

import java.util.Collection;
import java.util.HashSet;
import structs.model.PPINetwork;

public class NetworksHierarchy {

    private Collection<PPINetwork> networksAbove = new HashSet<PPINetwork>();
    private Collection<PPINetwork> networksBelow = new HashSet<PPINetwork>();

    public enum NetworkPosition {

        ABOVE, BELOW, NEIGHBOUR
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
        } else {
            return NetworkPosition.NEIGHBOUR;
        }
    }
}
