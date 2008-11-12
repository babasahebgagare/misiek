package main;

import java.util.HashMap;
import java.util.Map;
import mappers.IDMapper;
import structs.model.CytoAbstractPPINetwork;
import structs.model.CytoPPINetwork;
import structs.model.PPINetwork;
import structs.model.CytoPPINetworkProjection;

public class CytoDataHandle {

    private static IDMapper networkIDMapper = new IDMapper();
    private static IDMapper projectionsIDMapper = new IDMapper();
    private static Map<String, CytoPPINetworkProjection> projections = new HashMap<String, CytoPPINetworkProjection>();
    private static Map<String, CytoPPINetwork> cytoNetworks = new HashMap<String, CytoPPINetwork>();

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

    public static IDMapper getProjectionsIDMapper() {
        return projectionsIDMapper;
    }

    public static void setProjectionsIDMapper(IDMapper projectionsIDMapper) {
        CytoDataHandle.projectionsIDMapper = projectionsIDMapper;
    }

    public static CytoAbstractPPINetwork findNetworkByCytoID(String CytoID) {

        String CytoPPINetworkID = networkIDMapper.getIDByCytoID(CytoID);
        CytoAbstractPPINetwork ret = cytoNetworks.get(CytoPPINetworkID);
        if (ret == null) {
            CytoPPINetworkID = projectionsIDMapper.getIDByCytoID(CytoID);
            ret = projections.get(CytoPPINetworkID);
        }
        return ret;
    }

    /*  public static CytoPPINetworkProjection findProjectionByCytoID(String PPINetworkProjectionCytoID) {
    
    String PPINetworkProjectionID = projectionsIDMapper.getIDByCytoID(PPINetworkProjectionCytoID);
    return projections.get(PPINetworkProjectionID);
    }
     */
    public static void addProjectionIDMapping(String CytoID, String ID) {
        projectionsIDMapper.addMapping(CytoID, ID);
    }

    public static void addNetworkIDMapping(String CytoID, String ID) {
        networkIDMapper.addMapping(CytoID, ID);
    }
}
