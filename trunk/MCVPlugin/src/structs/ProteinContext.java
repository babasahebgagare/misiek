package structs;

public class ProteinContext {

    private String ParentProteinID;
    private String NetworkID;

    ProteinContext(String NetworkID, String ParentProteinID) {
        this.NetworkID = NetworkID;
        this.ParentProteinID = ParentProteinID;
    }

    public String getNetworkID() {
        return NetworkID;
    }

    public void setNetworkID(String NetworkID) {
        this.NetworkID = NetworkID;
    }

    public String getParentProteinID() {
        return ParentProteinID;
    }

    public void setParentProteinID(String ParentProteinID) {
        this.ParentProteinID = ParentProteinID;
    }
}
