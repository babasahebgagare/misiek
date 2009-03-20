package main;

import cytolisteners.CytoListeners;
import cytoscape.Cytoscape;
import cytoscape.ding.DingNetworkView;
import cytoscape.view.CyNetworkView;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import giny.view.GraphViewChangeEvent;
import giny.view.GraphViewChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.SwingConstants;
import ui.LeftPanel;
import utils.MemoLogger;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import visual.calculators.MCVEdgeAppearanceCalculator;
import visual.calculators.MCVNodeAppearanceCalculator;

public class PluginInitializator {

    private static void initCommonVisualStyle() {
        VisualMappingManager vmm = Cytoscape.getVisualMappingManager();
        CalculatorCatalog catalog = vmm.getCalculatorCatalog();
        VisualStyle MCVStyle = catalog.getVisualStyle("MCVStyle");
        if (MCVStyle == null) {

            MCVStyle = new VisualStyle(vmm.getVisualStyle());
            MCVStyle.setName("MCVStyle");
            MCVStyle.setNodeAppearanceCalculator(new MCVNodeAppearanceCalculator());
            MCVStyle.setEdgeAppearanceCalculator(new MCVEdgeAppearanceCalculator());

            catalog.addVisualStyle(MCVStyle);
        } else {
        }
    }

    private static void initNetworkListeners() {
        CytoListeners.createListeners();
    }

    private static void initUI() {
        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        LeftPanel myLeftPanel = new LeftPanel();
        leftPanel.add(ResourceBundle.getBundle("resources/ui").getString("TABNAME"), myLeftPanel);
    }

    public static void initAll() {
        initUI();
        initVisualStyles();
    }

    public static void initVisualStyles() {
        initCommonVisualStyle();
        initNetworkListeners();
    }
}
