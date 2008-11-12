package converter;

import cytoscape.CyNetwork;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import java.util.Collection;
import main.CytoDataHandle;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.Protein;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {

            CytoProtein cytoProtein = new CytoProtein(protein.getID(), protein);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
