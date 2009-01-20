package model;

import cytoscape.layout.LayoutProperties;

public class CytoClusterProperties extends LayoutProperties {

    public CytoClusterProperties(String propertyPrefix) {
        super(propertyPrefix);
        setModuleType("clusterMaker");
    }
}
