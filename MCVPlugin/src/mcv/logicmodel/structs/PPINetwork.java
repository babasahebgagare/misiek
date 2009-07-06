package mcv.logicmodel.structs;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PPINetwork extends SpeciesTreeNode {

    private Map<String, Protein> proteins = new HashMap<String, Protein>();
    private Map<String, Interaction> interactions = new HashMap<String, Interaction>();

    public PPINetwork(String NetworkID, SpeciesTreeNode ParentNetwork) {
        super(NetworkID, ParentNetwork);
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

    public Protein addProtein(String ProteinID, Protein ParentProtein, Family Family) {
        Protein protein = new Protein(ProteinID, ParentProtein, this, Family);
        proteins.put(ProteinID, protein);
        return protein;
    }

    public Protein addRootProtein(String ProteinID, Family Family) {
        Protein protein = new Protein(ProteinID, this, Family);
        proteins.put(ProteinID, protein);
        return protein;
    }

    public void deleteInteraction(String iD) {
        interactions.remove(iD);
    }

    public Protein getProtein(String ProteinID) {
        return proteins.get(ProteinID);
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

    @Override
    public int getInteractionsCount() {
        return interactions.size();
    }
}
