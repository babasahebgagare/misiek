package envinterface;

public class EnvNode {

    private EnvNetwork network;
    private String ID;
    private Integer index;

    public EnvNode(EnvNetwork network, String ID, Integer index) {
        this.ID = ID;
        this.index = index;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public EnvNetwork getNetwork() {
        return network;
    }

    public void setNetwork(EnvNetwork network) {
        this.network = network;
    }
}
