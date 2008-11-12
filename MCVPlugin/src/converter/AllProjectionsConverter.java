package converter;

import java.util.Collection;
import main.CytoDataHandle;
import main.DataHandle;
import structs.model.CytoPPINetworkProjection;

public class AllProjectionsConverter {

    public static void convertAllProjections() {
        Collection<CytoPPINetworkProjection> projections = CytoDataHandle.getProjections().values();

        for (CytoPPINetworkProjection projection : projections) {

            ProjectionConverter.convertPPINetworkProjection(projection);
        }
    }
}
