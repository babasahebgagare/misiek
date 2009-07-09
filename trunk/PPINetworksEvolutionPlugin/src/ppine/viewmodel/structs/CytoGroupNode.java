package ppine.viewmodel.structs;

public class CytoGroupNode {

    private String ID;
    private CytoGroupNodeContext context;

    public CytoGroupNode(String ID, CytoProtein motherProtein) {
        this.ID = ID;
        this.context = new CytoGroupNodeContext(motherProtein);
    }

    public void addCytoProteinInside(CytoProteinProjection proteinProjection) {
        context.addProteinInside(proteinProjection);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public CytoGroupNodeContext getContext() {
        return context;
    }

    public void setContext(CytoGroupNodeContext context) {
        this.context = context;
    }
}
