package mcv.viewmodel.structs;

import mcv.logicmodel.structs.PPINetwork;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

public class CytoPPINetwork extends CytoAbstractPPINetwork {

    private Map<String, CytoProtein> proteins = new TreeMap<String, CytoProtein>();
    private Map<String, CytoInteraction> interactions = new TreeMap<String, CytoInteraction>();

    public CytoPPINetwork(PPINetwork network, String ID) {
        super(network, ID);
    }

    public void addCytoInteraction(CytoInteraction cytoInteraction) {
        interactions.put(cytoInteraction.getCytoID(), cytoInteraction);
    }

    public void addCytoProtein(CytoProtein cytoProtein) {
        proteins.put(cytoProtein.getCytoID(), cytoProtein);
    }

    @Override
    public Collection<CytoProtein> getCytoProteins() {
        return proteins.values();
    }

    @Override
    public CytoProtein getCytoProtein(String ID) {
        return proteins.get(ID);
    }

    @Override
    public Collection<CytoInteraction> getCytoInteractions() {
        return interactions.values();
    }

    @Override
    public CytoInteraction getCytoInteraction(String ID) {
        return interactions.get(ID);
    }

    @Override
    public void deleteCytoProtein(String ID) {
        proteins.remove(ID);
    }

    @Override
    public void deleteCytoInteraction(String ID) {
        interactions.remove(ID);
    }

    @Override
    public void deleteCytoInteractions() {
        interactions = new TreeMap<String, CytoInteraction>();
    }

    @Override
    public CytoAbstractPPINetwork tryGetMother() {
        return null;
    }
}
