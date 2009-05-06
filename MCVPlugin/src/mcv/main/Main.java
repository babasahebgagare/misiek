package mcv.main;

import cytoscape.plugin.CytoscapePlugin;
import mcv.main.PluginDataHandle;
import mcv.main.PluginInitializator;

public class Main extends CytoscapePlugin {

    public Main() {
        PluginInitializator.initAll();
        PluginDataHandle.initPluginDataHandle();
    }
}

