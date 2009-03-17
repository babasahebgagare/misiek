package cyto;

import cytoscape.layout.LayoutProperties;

public class CytoClusterProperties extends LayoutProperties {

    public CytoClusterProperties(final String propertyPrefix) {
        super(propertyPrefix);
        setModuleType("clusterMaker");
    }
}
