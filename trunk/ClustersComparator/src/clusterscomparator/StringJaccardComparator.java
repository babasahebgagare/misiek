package clusterscomparator;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class StringJaccardComparator {

    private Map<String, String> clustering1;
    private Map<String, String> clustering2;
    private Set<String> range;
    private Double jaccard;
    private Double leftJaccard;
    private Double rightJaccard;

    StringJaccardComparator(Map<String, String> clustering1, Map<String, String> clustering2) {
        this.clustering1 = clustering1;
        this.clustering2 = clustering2;
    }

    public void setClustering1(Map<String, String> clustering1, Map<String, String> clustering2) {
        this.clustering1 = clustering1;
        this.clustering2 = clustering2;
    }

    public void generateRange(int from, int to) {
        for (String ex : clustering1.keySet()) {
            range.add(ex);
        }
    }

    public void setRange(Set<String> range) {
        this.range = range;
    }

    public void calculateJaccards() {

        Double N11 = Double.valueOf(0);
        Double N01 = Double.valueOf(0);
        Double N10 = Double.valueOf(0);
        boolean same1;
        boolean same2;

        for (String i : range) {
            for (String j : range) {

                if (i.compareTo(j) < 0) {
                    //      System.out.println("BADAM: " + i + " " + j);
                    if (clustering1.get(i).equals(clustering1.get(j))) {
                        same1 = true;
                    } else {
                        same1 = false;
                    }
                    if (clustering2.get(i).equals(clustering2.get(j))) {
                        same2 = true;
                    } else {
                        same2 = false;
                    }
                    if (same1 && same2) {
                        N11++;
                    }
                    if (!same1 && same2) {
                        N01++;
                    }
                    if (same1 && !same2) {
                        N10++;
                    }

                }
            }
        }
        jaccard = N11 / (N11 + N01 + N10);
        leftJaccard = N11 / (N11 + N10);
        rightJaccard = N11 / (N11 + N01);
    }

    public Double getJaccard() {
        return jaccard;
    }

    public Double getLeftJaccard() {
        return leftJaccard;
    }

    public Double getRightJaccard() {
        return rightJaccard;
    }
}
