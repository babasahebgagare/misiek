package ppine.logicmodel.structs;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExpInteraction {

    private Experiment exp;
    private Protein source;
    private Protein target;
    private String ID;
    private SpeciesTreeNode network;

    public ExpInteraction(Experiment exp, Protein source, Protein target, String ID, SpeciesTreeNode network) {
        this.exp = exp;
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

    public Experiment getExperiment() {
        return exp;
    }

    public void setExperiment(Experiment exp) {
        this.exp = exp;
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
