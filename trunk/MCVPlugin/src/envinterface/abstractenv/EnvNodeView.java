package envinterface.abstractenv;

public abstract class EnvNodeView {

    private int XPosition;
    private int YPosition;
    private EnvNetworkView envNetworkView;
    private EnvNode node;

    public EnvNodeView(EnvNetworkView envNetworkView, EnvNode node) {
        this.envNetworkView = envNetworkView;
        this.node = node;
    }

    public int getXPosition() {
        return XPosition;
    }

    public void setXPosition(int XPosition) {
        this.XPosition = XPosition;
    }

    public int getYPosition() {
        return YPosition;
    }

    public void setYPosition(int YPosition) {
        this.YPosition = YPosition;
    }
}
