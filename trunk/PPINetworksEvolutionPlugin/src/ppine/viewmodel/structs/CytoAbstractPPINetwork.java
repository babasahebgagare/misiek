package ppine.viewmodel.structs;

import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ppine.utils.IDCreator;

public abstract class CytoAbstractPPINetwork extends CytoObject {

    private Map<String, CytoProtein> proteins = new HashMap<String, CytoProtein>();
    private Map<String, CytoInteraction> interactions = new HashMap<String, CytoInteraction>();
    private Map<String, CytoExpInteraction> expInteractions = new HashMap<String, CytoExpInteraction>();
    private SpeciesTreeNode network;
    private String ID;

    public boolean containsCytoProtein(String SourceID) {
        Protein protein = this.network.getProtein(SourceID);

        if (protein == null) {
            return false;
        }

        String cytoProteinID = IDCreator.createProteinProjectionID(protein, this);

        return getCytoProtein(cytoProteinID) != null;
    }

    public void deleteCytoExpInteraction(String ID) {
        expInteractions.remove(ID);
    }

    public void deleteCytoExpInteractions() {
        expInteractions = new HashMap<String, CytoExpInteraction>();
    }

    public void addCytoExpInteraction(CytoExpInteraction cytoExpInteraction) {
        expInteractions.put(cytoExpInteraction.getCytoID(), cytoExpInteraction);
    }

    public Collection<CytoExpInteraction> getCytoExpInteractions() {
        return expInteractions.values();
    }

    public CytoExpInteraction getCytoExpInteraction(String ID) {
        return expInteractions.get(ID);
    }

    public void deleteCytoInteraction(String ID) {
        interactions.remove(ID);
    }

    public void deleteCytoInteractions() {
        interactions = new HashMap<String, CytoInteraction>();
    }

    public void addCytoInteraction(CytoInteraction cytoInteraction) {
        interactions.put(cytoInteraction.getCytoID(), cytoInteraction);
    }

    public Collection<CytoInteraction> getCytoInteractions() {
        return interactions.values();
    }

    public CytoInteraction getCytoInteraction(String ID) {
        return interactions.get(ID);
    }

    public void addCytoProtein(CytoProtein cytoProtein) {
        proteins.put(cytoProtein.getCytoID(), cytoProtein);
    }

    public Collection<CytoProtein> getCytoProteins() {
        return proteins.values();
    }

    public CytoProtein getCytoProtein(String ID) {
        return proteins.get(ID);
    }

    public void deleteCytoProtein(String ID) {
        proteins.remove(ID);
    }

    public abstract CytoAbstractPPINetwork tryGetMother();

    public CytoAbstractPPINetwork(SpeciesTreeNode network, String ID) {
        this.network = network;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode network) {
        this.network = network;
    }
}
