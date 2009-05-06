package mcv.logicmodel.controllers;

import java.util.Collection;
import mcv.viewmodel.structs.CytoPPINetwork;
import mcv.viewmodel.structs.CytoProtein;
import mcv.logicmodel.structs.Protein;
import mcv.utils.IDCreator;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {
            String cytoProteinID = IDCreator.createProteinProjectionID(protein, cytoPPINetwork);
            CytoProtein cytoProtein = new CytoProtein(cytoProteinID, protein, cytoPPINetwork);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
