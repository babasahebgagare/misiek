package structs.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CytoPPINetworkProjection extends CytoAbstractPPINetwork {

    private Map<String, CytoProteinProjection> proteins = new HashMap<String, CytoProteinProjection>();
    private Map<String, CytoInteraction> interactions = new HashMap<String, CytoInteraction>();
    private CytoAbstractPPINetwork cytoMotherNetwork;

    public CytoPPINetworkProjection(CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network, String ID) {
        super(network, ID);
        this.cytoMotherNetwork = cytoMotherNetwork;
    }

    public void addCytoProtein(CytoProteinProjection proteinProjection) {
        proteins.put(proteinProjection.getCytoID(), proteinProjection);
    }

    public void addCytoInteraction(CytoInteraction cytoInteractionProjection) {
        interactions.put(cytoInteractionProjection.getCytoID(), cytoInteractionProjection);
    }

    public Collection<CytoProteinProjection> getCytoProteinsProjections() {
        return proteins.values();
    }

    @Override
    public Collection<CytoProtein> getCytoProteins() {
        Collection<CytoProtein> ret = new HashSet<CytoProtein>();
        for (CytoProteinProjection cytoProteinProjection : proteins.values()) {
            ret.add(cytoProteinProjection);
        }
        return ret;
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

    public CytoAbstractPPINetwork getCytoMotherNetwork() {
        return cytoMotherNetwork;
    }

    public void setCytoMotherNetwork(CytoAbstractPPINetwork cytoMotherNetwork) {
        this.cytoMotherNetwork = cytoMotherNetwork;
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
        interactions = new HashMap<String, CytoInteraction>();
    }
}
