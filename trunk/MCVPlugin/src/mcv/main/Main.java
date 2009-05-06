package mcv.main;

import cytoscape.plugin.CytoscapePlugin;

public class Main extends CytoscapePlugin {

    public Main() {
        PluginInitializator.initAll();
        PluginDataHandle.initPluginDataHandle();
    }
}

