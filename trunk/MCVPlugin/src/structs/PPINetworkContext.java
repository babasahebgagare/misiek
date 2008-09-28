package structs;

public class PPINetworkContext {

    private PPINetwork ParentNetwork;

    public PPINetworkContext(PPINetwork ParentNetwork) {
        this.ParentNetwork = ParentNetwork;
    }

    public PPINetwork getParentNetwork() {
        return ParentNetwork;
    }

    public void setParentNetwork(PPINetwork ParentNetwork) {
        this.ParentNetwork = ParentNetwork;
    }
}
