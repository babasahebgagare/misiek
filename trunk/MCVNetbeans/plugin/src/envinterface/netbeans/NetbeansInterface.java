package envinterface.netbeans;

import envinterface.abstractenv.EnvEdge;
import envinterface.abstractenv.EnvInterface;
import envinterface.abstractenv.EnvNetwork;
import envinterface.abstractenv.EnvNetworkView;
import envinterface.abstractenv.EnvNode;
import envinterface.abstractenv.EnvNodeView;

public class NetbeansInterface extends EnvInterface {

    @Override
    public EnvNetwork createNetwork(String title) {
        NetbeansNetwork netbeansNetwork = new NetbeansNetwork(title, title);
        createNetworkView(netbeansNetwork);
        return netbeansNetwork;
    }

    @Override
    public EnvNode createNode(EnvNetwork network, String ID) {
        /*  WidgetAction moveAction = ActionFactory.createMoveAction();
        LabelWidget label1 = new LabelWidget(scene, ID);
        label1.setPreferredLocation(new Point(new Random().nextInt(100), 100));

        label1.getActions().addAction(moveAction);
        mainLayer.addChild(label1);
         */ EnvNode node = new EnvNode(network, ID, NetbeansRootGenerator.generate());
        EnvNetworkView envNetworkView = EnvInterface.getInstance().getNetworkView(node.getNetwork().getID());
        envNetworkView.createNodeView(node);

        return node;
    }

    @Override
    public EnvEdge createEdge(EnvNetwork network, String ID, EnvNode source, EnvNode target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteNode(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteEdge(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EnvNetwork currentNetwork() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EnvNetworkView createNetworkView(EnvNetwork envNetwork) {
        EnvNetworkView envNetworkView = new NetbeansNetworkView(envNetwork);
        getNetworksView().put(envNetwork.getID(), envNetworkView);
        return envNetworkView;
    }
}
