package structs.model;

public class CytoInteraction extends CytoObject {

    private Interaction interaction;

    public CytoInteraction(String CytoID, Interaction interaction) {
        this.setCytoID(CytoID);
        this.interaction = interaction;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }
}
