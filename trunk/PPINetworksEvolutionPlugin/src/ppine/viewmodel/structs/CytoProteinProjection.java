package ppine.viewmodel.structs;

import ppine.logicmodel.structs.Protein;

public class CytoProteinProjection extends CytoProtein {

    private CytoProtein cytoMotherProtein;

    public CytoProteinProjection(String CytoID, Protein protein, CytoAbstractPPINetwork cytoNetwork, CytoProtein cytoMotherProtein) {
        super(CytoID, protein, cytoNetwork);
        this.cytoMotherProtein = cytoMotherProtein;
    }

    public CytoProtein tryGetCytoMotherProtein() {
        return cytoMotherProtein;
    }

    public void setCytoMotherProtein(CytoProtein cytoMotherProtein) {
        this.cytoMotherProtein = cytoMotherProtein;
    }
}
