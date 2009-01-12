package envinterface.abstractenv;

public abstract class EnvNetwork {

    private String ID;
    private String title;

    public EnvNetwork(String ID, String title) {
        this.ID = ID;
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
