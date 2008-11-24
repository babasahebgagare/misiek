package structs.model;

public class CytoInteraction extends CytoNetworkObject {

    private Interaction interaction;
    private CytoProtein source;
    private CytoProtein target;

    public CytoInteraction(String CytoID, Interaction interaction, CytoProtein source, CytoProtein target, CytoAbstractPPINetwork cytoNetwork) {
        this.setCytoID(CytoID);
        this.setCytoNetowork(cytoNetwork);
        this.interaction = interaction;
        this.source = source;
        this.target = target;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
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
