package ppine.ui.mips;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.io.IOException;
import java.util.Set;
import javax.swing.JTextArea;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesCytoReader {

    private Complex proteinsComplex = new Complex("my_complex");

    public Complex readProteins(JTextArea area) throws IOException {

        CyNetwork network = Cytoscape.getCurrentNetwork();
        @SuppressWarnings("unchecked")
        Set<CyNode> nodes = network.getSelectedNodes();
        for (CyNode node : nodes) {
            String proteinID = node.getIdentifier();
            proteinsComplex.addProtein(proteinID.toUpperCase());
        }

        return proteinsComplex;
    }

    public Complex getNamesMapping() {
        return proteinsComplex;
    }
}

