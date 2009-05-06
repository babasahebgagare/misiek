package mcv.logicmodel.structs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mcv.logicmodel.structs.Protein;

public class ProjectorInfo {

    private Map<String, Collection<Protein>> projectorMapUp = new HashMap<String, Collection<Protein>>();
    private Map<String, Collection<Protein>> projectorMapDown = new HashMap<String, Collection<Protein>>();

    public ProjectorInfo() {
    }

    public Map<String, Collection<Protein>> getProjectorMapDown() {
        return projectorMapDown;
    }

    public void setProjectorMapDown(Map<String, Collection<Protein>> projectorMapDown) {
        this.projectorMapDown = projectorMapDown;
    }

    public Map<String, Collection<Protein>> getProjectorMapUp() {
        return projectorMapUp;
    }

    public void setProjectorMapUp(Map<String, Collection<Protein>> projectorMapUp) {
        this.projectorMapUp = projectorMapUp;
    }
}
