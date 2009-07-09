package ppine.logicmodel.structs;

public class ProteinContext {

    private SpeciesTreeNode network;
    private Protein parentProtein;

    public ProteinContext(SpeciesTreeNode Network, Protein ParentProtein) {
        this.network = Network;
        this.parentProtein = ParentProtein;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode Network) {
        this.network = Network;
    }

    public Protein tryGetParentProtein() {
        return parentProtein;
    }

    public void setParentProtein(Protein ParentProtein) {
        this.parentProtein = ParentProtein;
    }
}
