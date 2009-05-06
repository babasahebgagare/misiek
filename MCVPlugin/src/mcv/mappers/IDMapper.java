package mcv.mappers;

import java.util.HashMap;
import java.util.Map;

public class IDMapper {

    private Map<String, String> map = new HashMap<String, String>();

    public void addMapping(String CytoID, String ID) {
        map.put(CytoID, ID);
    }

    public String getIDByCytoID(String CytoID) {
        return map.get(CytoID);
    }

    public void deleteMapping(String CytoID) {
        map.remove(CytoID);
    }
}
