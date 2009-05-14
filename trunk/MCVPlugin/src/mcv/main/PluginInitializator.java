package mcv.main;

import mcv.cytolisteners.CytoListeners;
import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import mcv.ui.LeftPanel;
import mcv.ui.MCVMainPanel;
import mcv.ui.PluginMenusHandle;
import mcv.ui.familycolors.SpeciesFamilyColorPanel;
import mcv.ui.logs.LogsPanel;
import mcv.visual.calculators.MCVEdgeAppearanceCalculator;
import mcv.visual.calculators.MCVNodeAppearanceCalculator;
import org.jdesktop.swingx.VerticalLayout;

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
        JPanel myPanel = new MCVMainPanel();

        JPanel logsPanel = new LogsPanel();
        SpeciesFamilyColorPanel families = new SpeciesFamilyColorPanel();
        myPanel.setLayout(new VerticalLayout());
        myPanel.add(myLeftPanel);
        myPanel.add(families);
        myPanel.add(logsPanel);

        PluginMenusHandle.setMcvPanel(myLeftPanel);
        PluginMenusHandle.setFamiliesColorListPanel(families);
        leftPanel.add(ResourceBundle.getBundle("mcv/resources/ui").getString("TABNAME"), myPanel);
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
