package mcv.viewmodel.structs;

import mcv.logicmodel.structs.Experiment;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class CytoExpInteraction extends CytoNetworkObject {

    private Experiment exp;
    private CytoProtein source;
    private CytoProtein target;

    public CytoExpInteraction(String CytoID, Experiment exp, CytoProtein source, CytoProtein target, CytoAbstractPPINetwork cytoNetwork) {
        this.setCytoID(CytoID);
        this.setCytoNetwork(cytoNetwork);
        this.exp = exp;
        this.source = source;
        this.target = target;
    }

    public Experiment getExp() {
        return exp;
    }

    public void setExp(Experiment exp) {
        this.exp = exp;
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
