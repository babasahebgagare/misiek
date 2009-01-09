package envinterface;

public class EnvNetwork {

    private String ID;
    private String title;

    public EnvNetwork(String identifier, String title) {
        this.ID = identifier;
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
