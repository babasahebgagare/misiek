package main;

import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import javax.swing.SwingConstants;
import ui.LeftPanel;

/**
 *
 * @author misiek
 */
public class PluginInitializator {

    private static void initUI() {
        CytoPanelImp leftPanel = (CytoPanelImp)Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);
        
        leftPanel.add("MCVPlugin", new LeftPanel());      
    }
    
    
    public static void initAll() {
        initUI();  
    }
    
}
