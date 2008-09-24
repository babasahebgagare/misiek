package structs;

public class PPINetworkContext {

    private String ParentNetworkID = null;

    PPINetworkContext(String ParentID) {
        ParentNetworkID = ParentID;
    }

    public String getParentNetworkID() {
        return ParentNetworkID;
    }

    public void setParentNetworkID(String ParentNetworkID) {
        this.ParentNetworkID = ParentNetworkID;
    }
}
