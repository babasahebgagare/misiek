package algorithm.smart;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author misiek
 */
public class Examplar implements Comparable<Examplar> {

    private Map<Integer, SiblingData> siblingMap = new HashMap<Integer, SiblingData>();
    private Integer name;
    private Vector<Boolean> imcenter = null;
    private Integer convits = null;

    public Examplar(final Integer name, final Integer convits) {
        this.name = name;
        this.convits = convits;
        if (convits != null) {
            imcenter = new Vector<Boolean>();
        }
    }

    public void createSibling(final double s, final Integer siblingName) {
        SiblingData sibling = new SiblingData(s, siblingName);
        siblingMap.put(siblingName, sibling);
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer(name);
        ret.append(": ");
        for (Integer key : siblingMap.keySet()) {
            ret.append(siblingMap.get(key).toString());
            ret.append("\n");
        }
        return ret.toString();
    }

    public Map<Integer, SiblingData> getSiblingMap() {
        return siblingMap;
    }

    public Integer getName() {
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

    public int compareTo(Examplar ex) {

        return this.getName().compareTo(ex.getName());

    }
}
