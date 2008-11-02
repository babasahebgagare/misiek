package structs.model;

import java.util.Collection;
import java.util.HashSet;
import projector.NetworksHierarchy;

public class PPINetworkContext {

    private PPINetwork ParentNetwork;
    private Collection<PPINetwork> ChildrenNetworks;
    private NetworksHierarchy hierarchy;

    public PPINetworkContext(PPINetwork ParentNetwork) {
        this.ParentNetwork = ParentNetwork;
        this.ChildrenNetworks = new HashSet<PPINetwork>();
        this.hierarchy = new NetworksHierarchy();
    }

    public void addChild(PPINetwork child) {
        ChildrenNetworks.add(child);
    }

    public PPINetwork getParentNetwork() {
        return ParentNetwork;
    }

    public void setParentNetwork(PPINetwork ParentNetwork) {
        this.ParentNetwork = ParentNetwork;
    }

    public Collection<PPINetwork> getChildrenNetworks() {
        return ChildrenNetworks;
    }

    public void setChildrenNetworks(Collection<PPINetwork> ChildrenNetworks) {
        this.ChildrenNetworks = ChildrenNetworks;
    }

    public NetworksHierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(NetworksHierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }
}
