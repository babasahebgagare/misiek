package mcv.viewmodel.structs;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class CytoExpInteraction extends CytoNetworkObject {

    private String expID;
    private CytoProtein source;
    private CytoProtein target;

    public CytoExpInteraction(String CytoID, String expID, CytoProtein source, CytoProtein target, CytoAbstractPPINetwork cytoNetwork) {
        this.setCytoID(CytoID);
        this.setCytoNetwork(cytoNetwork);
        this.expID = expID;
        this.source = source;
        this.target = target;
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }

    public CytoProtein getSource() {
        return source;
    }

    public void setSource(CytoProtein source) {
        this.source = source;
    }

    public CytoProtein getTarget() {
        return target;
    }

    public void setTarget(CytoProtein target) {
        this.target = target;
    }
}
