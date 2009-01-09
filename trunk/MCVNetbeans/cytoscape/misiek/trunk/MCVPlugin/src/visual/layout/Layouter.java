package visual.layout;

import viewmodel.structs.CytoPPINetworkProjectionToDown;
import viewmodel.structs.CytoPPINetworkProjectionToUp;

public abstract class Layouter {

    private static Layouter layouter = new DefaultLayouter();

    public static Layouter getInstance() {
        return layouter;
    }

    public abstract void ProjectionToDownLayout(CytoPPINetworkProjectionToDown projection);

    public abstract void ProjectionToUpLayout(CytoPPINetworkProjectionToUp projection);
}
