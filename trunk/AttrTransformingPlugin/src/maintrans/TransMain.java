package maintrans;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import panel.DoubleTransformingPanel;

public class TransMain extends CytoscapePlugin {

    public TransMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        JPanel myAff = new DoubleTransformingPanel();
        myAff.setLayout(new FlowLayout());

        leftPanel.add("Attributes transforming panel", myAff);
        System.out.println("Attributes transforming panel");
    }
}

