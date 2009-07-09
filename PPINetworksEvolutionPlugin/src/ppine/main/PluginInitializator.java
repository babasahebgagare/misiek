package ppine.main;

import ppine.cytolisteners.CytoListeners;
import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import java.util.ResourceBundle;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ppine.ui.LeftPanel;
import ppine.ui.MCVMainPanel;
import ppine.ui.PluginMenusHandle;
import ppine.ui.familycolors.SpeciesFamilyColorPanel;
import ppine.visual.calculators.MCVEdgeAppearanceCalculator;
import ppine.visual.calculators.MCVNodeAppearanceCalculator;
import org.jdesktop.swingx.VerticalLayout;

public class PluginInitializator {

    private static void initCommonVisualStyle() {
        VisualMappingManager vmm = Cytoscape.getVisualMappingManager();
        CalculatorCatalog catalog = vmm.getCalculatorCatalog();
        VisualStyle MCVStyle = catalog.getVisualStyle("PPINEStyle");
        if (MCVStyle == null) {

            MCVStyle = new VisualStyle(vmm.getVisualStyle());
            MCVStyle.setName("PPINEStyle");
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

     //   JPanel logsPanel = new LogsPanel();
        SpeciesFamilyColorPanel families = new SpeciesFamilyColorPanel();
        myPanel.setLayout(new VerticalLayout());
        myPanel.add(myLeftPanel);
        myPanel.add(families);
   //     myPanel.add(logsPanel);

        PluginMenusHandle.setMcvPanel(myPanel);
        PluginMenusHandle.setFamiliesColorListPanel(families);
        leftPanel.add(ResourceBundle.getBundle("ppine/resources/ui").getString("TABNAME"), myPanel);
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
