package structs.model;

public class ProteinProjectionContext {

    private Protein motherProtein;

    public ProteinProjectionContext(Protein motherProtein) {
        this.motherProtein = motherProtein;
    }

    public Protein getMotherProtein() {
        return motherProtein;
    }

    public void setMotherProtein(Protein motherProtein) {
        this.motherProtein = motherProtein;
    }
}
