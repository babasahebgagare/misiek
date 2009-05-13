package mcv.logicmodel.structs;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class PPINetwork {

    private Map<String, Protein> proteins = new HashMap<String, Protein>();
    private Map<String, Interaction> interactions = new HashMap<String, Interaction>();
    private PPINetworkContext context = null;
    private String ID;

    public PPINetwork(String NetworkID, PPINetwork ParentNetwork) {
        ID = NetworkID;
        context = new PPINetworkContext(ParentNetwork, this);
    }

    public void deleteAllInteractions() {
        interactions = new HashMap<String, Interaction>();
    }

    public void addInteraction(Interaction interaction) {
        interactions.put(interaction.getID(), interaction);
    }

    public boolean containsProtein(String ProteinID) {
        return proteins.containsKey(ProteinID);
    }

    public void addProtein(String ProteinID, Protein ParentProtein, Family Family) {
        Protein protein = new Protein(ProteinID, ParentProtein, this, Family);
        proteins.put(ProteinID, protein);
    }

    public void addRootProtein(String ProteinID, Family Family) {
        Protein protein = new Protein(ProteinID, this, Family);
        proteins.put(ProteinID, protein);
    }

    public void deleteInteraction(String iD) {
        interactions.remove(iD);
    }

    public Protein getProtein(String ProteinID) {
        return proteins.get(ProteinID);
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

    public Map<String, Protein> getProteins() {
        return proteins;
    }

    public void setProteins(Map<String, Protein> proteins) {
        this.proteins = proteins;
    }

    public Map<String, Interaction> getInteractions() {
        return interactions;
    }

    public void setInteractions(Map<String, Interaction> interactions) {
        this.interactions = interactions;
    }
}
