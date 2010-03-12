/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package ppine.main;

import ppine.cytolisteners.CytoListeners;
import cytoscape.Cytoscape;
import cytoscape.view.cytopanels.CytoPanelImp;
import cytoscape.visual.CalculatorCatalog;
import cytoscape.visual.EdgeAppearanceCalculator;
import cytoscape.visual.GlobalAppearanceCalculator;
import cytoscape.visual.NodeAppearanceCalculator;
import cytoscape.visual.VisualMappingManager;
import cytoscape.visual.VisualStyle;
import cytoscape.visual.calculators.Calculator;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.VerticalLayout;
import ppine.ui.LeftPanel;
import ppine.ui.PPINEMainPanel;
import ppine.ui.PluginMenusHandle;
import ppine.ui.familycolors.SpeciesFamilyColorPanel;
import ppine.visual.calculators.PPINEEdgeAppearanceCalculator;
import ppine.visual.calculators.PPINENodeAppearanceCalculator;

public class PluginInitializator {

    private static JPanel myPanel;

    static void activatePlugin() {
        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);
        String tabName = "NetworkEvolution";
        leftPanel.add(tabName, myPanel);
        int index = leftPanel.indexOfComponent(tabName);
        leftPanel.setSelectedIndex(index);
    }

    static void disactivatePlugin() {
        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);
        leftPanel.remove(myPanel);
    }

    private static void initCommonVisualStyle() {
        VisualMappingManager vmm = Cytoscape.getVisualMappingManager();

        CalculatorCatalog catalog = vmm.getCalculatorCatalog();
        VisualStyle currentVisualStyle = catalog.getVisualStyle("default");
        VisualStyle PPINEStyle = catalog.getVisualStyle("NetworkEvolutionStyle");
        if (PPINEStyle == null) {
            Calculator defaultNodeCalc = currentVisualStyle.getNodeAppearanceCalculator().getCalculators().get(0);
            Calculator defaultEdgeCalc = currentVisualStyle.getNodeAppearanceCalculator().getCalculators().get(0);
            NodeAppearanceCalculator nac = new PPINENodeAppearanceCalculator(defaultNodeCalc);
            EdgeAppearanceCalculator eac = new PPINEEdgeAppearanceCalculator(defaultEdgeCalc);
            GlobalAppearanceCalculator gac = currentVisualStyle.getGlobalAppearanceCalculator();
            PPINEStyle = new VisualStyle("NetworkEvolutionStyle", nac, eac, gac);
            //PPINEStyle.
            //PPINEStyle.setName("PPINEStyle");
            //PPINEStyle.setNodeAppearanceCalculator();
            //PPINEStyle.setEdgeAppearanceCalculator();

            catalog.addVisualStyle(PPINEStyle);
            vmm.setVisualStyle(PPINEStyle);
        } else {
        }
    }

    private static void initNetworkListeners() {
        CytoListeners.createListeners();
    }

    private static void initUI() {
        LeftPanel myLeftPanel = new LeftPanel();
        myPanel = new PPINEMainPanel();

        //   JPanel logsPanel = new LogsPanel();
        SpeciesFamilyColorPanel families = new SpeciesFamilyColorPanel();
        // MIPSPanel mipsPanel = new MIPSPanel();
        myPanel.setLayout(new VerticalLayout());
        myPanel.add(myLeftPanel);
        myPanel.add(families);
        // myPanel.add(mipsPanel);
        //     myPanel.add(logsPanel);

        PluginMenusHandle.setPPINEPanel(myPanel);
        PluginMenusHandle.setFamiliesColorListPanel(families);
    }

    public static void initAll() {
        initUI();
        initVisualStyles();
        PluginDataHandle.refreshPluginDataHandle();
    }

    public static void initVisualStyles() {
        initCommonVisualStyle();
        initNetworkListeners();
    }
}
