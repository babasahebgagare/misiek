package maintrans;

import cytoscape.Cytoscape;
import cytoscape.plugin.CytoscapePlugin;
import cytoscape.view.cytopanels.CytoPanelImp;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TransMain extends CytoscapePlugin {

    public TransMain() {

        CytoPanelImp leftPanel = (CytoPanelImp) Cytoscape.getDesktop().getCytoPanel(SwingConstants.WEST);

        JPanel myAff = new JPanel();
        myAff.setLayout(new FlowLayout());

        leftPanel.add("Affinity panel", myAff);
        System.out.println("Affinity propagation");
    }
}

