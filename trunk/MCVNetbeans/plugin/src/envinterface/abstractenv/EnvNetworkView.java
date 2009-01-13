package envinterface.abstractenv;

import java.util.HashMap;
import java.util.Map;

public abstract class EnvNetworkView {

    private EnvNetwork envNetwork;
    private Map<String, EnvNodeView> nodeViews = new HashMap<String, EnvNodeView>();
    private Map<String, EnvEdgeView> edgeViews = new HashMap<String, EnvEdgeView>();

    public EnvNetworkView(EnvNetwork envNetwork) {
        this.envNetwork = envNetwork;
    }

    public EnvNetwork getEnvNetwork() {
        return envNetwork;
    }

    public void setEnvNetwork(EnvNetwork envNetwork) {
        this.envNetwork = envNetwork;
    }

    public Map<String, EnvEdgeView> getEdgeViews() {
        return edgeViews;
    }

    public void setEdgeViews(Map<String, EnvEdgeView> edgeViews) {
        this.edgeViews = edgeViews;
    }

    public Map<String, EnvNodeView> getNodeViews() {
        return nodeViews;
    }

    public void setNodeViews(Map<String, EnvNodeView> nodeViews) {
        this.nodeViews = nodeViews;
    }

    public abstract EnvNodeView createNodeView(EnvNode node);
}
