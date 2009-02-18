package envinterface.abstractenv;

public abstract class EnvNetworkView {

    private EnvNetwork envNetwork;

    public EnvNetworkView(EnvNetwork envNetwork) {
        this.envNetwork = envNetwork;
    }

    public EnvNetwork getEnvNetwork() {
        return envNetwork;
    }

    public void setEnvNetwork(EnvNetwork envNetwork) {
        this.envNetwork = envNetwork;
    }

    public abstract EnvNodeView createNodeView(EnvNode node);
}
