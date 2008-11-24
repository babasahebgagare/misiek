package structs.model;

public class CytoGroupNode {

    private String ID;
    private GroupNodeContext context;

    public CytoGroupNode(String ID, CytoProtein motherProtein) {
        this.ID = ID;
        this.context = new GroupNodeContext(motherProtein);
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

    public GroupNodeContext getContext() {
        return context;
    }

    public void setContext(GroupNodeContext context) {
        this.context = context;
    }
}
