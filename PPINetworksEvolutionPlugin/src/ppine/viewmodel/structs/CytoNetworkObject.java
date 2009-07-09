package ppine.viewmodel.structs;

public class CytoNetworkObject extends CytoObject {

    private CytoAbstractPPINetwork cytoNetwork;
    private int index;

    public CytoAbstractPPINetwork getCytoNetwork() {
        return cytoNetwork;
    }

    public void setCytoNetwork(CytoAbstractPPINetwork cytoNetwork) {
        this.cytoNetwork = cytoNetwork;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
