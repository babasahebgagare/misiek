package mcv.logicmodel.structs;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PPINetworkExp extends SpeciesTreeNode {

    private Map<String, Protein> proteins = new HashMap<String, Protein>();
    private Map<String, ExpInteraction> interactions = new HashMap<String, ExpInteraction>();

    public PPINetworkExp(String NetworkID, SpeciesTreeNode ParentNetwork) {
        super(NetworkID, ParentNetwork);
    }

    @Override
    public void deleteAllInteractions() {
        interactions = new HashMap<String, ExpInteraction>();
    }

    public void addInteraction(ExpInteraction expInteraction) {
        interactions.put(expInteraction.ID, expInteraction);
    }

    @Override
    public boolean containsProtein(String proteinID) {
        return proteins.containsKey(proteinID);
    }

    @Override
    public Protein addProtein(String proteinID, Protein parentProtein, Family family) {
        Protein protein = new Protein(proteinID, parentProtein, this, family);
        proteins.put(proteinID, protein);
        return protein;
    }

    @Override
    public Protein addRootProtein(String proteinID, Family family) {
        Protein protein = new Protein(proteinID, this, family);
        proteins.put(proteinID, protein);
        return protein;
    }

    @Override
    public void deleteInteraction(String iD) {
        interactions.remove(iD);
    }

    @Override
    public Protein getProtein(String proteinID) {
        return proteins.get(proteinID);
    }

    @Override
    public Map<String, Protein> getProteins() {
        return proteins;
    }

    @Override
    public void setProteins(Map<String, Protein> proteins) {
        this.proteins = proteins;
    }

    public Map<String, ExpInteraction> getInteractions() {
        return interactions;
    }

    public void setInteractions(Map<String, ExpInteraction> interactions) {
        this.interactions = interactions;
    }

    @Override
    public int getInteractionsCount() {
        return interactions.size();
    }
}
