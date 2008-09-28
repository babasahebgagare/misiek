package projector;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import structs.Protein;

public class ProjectorInfo {

    private Map<String, Collection<Protein>> projectorMap = new HashMap<String, Collection<Protein>>();

    public ProjectorInfo() {
    }

    public Map<String, Collection<Protein>> getProjectorMap() {
        return projectorMap;
    }

    public void setProjectorMap(Map<String, Collection<Protein>> projectorMap) {
        this.projectorMap = projectorMap;
    }
}
