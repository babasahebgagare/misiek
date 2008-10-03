package structs;

public class GroupNodeContext {

    private Protein motherProtein;

    public GroupNodeContext(Protein motherProtein) {
        this.motherProtein = motherProtein;
    }

    public Protein getMotherProtein() {
        return motherProtein;
    }

    public void setMotherProtein(Protein motherProtein) {
        this.motherProtein = motherProtein;
    }
}
