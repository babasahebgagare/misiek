package logicmodel.structs;

import viewmodel.structs.CytoNetworkObject;
import viewmodel.structs.CytoAbstractPPINetwork;

public class CytoProtein extends CytoNetworkObject {

    private Protein protein;

    public CytoProtein(String CytoID, Protein protein, CytoAbstractPPINetwork cytoNetwork) {
        this.setCytoID(CytoID);
        this.setCytoNetowork(cytoNetwork);
        this.protein = protein;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }
}
