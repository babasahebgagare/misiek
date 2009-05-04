package viewmodel.structs;

import logicmodel.structs.PPINetwork;
import logicmodel.structs.Protein;
import java.util.Collection;
import utils.IDCreator;

public abstract class CytoAbstractPPINetwork extends CytoObject {

    private PPINetwork network;
    private String ID;

    public boolean containsCytoProtein(String SourceID) {
        Protein protein = this.network.getProtein(SourceID);

        if (protein == null) {
            return false;
        }

        String cytoProteinID = IDCreator.createProteinProjectionID(protein, this);

        return getCytoProtein(cytoProteinID) != null;
    }

    public abstract void deleteCytoInteractions();

    public abstract void deleteCytoProtein(String ID);

    public abstract void deleteCytoInteraction(String ID);

    public abstract Collection<CytoProtein> getCytoProteins();

    public abstract CytoProtein getCytoProtein(String ID);

    public abstract Collection<CytoInteraction> getCytoInteractions();

    public abstract CytoInteraction getCytoInteraction(String ID);

    public abstract void addCytoInteraction(CytoInteraction cytoInteraction);

    public abstract CytoAbstractPPINetwork tryGetMother();

    public CytoAbstractPPINetwork(PPINetwork network, String ID) {
        this.network = network;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public PPINetwork getNetwork() {
        return network;
    }

    public void setNetwork(PPINetwork network) {
        this.network = network;
    }
}
