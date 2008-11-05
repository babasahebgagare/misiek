package converter;

import java.util.Collection;
import main.DataHandle;
import structs.model.PPINetworkProjection;

public class AllProjectionsConverter {

    public static void convertAllProjections() {
        Collection<PPINetworkProjection> projections = DataHandle.getProjections().values();

        for (PPINetworkProjection projection : projections) {

            ProjectionConverter.convertPPINetworkProjection(projection);
        }
    }
}
