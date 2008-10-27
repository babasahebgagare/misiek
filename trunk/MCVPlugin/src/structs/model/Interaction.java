package structs.model;

public class Interaction {

    private String ID;
    private String sourceID;
    private String targetID;
    private Double probability;

    public Interaction(String ID, String SourceID, String TargetID, Double Probability) {
        this.ID = ID;
        this.sourceID = SourceID;
        this.targetID = TargetID;
        this.probability = Probability;
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

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getTargetID() {
        return targetID;
    }

    public void setTargetID(String targetID) {
        this.targetID = targetID;
    }
}
