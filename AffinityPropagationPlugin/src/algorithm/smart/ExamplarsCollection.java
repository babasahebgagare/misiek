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

    public void setSimilarity(String from, String to, double sim, double a) {
        Examplar exfrom = examplars.get(from);
        if (exfrom == null) {
            exfrom = new Examplar(from);
            examplars.put(from, exfrom);
        }
        exfrom.createSibling(a, sim, to);
    }

    @Override
    public String toString() {
        String ret = "";
        for (String key : examplars.keySet()) {
            ret += key + ": " + examplars.get(key).toString() + "\n";
        }
        return ret;
    }

    public Map<String, Examplar> getExamplars() {
        return examplars;
    }

    public void setExamplars(Map<String, Examplar> examplars) {
        this.examplars = examplars;
    }
    
}
