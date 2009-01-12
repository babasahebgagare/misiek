package envinterface.abstractenv;

public class EnvNode {

    private EnvNetwork network;
    private String ID;
    private Integer rootID;

    public EnvNode(EnvNetwork network, String ID, Integer index) {
        this.ID = ID;
        this.rootID = index;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getRootID() {
        return rootID;
    }

    public void setRootID(Integer rootID) {
        this.rootID = rootID;
    }

    public EnvNetwork getNetwork() {
        return network;
    }

    public void setNetwork(EnvNetwork network) {
        this.network = network;
    }
}
