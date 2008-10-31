package structs.model;

public class ProteinProjection {

    private Protein protein;
    private ProteinProjectionContext projectionContext;
    private String ID;

    public ProteinProjection(String ID, Protein protein, Protein motherProtein) {
        this.ID = ID;
        this.protein = protein;
        this.projectionContext = new ProteinProjectionContext(motherProtein);
    }

    public ProteinProjectionContext getProjectionContext() {
        return projectionContext;
    }

    public void setProjectionContext(ProteinProjectionContext projectionContext) {
        this.projectionContext = projectionContext;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }
}
