/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author misiek
 */
public class Examplar {

    private Map<String, SiblingData> siblingMap = new HashMap<String, SiblingData>();
    private String name;

    public Examplar(String name) {
        this.name = name;
    }

    public void createSibling(double a, double s, String siblingName) {
        SiblingData sibling = new SiblingData(a, s, siblingName);
        siblingMap.put(siblingName, sibling);
    }

    @Override
    public String toString() {
        String ret = "";
        for (String key : siblingMap.keySet()) {
            ret += siblingMap.get(key).toString() + "\n";
        }
        return ret;
    }

    public Map<String, SiblingData> getSiblingMap() {
        return siblingMap;
    }

    public String getName() {
        return name;
    }
}
