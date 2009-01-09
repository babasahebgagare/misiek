package main;

import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;
import javax.swing.SwingConstants;
import ui.LeftPanel;
import utils.MemoLogger;
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
        Cytoscape.getSwingPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String PropertyName = evt.getPropertyName();
                if (PropertyName.equals(Cytoscape.NETWORK_CREATED)) {
                    MemoLogger.log("Network created: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_DESTROYED)) {
                    MemoLogger.log("Network deleted: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_LOADED)) {
                    MemoLogger.log("Network loaded: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_SAVED)) {
                    MemoLogger.log("Network saved: " + evt.getNewValue().toString());
                }
            }
        });
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
        initNetworkListeners();
    }
}
