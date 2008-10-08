package structs;

import java.util.HashMap;
import java.util.Map;

public class PPINetworkProjection {

    private Map<String, GroupNode> groupNodes = new HashMap<String, GroupNode>();
    private Map<String, ProteinProjection> proteinProjections = new HashMap<String, ProteinProjection>();
    private Map<String, Interaction> groupNodeInteractions = new HashMap<String, Interaction>();
    private Map<String, Interaction> proteinProjectionInteractions = new HashMap<String, Interaction>();
    private PPINetworkProjectionContext context;
    private String ID;
    private String CytoID;

    public PPINetworkProjection(String ID, PPINetwork motherNetwork) {
        this.ID = ID;
        this.context = new PPINetworkProjectionContext(motherNetwork);
    }

    public ProteinProjection getProteinProjection(String ID) {
        return proteinProjections.get(ID);
    }

    public GroupNode getGroupNode(String ID) {
        return groupNodes.get(ID);
    }

    public void addGroupNodeInteraction(Interaction interaction) {
        groupNodeInteractions.put(interaction.getID(), interaction);
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

    public PPINetworkProjectionContext getContext() {
        return context;
    }

    public void setContext(PPINetworkProjectionContext context) {
        this.context = context;
    }

    public Map<String, Interaction> getGroupNodeInteractions() {
        return groupNodeInteractions;
    }

    public void setGroupNodeInteractions(Map<String, Interaction> groupNodeInteractions) {
        this.groupNodeInteractions = groupNodeInteractions;
    }

    public Map<String, Interaction> getProteinProjectionInteractions() {
        return proteinProjectionInteractions;
    }

    public void setProteinProjectionInteractions(Map<String, Interaction> proteinProjectionInteractions) {
        this.proteinProjectionInteractions = proteinProjectionInteractions;
    }
}
