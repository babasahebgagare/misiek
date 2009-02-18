package logicmodel.controllers;

import java.util.Collection;
import viewmodel.structs.CytoPPINetwork;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.Protein;
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
