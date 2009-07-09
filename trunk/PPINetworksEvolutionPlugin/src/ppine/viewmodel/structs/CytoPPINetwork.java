package mcv.viewmodel.structs;

import mcv.logicmodel.structs.SpeciesTreeNode;

public class CytoPPINetwork extends CytoAbstractPPINetwork {

    public CytoPPINetwork(SpeciesTreeNode network, String ID) {
        super(network, ID);
    }

    @Override
    public CytoAbstractPPINetwork tryGetMother() {
        return null;
    }
}
