package structs;

public class ProteinProjection {

    private String ID;
    private ProteinProjectionContext context;

    public ProteinProjection(String ID, Protein motherProtein) {
        this.ID = ID;
        this.context = new ProteinProjectionContext(motherProtein);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ProteinProjectionContext getContext() {
        return context;
    }

    public void setContext(ProteinProjectionContext context) {
        this.context = context;
    }
}
