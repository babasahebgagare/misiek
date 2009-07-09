package ppine.main;

import cytoscape.plugin.CytoscapePlugin;

public class Main extends CytoscapePlugin {

    public Main() {
        PluginInitializator.initAll();
        PluginDataHandle.refreshPluginDataHandle();
    }
}

