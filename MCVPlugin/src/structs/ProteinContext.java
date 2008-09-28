package structs;

public class ProteinContext {

    private PPINetwork Network;
    private Protein ParentProtein;

    public ProteinContext(PPINetwork Network, Protein ParentProtein) {
        this.Network = Network;
        this.ParentProtein = ParentProtein;
    }

    public PPINetwork getNetwork() {
        return Network;
    }

    public void setNetwork(PPINetwork Network) {
        this.Network = Network;
    }

    public Protein getParentProtein() {
        return ParentProtein;
    }

    public void setParentProtein(Protein ParentProtein) {
        this.ParentProtein = ParentProtein;
    }
}
