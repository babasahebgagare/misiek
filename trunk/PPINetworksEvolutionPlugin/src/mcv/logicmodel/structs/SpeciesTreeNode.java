package mcv.logicmodel.structs;

import java.util.Map;

public abstract class SpeciesTreeNode {

    private SpeciesTreeNodeContext context = null;
    private String ID;

    public SpeciesTreeNode(String NetworkID, SpeciesTreeNode ParentNetwork) {
        ID = NetworkID;
        context = new SpeciesTreeNodeContext(ParentNetwork, this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SpeciesTreeNodeContext getContext() {
        return context;
    }

    public void setContext(SpeciesTreeNodeContext context) {
        this.context = context;
    }

    public abstract void deleteAllInteractions();

    //public abstract void addInteraction(Interaction interaction);
    public abstract boolean containsProtein(String ProteinID);

    public abstract Protein addProtein(String ProteinID, Protein ParentProtein, Family Family);

    public abstract Protein addRootProtein(String ProteinID, Family Family);

    public abstract void deleteInteraction(String iD);

    public abstract Protein getProtein(String ProteinID);

    public abstract Map<String, Protein> getProteins();

    public abstract void setProteins(Map<String, Protein> proteins);

    public abstract int getInteractionsCount();

    //public abstract Map<String, Interaction> getInteractions();

    //public abstract void setInteractions(Map<String, Interaction> interactions);
}
