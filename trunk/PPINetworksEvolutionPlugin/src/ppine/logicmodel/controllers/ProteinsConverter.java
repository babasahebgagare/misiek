package ppine.logicmodel.controllers;

import java.util.Collection;
import ppine.viewmodel.structs.CytoProtein;
import ppine.logicmodel.structs.Protein;
import ppine.utils.IDCreator;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;

public class ProteinsConverter {

    static void convertNetworkProteins(CytoAbstractPPINetwork cytoPPINetwork, Collection<Protein> proteins) {
        for (Protein protein : proteins) {
            String cytoProteinID = IDCreator.createProteinProjectionID(protein, cytoPPINetwork);
            CytoProtein cytoProtein = new CytoProtein(cytoProteinID, protein, cytoPPINetwork);
            cytoPPINetwork.addCytoProtein(cytoProtein);
        }
    }
}
