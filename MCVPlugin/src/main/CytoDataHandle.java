package main;

import java.util.HashMap;
import java.util.Map;
import mappers.IDMapper;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoGroupNode;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.CytoProteinProjection;
import structs.model.PPINetwork;
import structs.model.CytoPPINetworkProjection;
import structs.model.Protein;

public class CytoDataHandle {

    private static IDMapper networkIDMapper = new IDMapper();
    private static Map<String, CytoPPINetworkProjection> projections = new HashMap<String, CytoPPINetworkProjection>();
    private static Map<String, CytoPPINetwork> cytoNetworks = new HashMap<String, CytoPPINetwork>();

    public static CytoGroupNode createCytoGroupNode(String groupNodeID, CytoProtein cytoProtein) {
        CytoGroupNode cytoGroupNode = new CytoGroupNode(groupNodeID, cytoProtein);
        return cytoGroupNode;
    }

    public static CytoPPINetworkProjection createCytoProjectionNetwork(String projectionID, CytoAbstractPPINetwork cytoMotherNetwork, PPINetwork network) {
        CytoPPINetworkProjection projection = new CytoPPINetworkProjection(cytoMotherNetwork, network, projectionID);
        projections.put(projectionID, projection);
        return projection;
    }

    public static CytoProteinProjection createCytoProteinProjection(String ProteinProjectionID, Protein proteinProject, CytoPPINetworkProjection projection) {
        CytoProteinProjection proteinProjection = new CytoProteinProjection(ProteinProjectionID, proteinProject, projection);
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
