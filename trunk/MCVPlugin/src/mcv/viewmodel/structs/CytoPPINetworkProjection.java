package mcv.viewmodel.structs;

import mcv.logicmodel.structs.SpeciesTreeNode;

public class CytoPPINetworkProjection extends CytoAbstractPPINetwork {

    private CytoAbstractPPINetwork cytoMotherNetwork;

    public CytoPPINetworkProjection(CytoAbstractPPINetwork cytoMotherNetwork, SpeciesTreeNode network, String ID) {
        super(network, ID);
        this.cytoMotherNetwork = cytoMotherNetwork;
    }

    public CytoAbstractPPINetwork getCytoMotherNetwork() {
        return cytoMotherNetwork;
    }

    public void setCytoMotherNetwork(CytoAbstractPPINetwork cytoMotherNetwork) {
        this.cytoMotherNetwork = cytoMotherNetwork;
    }

    @Override
    public CytoAbstractPPINetwork tryGetMother() {
        return cytoMotherNetwork;
    }
}
