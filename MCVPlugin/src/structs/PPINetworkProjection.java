package structs;

import java.util.HashMap;
import java.util.Map;

public class PPINetworkProjection extends PPINetwork {

    private Map<String, GroupNode> groupNodes = new HashMap<String, GroupNode>();

    public PPINetworkProjection(String NetworkID) {
        super(NetworkID, null);
    }
}
