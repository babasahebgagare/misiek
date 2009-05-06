package mcv.logicmodel.structs;

import java.util.Collection;
import java.util.HashSet;

public class PPINetworkContext {

    private PPINetwork parentNetwork;
    private Collection<PPINetwork> childrenNetworks;
    private NetworksHierarchy hierarchy;

    public PPINetworkContext(PPINetwork ParentNetwork, PPINetwork thisNetwork) {
        this.parentNetwork = ParentNetwork;
        this.childrenNetworks = new HashSet<PPINetwork>();
        this.hierarchy = new NetworksHierarchy(thisNetwork);
    }

    public void addChild(PPINetwork child) {
        childrenNetworks.add(child);
    }

    public PPINetwork tryGetParentNetwork() {
        return parentNetwork;
    }

    public void setParentNetwork(PPINetwork ParentNetwork) {
        this.parentNetwork = ParentNetwork;
    }

    public Collection<PPINetwork> getChildrenNetworks() {
        return childrenNetworks;
    }

    public void setChildrenNetworks(Collection<PPINetwork> ChildrenNetworks) {
        this.childrenNetworks = ChildrenNetworks;
    }

    public NetworksHierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(NetworksHierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }
}
