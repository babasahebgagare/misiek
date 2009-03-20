package viewmodel.controllers;

import io.AbstractDataReader;
import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import mappers.IDMapper;
import viewmodel.structs.CytoAbstractPPINetwork;
import viewmodel.structs.CytoGroupNode;
import viewmodel.structs.CytoInteraction;
import viewmodel.structs.CytoPPINetwork;
import viewmodel.structs.CytoProtein;
import viewmodel.structs.CytoProteinProjection;
import logicmodel.structs.PPINetwork;
import viewmodel.structs.CytoPPINetworkProjection;
import viewmodel.structs.CytoPPINetworkProjectionToDown;
import viewmodel.structs.CytoPPINetworkProjectionToUp;
import logicmodel.structs.Interaction;
import logicmodel.structs.Protein;
import utils.IDCreator;

public class CytoDataHandle {

    private IDMapper networkIDMapper = new IDMapper();
    private Map<Integer, CytoProtein> cytoProteins = new HashMap<Integer, CytoProtein>();
    private Map<Integer, CytoInteraction> cytoInteractions = new HashMap<Integer, CytoInteraction>();
    private Map<String, CytoPPINetworkProjection> projections = new HashMap<String, CytoPPINetworkProjection>();
    private Map<String, CytoPPINetwork> cytoNetworks = new HashMap<String, CytoPPINetwork>();

    public void deleteAllCytoInteractionsByNetwork(CytoAbstractPPINetwork cytoNetwork) {
        for (CytoInteraction interaction : cytoNetwork.getCytoInteractions()) {
            Integer cytoIndex = Integer.valueOf(interaction.getIndex());
            cytoInteractions.remove(cytoIndex);
        }
    }

    public void deleteAllCytoProteinsByNetwork(CytoAbstractPPINetwork cytoNetwork) {
        for (CytoProtein protein : cytoNetwork.getCytoProteins()) {
            Integer cytoIndex = Integer.valueOf(protein.getIndex());
            cytoInteractions.remove(cytoIndex);
        }
    }

    public void cytoNetworkViewDeleted(String networkID, String networkName) {
        System.out.println("DELETING --------------- " + networkID + " " + networkName);
        if (cytoNetworks.containsKey(networkName)) {
            System.out.println("YEAH cyto");
            CytoAbstractPPINetwork net = cytoNetworks.get(networkName);
            deleteAllCytoInteractionsByNetwork(net);
            deleteAllCytoProteinsByNetwork(net);
            cytoNetworks.remove(networkName);
        }
        if (projections.containsKey(networkName)) {
            CytoAbstractPPINetwork net = projections.get(networkName);
            deleteAllCytoInteractionsByNetwork(net);
            deleteAllCytoProteinsByNetwork(net);

            projections.remove(networkName);
        }
    }

    public Collection<CytoInteraction> getCytoInteractions() {
        return cytoInteractions.values();
    }

    public Set<CytoAbstractPPINetwork> getCytoPPINetworks() {

        Set<CytoAbstractPPINetwork> res = new HashSet<CytoAbstractPPINetwork>();
        for (CytoPPINetworkProjection proj : projections.values()) {
            res.add(proj);
        }

        for (CytoPPINetwork net : cytoNetworks.values()) {
            res.add(net);
        }

        return res;
    }

    public void addCytoInteractionMapping(int index, CytoInteraction object) {
        cytoInteractions.put(Integer.valueOf(index), object);
    }

    public void deleteCytoInteractionMapping(int index) {
        cytoInteractions.remove(Integer.valueOf(index));
    }

    public CytoInteraction getCytoInteractionByIndex(int index) {
        return cytoInteractions.get(Integer.valueOf(index));
    }

    public void addCytoProteinMapping(int index, CytoProtein object) {
        cytoProteins.put(Integer.valueOf(index), object);
    }

    public void deleteCytoProteinMapping(int index) {
        cytoProteins.remove(Integer.valueOf(index));
    }

    public CytoProtein tryGetCytoProteinByIndex(int index) {
        return cytoProteins.get(Integer.valueOf(index));
    }

    public void createCytoInteraction(String EdgeID, String SourceID, String TargetID, Double Probability, CytoAbstractPPINetwork cytoNetwork) {

        String SourceCytoID = IDCreator.createProteinProjectionID(SourceID, cytoNetwork);
        String TargetCytoID = IDCreator.createProteinProjectionID(TargetID, cytoNetwork);
        String EdgeCytoID = IDCreator.createInteractionProjectionID(EdgeID, cytoNetwork);

        CytoProtein source = cytoNetwork.getCytoProtein(SourceCytoID);
        CytoProtein target = cytoNetwork.getCytoProtein(TargetCytoID);

        CytoInteraction cytoInteraction = new CytoInteraction(EdgeCytoID, source, target, cytoNetwork, Probability, null);
        cytoNetwork.addCytoInteraction(cytoInteraction);
    }

    public void createCytoInteraction(Interaction interaction, CytoAbstractPPINetwork cytoNetwork) {

        String SourceCytoID = IDCreator.createProteinProjectionID(interaction.getSource().getID(), cytoNetwork);
        String TargetCytoID = IDCreator.createProteinProjectionID(interaction.getTarget().getID(), cytoNetwork);
        String EdgeCytoID = IDCreator.createInteractionProjectionID(interaction.getID(), cytoNetwork);

        CytoProtein source = cytoNetwork.getCytoProtein(SourceCytoID);
        CytoProtein target = cytoNetwork.getCytoProtein(TargetCytoID);

        if (source != null && target != null) {

            CytoInteraction cytoInteraction = new CytoInteraction(EdgeCytoID, source, target, cytoNetwork, interaction.getProbability(), interaction);
            cytoNetwork.addCytoInteraction(cytoInteraction);
        }
    }

    public void deleteCytoscapeInteractions(CytoAbstractPPINetwork cytoNetwork) {
        CyNetwork cyNetwork = Cytoscape.getNetwork(cytoNetwork.getCytoID());
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(cytoNetwork.getCytoID());

        for (CytoInteraction cytoInteraction : cytoNetwork.getCytoInteractions()) {
            CyEdge edge = Cytoscape.getRootGraph().getEdge(cytoInteraction.getCytoID());

            cyNetwork.removeEdge(edge.getRootGraphIndex(), true);
            cyNetworkView.removeEdgeView(edge);
        }

    }

    public void deleteCytoNetwork(String CytoID) {
        cytoNetworks.remove(CytoID);
        projections.remove(CytoID);
        networkIDMapper.deleteMapping(CytoID);
    }

    public void updateCytoInteractions(CytoAbstractPPINetwork cytoNetwork, double treshold) {
        deleteCytoscapeInteractions(cytoNetwork);
        cytoNetwork.deleteCytoInteractions();
        AbstractDataReader.getInstance().readInteractions(cytoNetwork, treshold);
    }

    public CytoGroupNode createCytoGroupNode(String groupNodeID, CytoProtein cytoProtein) {
        CytoGroupNode cytoGroupNode = new CytoGroupNode(groupNodeID, cytoProtein);
        return cytoGroupNode;
    }

    public CytoPPINetworkProjectionToDown createCytoProjectionToDown(String projectionID, CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network) {
        CytoPPINetworkProjectionToDown projection = new CytoPPINetworkProjectionToDown(cytoMotherNetwork, network, projectionID);
        projections.put(projectionID, projection);
        return projection;
    }

    public CytoPPINetworkProjectionToUp createCytoProjectionToUp(String projectionID, CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network) {
        CytoPPINetworkProjectionToUp projection = new CytoPPINetworkProjectionToUp(cytoMotherNetwork, network, projectionID);
        projections.put(projectionID, projection);
        return projection;
    }

    public CytoProteinProjection createCytoProteinProjection(String ProteinProjectionID, Protein proteinProject, CytoPPINetworkProjection projection, CytoProtein cytoMotherProtein) {
        CytoProteinProjection proteinProjection = new CytoProteinProjection(ProteinProjectionID, proteinProject, projection, cytoMotherProtein);
        projection.addCytoProtein(proteinProjection);
        return proteinProjection;
    }

    public CytoAbstractPPINetwork getCytoNetwork(String CytoID) {
        CytoAbstractPPINetwork cytoNetwork = cytoNetworks.get(CytoID);
        if (cytoNetwork == null) {
            cytoNetwork = projections.get(CytoID);
        }
        return cytoNetwork;
    }

    public IDMapper getNetworkIDMapper() {
        return networkIDMapper;
    }

    public void setNetworkIDMapper(IDMapper networkIDMapper) {
        this.networkIDMapper = networkIDMapper;
    }

    public CytoPPINetwork createCytoNetwork(String ID, PPINetwork network) {
        CytoPPINetwork cytoNetwork = new CytoPPINetwork(network, ID);
        cytoNetworks.put(ID, cytoNetwork);
        return cytoNetwork;
    }

    public Map<String, CytoPPINetworkProjection> getProjections() {
        return projections;
    }

    public void setProjections(Map<String, CytoPPINetworkProjection> projections) {
        this.projections = projections;
    }

    public CytoPPINetworkProjection tryFindNetworkProjectionByCytoID(String CytoID) {
        String CytoPPINetworkID = networkIDMapper.getIDByCytoID(CytoID);
        CytoPPINetworkProjection ret = projections.get(CytoPPINetworkID);

        return ret;
    }

    public CytoAbstractPPINetwork tryFindNetworkByCytoID(String CytoID) {
        String CytoPPINetworkID = networkIDMapper.getIDByCytoID(CytoID);

        CytoAbstractPPINetwork ret = cytoNetworks.get(CytoPPINetworkID);

        if (ret == null) {

            ret = projections.get(CytoPPINetworkID);
        }

        return ret;
    }

    public void addNetworkIDMapping(String CytoID, String ID) {
        networkIDMapper.addMapping(CytoID, ID);
    }
}
