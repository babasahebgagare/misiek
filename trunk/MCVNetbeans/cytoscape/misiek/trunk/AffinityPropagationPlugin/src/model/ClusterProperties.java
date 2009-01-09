package model;

import cytoscape.layout.LayoutProperties;

public class ClusterProperties extends LayoutProperties {

    public ClusterProperties(String propertyPrefix) {
        super(propertyPrefix);
        setModuleType("clusterMaker");
    }
}
