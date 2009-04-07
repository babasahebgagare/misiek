package algorithm.smart;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author misiek
 */
public class Examplar {

    private Map<String, SiblingData> siblingMap = new HashMap<String, SiblingData>();
    private String name;
    private Vector<Boolean> imcenter = null;
    private Integer convits = null;

    public Examplar(final String name, final Integer convits) {
        this.name = name;
        this.convits = convits;
        if (convits != null) {
            imcenter = new Vector<Boolean>();
        }
    }

    public void createSibling(final double s, final String siblingName) {
        SiblingData sibling = new SiblingData(s, siblingName);
        siblingMap.put(siblingName, sibling);
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer(name);
        ret.append(": ");
        for (String key : siblingMap.keySet()) {
            ret.append(siblingMap.get(key).toString());
            ret.append("\n");
        }
        return ret.toString();
    }

    public Map<String, SiblingData> getSiblingMap() {
        return siblingMap;
    }

    public String getName() {
        return name;
    }

    public void setImCenter(final Boolean c, final int iteration) {
        if (convits != null) {

            imcenter.add(0, c);
            if (imcenter.size() == convits.intValue() + 1) {
                imcenter.remove(convits.intValue());
            }
        }
    }

    public boolean changed() {

        if (imcenter.size() < convits) {
            return true;
        }

        boolean res = false;
        boolean value = imcenter.firstElement();

        for (Boolean b : imcenter) {
            if (b != value) {
                res = true;
                break;
            }
        }

        return res;
    }
}
