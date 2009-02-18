package envinterface.cytoscape;

import cytoscape.Cytoscape;
import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;
import giny.view.NodeView;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CytoscapeNodeView extends EnvNodeView {

    private NodeView cyNodeView = null;

    public CytoscapeNodeView(EnvNetworkView envNetworkView, EnvNode node) {
        super(envNetworkView, node);
    }
}
