package structs.model;

public class Interaction {

    private String ID;
    private Protein source;
    private Protein target;
    private Double probability;
    private PPINetwork network;

    public Interaction(String ID, Protein source, Protein target, Double Probability, PPINetwork network) {
        this.ID = ID;
        this.source = source;
        this.target = target;
        this.probability = Probability;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Protein getSource() {
        return source;
    }

    public void setSource(Protein source) {
        this.source = source;
    }

    public Protein getTarget() {
        return target;
    }

    public void setTarget(Protein target) {
        this.target = target;
    }

    public PPINetwork getNetwork() {
        return network;
    }

    public void setNetwork(PPINetwork network) {
        this.network = network;
    }
}
