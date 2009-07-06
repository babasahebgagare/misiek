package mcv.logicmodel.structs;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExpInteraction {

    String expID;
    Protein source;
    Protein target;
    String ID;
    SpeciesTreeNode network;

    public ExpInteraction(String expID, Protein source, Protein target, String ID, SpeciesTreeNode network) {
        this.expID = expID;
        this.source = source;
        this.target = target;
        this.ID = ID;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode network) {
        this.network = network;
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
