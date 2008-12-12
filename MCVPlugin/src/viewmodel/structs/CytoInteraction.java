package viewmodel.structs;

import logicmodel.structs.Interaction;
import logicmodel.structs.CytoProtein;

public class CytoInteraction extends CytoNetworkObject {

    private Double probability;
    private CytoProtein source;
    private CytoProtein target;
    private Interaction interaction;

    public CytoInteraction(String CytoID, CytoProtein source, CytoProtein target, CytoAbstractPPINetwork cytoNetwork, Double probability, Interaction interaction) {
        this.setCytoID(CytoID);
        this.setCytoNetowork(cytoNetwork);
        this.source = source;
        this.target = target;
        this.probability = probability;
        this.interaction = interaction;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
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
