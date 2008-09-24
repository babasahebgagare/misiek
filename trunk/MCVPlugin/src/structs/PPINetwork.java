package structs;

import java.util.HashMap;
import java.util.Map;

public class PPINetwork {

    private Map<String, Protein> proteins = new HashMap<String, Protein>();
    private Map<String, Interaction> interactions = new HashMap<String, Interaction>();
    private PPINetworkContext context = null;
    private String ID;
    private String CytoID;

    public PPINetwork(String NetworkID, String ParentNetworkID) {
        ID = NetworkID;
        context = new PPINetworkContext(ParentNetworkID);
    }

    public void addInteraction(String ID, String SourceID, String TargetID, Double Probability) {
        Interaction interaction = new Interaction(ID, SourceID, TargetID, Probability);

        interactions.put(ID, interaction);
    }

    public void addProtein(String ProteinID, String ParentProteinID, String FamilyID) {
        Protein protein = new Protein(ProteinID, ParentProteinID, ID, FamilyID);
        proteins.put(ProteinID, protein);
    }

    public void addRootProtein(String ProteinID, String FamilyID) {
        Protein protein = new Protein(ProteinID, ID, FamilyID);
        proteins.put(ProteinID, protein);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public PPINetworkContext getContext() {
        return context;
    }

    public void setContext(PPINetworkContext context) {
        this.context = context;
    }

    public Map<String, Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(Map<String, Interaction> interactions) {
        this.interactions = interactions;
    }

    public Map<String, Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Map<String, Protein> proteins) {
        this.proteins = proteins;
    }

    public String getCytoID() {
        return CytoID;
    }

    public void setCytoID(String CytoID) {
        this.CytoID = CytoID;
    }
}
