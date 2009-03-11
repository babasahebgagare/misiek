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
public class ExamplarsCollection {

    Map<String, Examplar> examplars = new HashMap<String, Examplar>();
    Integer convits = null;

    public ExamplarsCollection() {
    }

    public ExamplarsCollection(Integer convits) {
        this.convits = convits;
    }

    public int size() {
        return examplars.size();
    }

    public void setSimilarity(String from, String to, double sim) {
        Examplar exfrom = examplars.get(from);
        if (exfrom == null) {
            exfrom = new Examplar(from, convits);
            examplars.put(from, exfrom);
        }
        exfrom.createSibling(sim, to);
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for (String key : examplars.keySet()) {
            ret.append(key);
            ret.append(": ");
            ret.append(examplars.get(key).toString());
            ret.append("\n");
        }
        return ret.toString();
    }

    public Map<String, Examplar> getExamplars() {
        return examplars;
    }

    public void setExamplars(Map<String, Examplar> examplars) {
        this.examplars = examplars;
    }
}
