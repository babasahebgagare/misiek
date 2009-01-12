package envinterface.abstractenv;

public class EnvEdge {

    private EnvNetwork network;
    private String ID;
    private Integer rootID;
    private EnvNode source;
    private EnvNode target;

    public EnvEdge(EnvNetwork network, String ID, Integer rootID, EnvNode source, EnvNode target) {
        this.ID = ID;
        this.rootID = rootID;
        this.source = source;
        this.target = target;
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

    public EnvNode getSource() {
        return source;
    }

    public void setSource(EnvNode source) {
        this.source = source;
    }

    public EnvNode getTarget() {
        return target;
    }

    public void setTarget(EnvNode target) {
        this.target = target;
    }

    public EnvNetwork getNetwork() {
        return network;
    }

    public void setNetwork(EnvNetwork network) {
        this.network = network;
    }
}
