package envinterface.netbeans;

import envinterface.EnvEdge;
import envinterface.EnvInterface;
import envinterface.EnvNetwork;
import envinterface.EnvNode;

public class NetbeansInterface extends EnvInterface {

    @Override
    public EnvNetwork createNetwork(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EnvNode createNode(EnvNetwork network, String ID) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
