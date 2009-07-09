package mcv.logicmodel.structs;

public class Interaction {

    Protein source;
    Protein target;
    Double probability;
    String ID;
    SpeciesTreeNode network;

    public Interaction(Protein source, Protein target, Double probability, String ID, SpeciesTreeNode network) {
        this.source = source;
        this.target = target;
        this.probability = probability;
        this.ID = ID;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode network) {
        this.network = network;
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
}
