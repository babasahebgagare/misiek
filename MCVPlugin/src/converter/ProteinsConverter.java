package converter;

import java.util.Collection;
import structs.model.CytoPPINetwork;
import structs.model.CytoProtein;
import structs.model.Protein;
import utils.IDCreator;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {

            String cytoProteinID = IDCreator.createProteinProjectionID(protein, cytoPPINetwork);
            CytoProtein cytoProtein = new CytoProtein(cytoProteinID, protein, cytoPPINetwork);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
