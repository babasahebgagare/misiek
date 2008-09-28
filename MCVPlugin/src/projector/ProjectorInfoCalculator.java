package projector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import structs.PPINetwork;
import main.DataHandle;
import structs.Protein;
import utils.Messenger;

public class ProjectorInfoCalculator {

    public static void calculateProjectorInfo() {

        Collection<PPINetwork> networks = DataHandle.getNetworks().values();

        for (PPINetwork network : networks) {

            Collection<Protein> proteins = network.getProteins().values();

            for (Protein protein : proteins) {
                calculateProjectorInfoForProtein(protein);

            }
        }
    }

    private static void addProjectorInfoForProteins(Protein A, Protein B) {
        PPINetwork ANetwork = A.getContext().getNetwork();
        PPINetwork BNetwork = B.getContext().getNetwork();
        Map<String, Collection<Protein>> AMap = A.getProjects().getProjectorMap();
        Map<String, Collection<Protein>> BMap = B.getProjects().getProjectorMap();

        if (AMap.get(BNetwork.getID()) == null) {
            AMap.put(BNetwork.getID(), new HashSet<Protein>());
        }
        if (BMap.get(ANetwork.getID()) == null) {
            BMap.put(ANetwork.getID(), new HashSet<Protein>());
        }

        AMap.get(BNetwork.getID()).add(B);
        BMap.get(ANetwork.getID()).add(A);
    }

    private static void calculateProjectorInfoForProtein(Protein protein) {
        Protein parentProtein = protein.getContext().getParentProtein();

        while (parentProtein != null) {
            addProjectorInfoForProteins(protein, parentProtein);

            parentProtein = parentProtein.getContext().getParentProtein();


        }
    }
}
