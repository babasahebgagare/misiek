package structs.model;

public class ProteinProjection extends Protein {

    private ProteinProjectionContext projectionContext;

    public ProteinProjection(String ID, Protein motherProtein) {
        super(ID, motherProtein.getContext().getNetwork(), motherProtein.getFamily());
        this.projectionContext = new ProteinProjectionContext(motherProtein);
    }

    public ProteinProjectionContext getProjectionContext() {
        return projectionContext;
    }

    public void setProjectionContext(ProteinProjectionContext projectionContext) {
        this.projectionContext = projectionContext;
    }
}
