package mcv.viewmodel.structs;

import mcv.logicmodel.structs.PPINetwork;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CytoPPINetworkProjectionToDown extends CytoPPINetworkProjection {

    private Map<String, CytoGroupNode> cytoGroupNodes = new HashMap<String, CytoGroupNode>();

    public CytoPPINetworkProjectionToDown(CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network, String ID) {
        super(cytoMotherNetwork, network, ID);
    }

    public void addCytoGroupNode(CytoGroupNode node) {
        cytoGroupNodes.put(node.getID(), node);
    }

    public Collection<CytoGroupNode> getCytoGroupNodes() {
        return cytoGroupNodes.values();
    }

    public void setCytoGroupNodes(Map<String, CytoGroupNode> cytoGroupNodes) {
        this.cytoGroupNodes = cytoGroupNodes;
    }
}
