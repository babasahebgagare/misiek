package mcv.logicmodel.controllers;

import java.util.Collection;
import mcv.viewmodel.structs.CytoProtein;
import mcv.logicmodel.structs.Protein;
import mcv.utils.IDCreator;
import mcv.viewmodel.structs.CytoAbstractPPINetwork;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoAbstractPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {
            String cytoProteinID = IDCreator.createProteinProjectionID(protein, cytoPPINetwork);
            CytoProtein cytoProtein = new CytoProtein(cytoProteinID, protein, cytoPPINetwork);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
