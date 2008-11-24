package converter;

import java.util.Collection;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.Protein;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {

            CytoProtein cytoProtein = new CytoProtein(protein.getID(), protein, cytoPPINetwork);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
