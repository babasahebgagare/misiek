package ppine.ui.mips;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class StringJaccardComparator {

    private Map<String, String> clustering1;
    private Map<String, String> clustering2;
    private Collection<String> range;
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

    private Collection<String> getInter(Collection<String> myComplexCollection, Collection<String> mipsComplexCollection) {
        Collection<String> ret = new TreeSet<String>(myComplexCollection);
        ret.retainAll(mipsComplexCollection);
        System.out.println("range: " + ret.size());
        return ret;
    }

    private Collection<String> getDiff(Collection<String> a, Collection<String> b) {
        Collection<String> ret = new TreeSet<String>(a);
        ret.removeAll(b);
        return ret;
    }

    public void generateRange() {
        range = getInter(clustering1.keySet(), clustering2.keySet());
        System.out.println("my: " + clustering1.keySet().size());
        System.out.println("mips: " + clustering2.keySet().size());
        System.out.println("my-mips: " + getDiff(clustering1.keySet(), clustering2.keySet()).size());
        System.out.println("mips-my: " + getDiff(clustering2.keySet(), clustering1.keySet()).size());
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
                    String clust1I = clustering1.get(i);
                    String clust1J = clustering1.get(j);
                    if (clust1I == null || clust1J == null) {
                        System.out.println("problem: " + i + " " + j);
                        same1 = false;
                    } else if (clust1I.equals(clust1J)) {
                        same1 = true;
                    } else {
                        same1 = false;
                    }
                    String clust2I = clustering2.get(i);
                    String clust2J = clustering2.get(j);
                    if (clust2I == null || clust2J == null) {
                        System.out.println("problem: " + i + " " + j);
                        same2 = false;
                    } else if (clust2I.equals(clust2J)) {
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
