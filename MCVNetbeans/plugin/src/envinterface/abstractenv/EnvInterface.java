package envinterface.abstractenv;

import envinterface.netbeans.NetbeansInterface;
import java.util.HashMap;

public abstract class EnvInterface {

    private static EnvInterface inter = new NetbeansInterface();

    public static EnvInterface getInstance() {
        return inter;
    }
    private HashMap<String, EnvNetworkView> networksView = new HashMap<String, EnvNetworkView>();
    private HashMap<String, EnvNetwork> networks = new HashMap<String, EnvNetwork>();
    private HashMap<Integer, EnvNode> nodes = new HashMap<Integer, EnvNode>();
    private HashMap<Integer, EnvEdge> edges = new HashMap<Integer, EnvEdge>();

    /**** ABSTRACT ***/
    public abstract EnvNetworkView createNetworkView(EnvNetwork envNetwork);

    public abstract EnvNetwork createNetwork(String title);

    public abstract EnvNetwork currentNetwork();

    public abstract EnvNode createNode(EnvNetwork network, String ID);

    public abstract EnvEdge createEdge(EnvNetwork network, String ID, EnvNode source, EnvNode target);

    public abstract void deleteNode(int index);

    public abstract void deleteEdge(int index);

    /**** implemented API  ***/
    public boolean existNetwork(String ID) {
        return networks.containsKey(ID);
    }

    public boolean existNetworkView(String ID) {
        return networksView.containsKey(ID);
    }

    public void deleteNode(Integer index) {
        deleteNode(index);
    }

    public EnvNode getNode(int index) {
        return getNode(new Integer(index));
    }

    public EnvEdge getEdge(int index) {
        return getEdge(new Integer(index));
    }

    public EnvNode getNode(Integer index) {
        return getNodes().get(index);
    }

    public EnvEdge getEdge(Integer index) {
        return getEdges().get(index);
    }

    public EnvNetwork getNetwork(String ID) {
        return networks.get(ID);
    }

    public EnvNetworkView getNetworkView(String ID) {
        return networksView.get(ID);
    }

    public HashMap<String, EnvNetwork> getNetworks() {
        return networks;
    }

    public void setNetworks(HashMap<String, EnvNetwork> networks) {
        this.networks = networks;
    }

    public HashMap<Integer, EnvEdge> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<Integer, EnvEdge> edges) {
        this.edges = edges;
    }

    public HashMap<Integer, EnvNode> getNodes() {
        return nodes;
    }

    public void setNodes(HashMap<Integer, EnvNode> nodes) {
        this.nodes = nodes;
    }

    public HashMap<String, EnvNetworkView> getNetworksView() {
        return networksView;
    }

    public void setNetworksView(HashMap<String, EnvNetworkView> networksView) {
        this.networksView = networksView;
    }
    
}
