package envinterface.netbeans;

import envinterface.abstractenv.EnvEdge;
import envinterface.abstractenv.EnvEdgeView;
import envinterface.abstractenv.EnvNetworkView;
import org.netbeans.api.visual.widget.ConnectionWidget;

public class NetbeansEdgeView extends EnvEdgeView {

    private ConnectionWidget connectionWidget;

    public NetbeansEdgeView(EnvEdge edge, EnvNetworkView envNetworkView) {
        super(edge, envNetworkView);
    }

    public ConnectionWidget getConnectionWidget() {
        return connectionWidget;
    }

    public void setConnectionWidget(ConnectionWidget connectionWidget) {
        this.connectionWidget = connectionWidget;
    }
}
