package algorithm.smart;

import java.util.Arrays;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Map;

/**
 *
 * @author misiek
 */
public class ExamplarsCollection {

    Map<Integer, Examplar> examplars = new TreeMap<Integer, Examplar>();
    Integer convits = null;

    public ExamplarsCollection() {
    }

    public ExamplarsCollection(final Integer convits) {
        this.convits = convits;
    }

    public int size() {
        return examplars.size();
    }

    public void setSimilarity(final Integer from, final Integer to, final double sim) {
        Examplar exfrom = examplars.get(from);
        if (exfrom == null) {
            exfrom = new Examplar(from, convits);
            examplars.put(from, exfrom);
        }
        Examplar exto = examplars.get(to);
        if (exto == null) {
            exto = new Examplar(to, convits);
            examplars.put(to, exto);
        }
        exfrom.createSibling(sim, to);
    }

    @Override
    public String toString() {
        StringBuffer ret = new StringBuffer();
        for (Integer key : examplars.keySet()) {
            ret.append(key);
            ret.append(": ");
            ret.append(examplars.get(key).toString());
            ret.append("\n");
        }
        return ret.toString();
    }

    public Map<Integer, Examplar> getExamplars() {
        return examplars;
    }

    public void setExamplars(final Map<Integer, Examplar> examplars) {
        this.examplars = examplars;
    }

    private String valuesToString(String kind) {
        int N = examplars.size();
        StringBuffer res = new StringBuffer();
        Double[][] matrix = new Double[N][N];
        Map<Integer, Integer> mapper = new TreeMap<Integer, Integer>();

        Integer[] examplarsPom = new Integer[N];
        int pos = 0;
        for (Examplar examplar : examplars.values()) {
            examplarsPom[pos] = (examplar.getName());
            pos++;
        }
        Arrays.sort(examplarsPom);

        for (int i = 0; i < N; i++) {
            mapper.put(examplarsPom[i], Integer.valueOf(i));
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Double.valueOf(-100000);
            }
        }

        for (Examplar examplar : examplars.values()) {
            Collection<SiblingData> sibling = examplar.getSiblingMap().values();
            for (SiblingData data : sibling) {
                Integer row = mapper.get(examplar.getName());
                Integer col = mapper.get(data.getExamplarName());

                Double value;
                if (kind.equals("R")) {
                    value = data.getR();
                } else if (kind.equals("S")) {
                    value = data.getS();
                } else {
                    value = data.getA();
                }
                matrix[row][col] = value;
            }
        }

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {
                res.append(matrix[i][j] + " ");
            }
            res.append("\n");
        }

        return res.toString();
    }

    public String responsiblitiesToString() {
        return valuesToString("R");
    }

    public String availibilitiesToString() {
        return valuesToString("A");
    }

    public String similaritiesToString() {
        return valuesToString("S");
    }
}
