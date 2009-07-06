package mcv.viewmodel.structs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mcv.logicmodel.structs.SpeciesTreeNode;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class CytoPPINetworkExperiments extends CytoAbstractPPINetwork {

    private Map<String, CytoProtein> proteins = new HashMap<String, CytoProtein>();
    private Map<String, CytoInteractionExp> interactions = new HashMap<String, CytoInteractionExp>();

    public CytoPPINetworkExperiments(SpeciesTreeNode network, String ID) {
        super(network, ID);
    }

    @Override
    public void deleteCytoInteractions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteCytoProtein(String ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteCytoInteraction(String ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<CytoProtein> getCytoProteins() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CytoProtein getCytoProtein(String ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<CytoInteraction> getCytoInteractions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CytoInteraction getCytoInteraction(String ID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addCytoInteraction(CytoInteraction cytoInteraction) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CytoAbstractPPINetwork tryGetMother() {
        
    }

    @Override
    public void addCytoProtein(CytoProtein cytoProtein) {
        this.proteins.put(cytoProtein.getCytoID(), cytoProtein);
    }
}
