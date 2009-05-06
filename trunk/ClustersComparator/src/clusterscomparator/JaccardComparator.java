package clusterscomparator;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class JaccardComparator {

    private Map<Integer, Integer> clustering1;
    private Map<Integer, Integer> clustering2;
    private Set<Integer> range;
    private Double jaccard;
    private Double leftJaccard;
    private Double rightJaccard;

    JaccardComparator(Map<Integer, Integer> clustering1, Map<Integer, Integer> clustering2) {
        this.clustering1 = clustering1;
        this.clustering2 = clustering2;
    }

    public void setClustering1(Map<Integer, Integer> clustering1, Map<Integer, Integer> clustering2) {
        this.clustering1 = clustering1;
        this.clustering2 = clustering2;
    }

    public void generateRange(int from, int to) {
        for (int i = from; i <= to; i++) {
            range.add(Integer.valueOf(i));
        }
    }

    public void setRange(Set<Integer> range) {
        this.range = range;
    }

    public void calculateJaccards() {

        Double N11 = Double.valueOf(0);
        Double N01 = Double.valueOf(0);
        Double N10 = Double.valueOf(0);
        boolean same1;
        boolean same2;

        for (Integer i : range) {
            for (Integer j : range) {

                if (i < j) {
                    Integer cluster1I = clustering1.get(i);
                    Integer cluster1J = clustering1.get(j);
                    if (cluster1I == null && cluster1J == null) {
                        same1 = true;
                    } else if ((cluster1I != null && cluster1J == null) || (cluster1I == null && cluster1J != null)) {
                        same1 = false;
                    } else if (cluster1I.equals(cluster1J)) {
                        same1 = true;
                    } else {
                        same1 = false;
                    }
                    Integer cluster2I = clustering2.get(i);
                    Integer cluster2J = clustering2.get(j);
                    if (cluster2I == null && cluster2J == null) {
                        same2 = true;
                    } else if ((cluster2I != null && cluster2J == null) || (cluster2I == null && cluster2J != null)) {
                        same2 = false;
                    } else if (cluster2I.equals(cluster2J)) {
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
