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

    public Double calculateJaccard() {

        Double N11 = Double.valueOf(0);
        Double N01 = Double.valueOf(0);
        Double N10 = Double.valueOf(0);
        boolean same1;
        boolean same2;

        for (Integer i : range) {
            for (Integer j : range) {

                if (i < j) {
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
        return N11 / (N11 + N01 + N10);
    }
}
