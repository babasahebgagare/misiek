package structs.model;

import java.util.Collection;

public abstract class CytoAbstractPPINetwork extends CytoObject {

    private PPINetwork network;
    private String ID;

    public abstract Collection<CytoProtein> getCytoProteins();

    public abstract CytoProtein getCytoProtein(String ID);

    public abstract Collection<CytoInteraction> getCytoInteractions();

    public abstract CytoInteraction getCytoInteraction(String ID);

    public abstract void addCytoInteraction(CytoInteraction cytoInteraction);

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
