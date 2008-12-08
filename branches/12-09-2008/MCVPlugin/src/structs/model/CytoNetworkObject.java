package structs.model;

public class CytoNetworkObject extends CytoObject {

    private CytoAbstractPPINetwork cytoNetowork;

    public CytoAbstractPPINetwork getCytoNetowork() {
        return cytoNetowork;
    }

    public void setCytoNetowork(CytoAbstractPPINetwork cytoNetowork) {
        this.cytoNetowork = cytoNetowork;
    }
}
