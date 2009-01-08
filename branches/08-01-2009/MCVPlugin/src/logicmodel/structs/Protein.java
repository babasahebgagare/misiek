package logicmodel.structs;

public class Protein {

    private String ID;
    private Family Family;
    private ProteinContext context;
    private ProjectorInfo projects;

    public Protein(String ID, PPINetwork Network, Family fam) {
        this.ID = ID;
        this.Family = fam;
        this.context = new ProteinContext(Network, null);
        this.projects = new ProjectorInfo();
    }

    public Protein(String ID, Protein ParentProtein, PPINetwork Network, Family fam) {
        this.ID = ID;
        this.Family = fam;
        this.context = new ProteinContext(Network, ParentProtein);
        this.projects = new ProjectorInfo();
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

    public ProjectorInfo getProjects() {
        return projects;
    }

    public void setProjects(ProjectorInfo projects) {
        this.projects = projects;
    }
}
