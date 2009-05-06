package mcv.logicmodel.structs;

public class ProteinContext {

    private PPINetwork network;
    private Protein parentProtein;

    public ProteinContext(PPINetwork Network, Protein ParentProtein) {
        this.network = Network;
        this.parentProtein = ParentProtein;
    }

    public PPINetwork getNetwork() {
        return network;
    }

    public void setNetwork(PPINetwork Network) {
        this.network = Network;
    }

    public Protein tryGetParentProtein() {
        return parentProtein;
    }

    public void setParentProtein(Protein ParentProtein) {
        this.parentProtein = ParentProtein;
    }
}
