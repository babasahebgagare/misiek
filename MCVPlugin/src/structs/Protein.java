package structs;

public class Protein {

    private String ID;
    private Family Family;
    private ProteinContext context;

    public Protein(String ID, String NetworkID, Family fam) {
        this.ID = ID;
        this.Family = fam;
        this.context = new ProteinContext(NetworkID, null);
    }

    public Protein(String ID, String ParentProteinID, String NetworkID, Family fam) {
        this.ID = ID;
        this.Family = fam;
        this.context = new ProteinContext(NetworkID, ParentProteinID);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public ProteinContext getContext() {
        return context;
    }

    public void setContext(ProteinContext context) {
        this.context = context;
    }

    public Family getFamily() {
        return this.Family;
    }

    public void setFamily(Family Family) {
        this.Family = Family;
    }
}
