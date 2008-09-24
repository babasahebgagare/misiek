package structs;

public class Protein {

    private String ID;
    private String FamilyID;
    private ProteinContext context;

    public Protein(String ID, String NetworkID, String famID) {
        this.ID = ID;
        this.FamilyID = famID;
        this.context = new ProteinContext(NetworkID, null);
    }
    
    public Protein(String ID, String ParentProteinID, String NetworkID, String famID) {
        this.ID = ID;
        this.FamilyID = famID;
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

    public String getFamilyID() {
        return FamilyID;
    }

    public void setFamilyID(String FamilyID) {
        this.FamilyID = FamilyID;
    }
    
}
