package main;

import io.AbstractDataReader;
import cytoscape.CyEdge;
import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import java.util.HashMap;
import java.util.Map;
import mappers.IDMapper;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoGroupNode;
import structs.model.CytoInteraction;
import structs.model.CytoObject;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;
import structs.model.PPINetwork;
import structs.model.CytoPPINetworkProjection;
import structs.model.CytoPPINetworkProjectionToDown;
import structs.model.CytoPPINetworkProjectionToUp;
import structs.model.Interaction;
import structs.model.Protein;
import utils.IDCreator;

public class CytoDataHandle {

    private static IDMapper networkIDMapper = new IDMapper();
    private static Map<Integer, CytoProtein> cytoProteins = new HashMap<Integer, CytoProtein>();
    private static Map<Integer, CytoInteraction> cytoInteractions = new HashMap<Integer, CytoInteraction>();
    private static Map<String, CytoPPINetworkProjection> projections = new HashMap<String, CytoPPINetworkProjection>();
    private static Map<String, CytoPPINetwork> cytoNetworks = new HashMap<String, CytoPPINetwork>();

    public static void addCytoInteractionMapping(int index, CytoInteraction object) {
        cytoInteractions.put(new Integer(index), object);
    }

    public static void deleteCytoInteractionMapping(int index) {
        cytoInteractions.remove(new Integer(index));
    }

    public static CytoInteraction getCytoInteractionByIndex(int index) {
        return cytoInteractions.get(new Integer(index));
    }

    public static void addCytoProteinMapping(int index, CytoProtein object) {
        cytoProteins.put(new Integer(index), object);
    }

    public static void deleteCytoProteinMapping(int index) {
        cytoProteins.remove(new Integer(index));
    }

    public static CytoProtein getCytoProteinByIndex(int index) {
        return cytoProteins.get(new Integer(index));
    }

    public static void createCytoInteraction(String EdgeID, String SourceID, String TargetID, Double Probability, CytoAbstractPPINetwork cytoNetwork) {

        String SourceCytoID = IDCreator.createProteinProjectionID(SourceID, cytoNetwork);
        String TargetCytoID = IDCreator.createProteinProjectionID(TargetID, cytoNetwork);
        String EdgeCytoID = IDCreator.createInteractionProjectionID(EdgeID, cytoNetwork);

        CytoProtein source = cytoNetwork.getCytoProtein(SourceCytoID);
        CytoProtein target = cytoNetwork.getCytoProtein(TargetCytoID);

        CytoInteraction cytoInteraction = new CytoInteraction(EdgeCytoID, source, target, cytoNetwork, Probability, null);
        cytoNetwork.addCytoInteraction(cytoInteraction);
    }

    public static void createCytoInteraction(Interaction interaction, CytoAbstractPPINetwork cytoNetwork) {

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

    public static void deleteCytoscapeInteractions(CytoAbstractPPINetwork cytoNetwork) {
        CyNetwork cyNetwork = Cytoscape.getNetwork(cytoNetwork.getCytoID());
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(cytoNetwork.getCytoID());

        for (CytoInteraction cytoInteraction : cytoNetwork.getCytoInteractions()) {
            CyEdge edge = Cytoscape.getRootGraph().getEdge(cytoInteraction.getCytoID());

            cyNetwork.removeEdge(edge.getRootGraphIndex(), true);
            cyNetworkView.removeEdgeView(edge);
        }

    }

    public static void deleteCytoNetwork(String CytoID) {
        cytoNetworks.remove(CytoID);
        projections.remove(CytoID);
        networkIDMapper.deleteMapping(CytoID);
    }

    public static void updateCytoInteractions(CytoAbstractPPINetwork cytoNetwork, double treshold) {
        deleteCytoscapeInteractions(cytoNetwork);
        cytoNetwork.deleteCytoInteractions();
        AbstractDataReader.getInstance().readInteractions(cytoNetwork, treshold);
    }

    public static CytoGroupNode createCytoGroupNode(String groupNodeID, CytoProtein cytoProtein) {
        CytoGroupNode cytoGroupNode = new CytoGroupNode(groupNodeID, cytoProtein);
        return cytoGroupNode;
    }

    public static CytoPPINetworkProjectionToDown createCytoProjectionToDown(String projectionID, CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network) {
        CytoPPINetworkProjectionToDown projection = new CytoPPINetworkProjectionToDown(cytoMotherNetwork, network, projectionID);
        projections.put(projectionID, projection);
        return projection;
    }

    public static CytoPPINetworkProjectionToUp createCytoProjectionToUp(String projectionID, CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network) {
        CytoPPINetworkProjectionToUp projection = new CytoPPINetworkProjectionToUp(cytoMotherNetwork, network, projectionID);
        projections.put(projectionID, projection);
        return projection;
    }

    public static CytoProteinProjection createCytoProteinProjection(String ProteinProjectionID, Protein proteinProject, CytoPPINetworkProjection projection, CytoProtein cytoMotherProtein) {
        CytoProteinProjection proteinProjection = new CytoProteinProjection(ProteinProjectionID, proteinProject, projection, cytoMotherProtein);
        projection.addCytoProtein(proteinProjection);
        return proteinProjection;
    }

    public static CytoAbstractPPINetwork getCytoNetwork(String CytoID) {
        CytoAbstractPPINetwork cytoNetwork = cytoNetworks.get(CytoID);
        if (cytoNetwork == null) {
            cytoNetwork = projections.get(CytoID);
        }
        return cytoNetwork;
    }

    public static IDMapper getNetworkIDMapper() {
        return networkIDMapper;
    }

    public static void setNetworkIDMapper(IDMapper networkIDMapper) {
        CytoDataHandle.networkIDMapper = networkIDMapper;
    }

    public static CytoPPINetwork createCytoNetwork(String ID, PPINetwork network) {
        CytoPPINetwork cytoNetwork = new CytoPPINetwork(network, ID);
        cytoNetworks.put(ID, cytoNetwork);
        return cytoNetwork;
    }

    public static Map<String, CytoPPINetworkProjection> getProjections() {
        return projections;
    }

    public static void setProjections(Map<String, CytoPPINetworkProjection> projections) {
        CytoDataHandle.projections = projections;
    }

    public static CytoPPINetworkProjection findNetworkProjectionByCytoID(String CytoID) {
        String CytoPPINetworkID = networkIDMapper.getIDByCytoID(CytoID);
        CytoPPINetworkProjection ret = projections.get(CytoPPINetworkID);

        return ret;
    }

    public static CytoAbstractPPINetwork findNetworkByCytoID(String CytoID) {
        String CytoPPINetworkID = networkIDMapper.getIDByCytoID(CytoID);

        CytoAbstractPPINetwork ret = cytoNetworks.get(CytoPPINetworkID);

        if (ret == null) {

            ret = projections.get(CytoPPINetworkID);
        }

        return ret;
    }

    public static void addNetworkIDMapping(String CytoID, String ID) {
        networkIDMapper.addMapping(CytoID, ID);
    }
}
