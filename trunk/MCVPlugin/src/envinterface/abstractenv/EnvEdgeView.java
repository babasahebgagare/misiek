package envinterface.abstractenv;

public class EnvEdgeView {

    private EnvEdge edge;
    private EnvNetworkView envNetworkView;

    public EnvEdgeView(EnvEdge edge, EnvNetworkView envNetworkView) {
        this.edge = edge;
        this.envNetworkView = envNetworkView;
    }
}
