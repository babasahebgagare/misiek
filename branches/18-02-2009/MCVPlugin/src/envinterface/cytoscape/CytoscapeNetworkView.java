package envinterface.cytoscape;

import cytoscape.view.CyNetworkView;
import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;

public class CytoscapeNetworkView extends EnvNetworkView {

    private CyNetworkView cyNetworkView;

    public CytoscapeNetworkView(EnvNetwork envNetwork, CyNetworkView cyNetworkView) {
        super(envNetwork);
        this.cyNetworkView = cyNetworkView;
    }

    @Override
    public EnvNodeView createNodeView(EnvNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
