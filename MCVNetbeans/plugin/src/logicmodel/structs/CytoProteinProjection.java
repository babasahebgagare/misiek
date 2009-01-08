package logicmodel.structs;

import viewmodel.structs.CytoAbstractPPINetwork;

public class CytoProteinProjection extends CytoProtein {

    CytoProtein cytoMotherProtein;

    public CytoProteinProjection(String CytoID, Protein protein, CytoAbstractPPINetwork cytoNetwork, CytoProtein cytoMotherProtein) {
        super(CytoID, protein, cytoNetwork);
        this.cytoMotherProtein = cytoMotherProtein;
    }

    public CytoProtein getCytoMotherProtein() {
        return cytoMotherProtein;
    }

    public void setCytoMotherProtein(CytoProtein cytoMotherProtein) {
        this.cytoMotherProtein = cytoMotherProtein;
    }
}
