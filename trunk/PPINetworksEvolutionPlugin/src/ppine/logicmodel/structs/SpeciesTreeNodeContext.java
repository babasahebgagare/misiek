package mcv.logicmodel.structs;

import java.util.Collection;
import java.util.HashSet;

public class SpeciesTreeNodeContext {

    private SpeciesTreeNode parentNetwork;
    private Collection<SpeciesTreeNode> childrenNetworks;
    private NetworksHierarchy hierarchy;

    public SpeciesTreeNodeContext(SpeciesTreeNode ParentNetwork, SpeciesTreeNode thisNetwork) {
        this.parentNetwork = ParentNetwork;
        this.childrenNetworks = new HashSet<SpeciesTreeNode>();
        this.hierarchy = new NetworksHierarchy(thisNetwork);
    }

    public void addChild(SpeciesTreeNode child) {
        childrenNetworks.add(child);
    }

    public SpeciesTreeNode tryGetParentNetwork() {
        return parentNetwork;
    }

    public void setParentNetwork(SpeciesTreeNode ParentNetwork) {
        this.parentNetwork = ParentNetwork;
    }

    public Collection<SpeciesTreeNode> getChildrenNetworks() {
        return childrenNetworks;
    }

    public void setChildrenNetworks(Collection<SpeciesTreeNode> ChildrenNetworks) {
        this.childrenNetworks = ChildrenNetworks;
    }

    public NetworksHierarchy getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(NetworksHierarchy hierarchy) {
        this.hierarchy = hierarchy;
    }
}
