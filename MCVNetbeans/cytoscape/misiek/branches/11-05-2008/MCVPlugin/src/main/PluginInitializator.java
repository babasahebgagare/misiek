package main;

import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import java.util.ResourceBundle;
import javax.swing.SwingConstants;
import ui.LeftPanel;
import visual.calculators.MCVEdgeAppearanceCalculator;
import visual.calculators.MCVEdgeProjectionedAppearanceCalculator;
import visual.calculators.MCVNodeAppearanceCalculator;
import visual.calculators.MCVNodeProjectionedAppearanceCalculator;

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

    private static void initProjectionedVisualStyle() {
        VisualMappingManager vmm = Cytoscape.getVisualMappingManager();
        CalculatorCatalog catalog = vmm.getCalculatorCatalog();
        VisualStyle MCVProjStyle = catalog.getVisualStyle("MCVProjStyle");
        if (MCVProjStyle == null) {

            MCVProjStyle = new VisualStyle(vmm.getVisualStyle());
            MCVProjStyle.setName("MCVProjStyle");
            MCVProjStyle.setNodeAppearanceCalculator(new MCVNodeProjectionedAppearanceCalculator());
            MCVProjStyle.setEdgeAppearanceCalculator(new MCVEdgeProjectionedAppearanceCalculator());

            catalog.addVisualStyle(MCVProjStyle);
        } else {
        }
    }

    private static void initUI() {
        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        leftPanel.add(ResourceBundle.getBundle("resources/ui").getString("TABNAME"), new LeftPanel());
    }

    public static void initAll() {
        initUI();
        initVisualStyles();
    }

    public static void initVisualStyles() {
        initCommonVisualStyle();
        initProjectionedVisualStyle();

    }
}
