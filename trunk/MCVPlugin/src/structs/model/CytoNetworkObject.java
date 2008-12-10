package structs.model;

public class CytoNetworkObject extends CytoObject {

    private CytoAbstractPPINetwork cytoNetowork;
    private int index;

    public CytoAbstractPPINetwork getCytoNetowork() {
        return cytoNetowork;
    }

    public void setCytoNetowork(CytoAbstractPPINetwork cytoNetowork) {
        this.cytoNetowork = cytoNetowork;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
