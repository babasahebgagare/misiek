package projector;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import structs.model.PPINetwork;
import main.DataHandle;
import structs.model.Protein;

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

    private static void addProjectorInfoForProteins(Protein Down, Protein Up) {

        PPINetwork DownNetwork = Down.getContext().getNetwork();
        PPINetwork UpNetwork = Up.getContext().getNetwork();

        Map<String, Collection<Protein>> UpMap = Down.getProjects().getProjectorMapUp();
        Map<String, Collection<Protein>> DownMap = Up.getProjects().getProjectorMapDown();

        if (!UpMap.containsKey(UpNetwork.getID())) {
            UpMap.put(UpNetwork.getID(), new HashSet<Protein>());
        }
        if (!DownMap.containsKey(DownNetwork.getID())) {
            DownMap.put(DownNetwork.getID(), new HashSet<Protein>());
        }

        UpMap.get(UpNetwork.getID()).add(Up);
        DownMap.get(DownNetwork.getID()).add(Down);
    }

    private static void calculateProjectorInfoForProtein(Protein protein) {
        Protein parentProtein = protein;

        while (parentProtein != null) {
            addProjectorInfoForProteins(protein, parentProtein);

            parentProtein = parentProtein.getContext().getParentProtein();


        }
    }
}
