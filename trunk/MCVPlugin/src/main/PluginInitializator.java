package main;

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
        Cytoscape.getSwingPropertyChangeSupport().addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent evt) {
                String PropertyName = evt.getPropertyName();

                if (PropertyName.equals("NETWORK_VIEW_CREATED")) {
                    MemoLogger.log("Network view created: " + evt.getNewValue().toString());
                    CyNetworkView cy = ((CyNetworkView) evt.getNewValue());
                    cy.addGraphViewChangeListener(new GraphViewChangeListener() {

                        public void graphViewChanged(GraphViewChangeEvent event) {
                            if (event.getType() == GraphViewChangeEvent.NODES_SELECTED_TYPE) {
                                System.out.println("SELECTED");
                            } else if (event.getType() == GraphViewChangeEvent.NODES_UNSELECTED_TYPE) {
                                System.out.println("UNSELECTED");
                            }
                        }
                    });
                } else if (PropertyName.equals("NETWORK_VIEW_DESTROYED")) {
                    String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();

                    MemoLogger.log("Network view destroyed: " + networkName);
                } else if (PropertyName.equals(Cytoscape.NETWORK_CREATED)) {
                    MemoLogger.log("Network created: " + evt.getNewValue().toString());
                } else if (PropertyName.equals(Cytoscape.NETWORK_DESTROYED)) {
                    System.out.println(evt.getNewValue().toString());

                    //  String networkName = ((DingNetworkView) evt.getNewValue()).getTitle();
                    String networkID = evt.getNewValue().toString();
                    //   System.out.println("DELETED: " + networkName + " " + networkID);
                    CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();
                    CytoAbstractPPINetwork netOrNull = cdh.tryFindNetworkByCytoID(networkID);
                    if (netOrNull != null) {
                        cdh.cytoNetworkViewDeleted(networkID, netOrNull.getID());
                    }
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
