package structs;

import java.util.HashMap;
import java.util.Map;

public class PPINetworkProjection {

    private Map<String, GroupNode> groupNodes = new HashMap<String, GroupNode>();
    private Map<String, ProteinProjection> proteinProjections = new HashMap<String, ProteinProjection>();
    private PPINetworkProjectionContext context;
    private String ID;
    private String CytoID;

    public PPINetworkProjection(String ID, PPINetwork motherNetwork) {
        this.ID = ID;
        this.context = new PPINetworkProjectionContext(motherNetwork);
    }

    public void addGroupNode(GroupNode node) {
        groupNodes.put(node.getID(), node);
    }

    public void addProteinProjection(ProteinProjection proteinProjection) {
        proteinProjections.put(proteinProjection.getID(), proteinProjection);
    }

    public String getCytoID() {
        return CytoID;
    }

    public void setCytoID(String CytoID) {
        this.CytoID = CytoID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Map<String, GroupNode> getGroupNodes() {
        return groupNodes;
    }

    public void setGroupNodes(Map<String, GroupNode> groupNodes) {
        this.groupNodes = groupNodes;
    }

    public Map<String, ProteinProjection> getProteinProjections() {
        return proteinProjections;
    }

    public void setProteinProjections(Map<String, ProteinProjection> proteinProjections) {
        this.proteinProjections = proteinProjections;
    }
}
