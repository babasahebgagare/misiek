package algorithm.smart;

public class IterationData {

    private int iter;
    private int clusters;

    public IterationData(final int iter, final int clusters) {
        this.iter = iter;
        this.clusters = clusters;
    }

    public int getClusters() {
        return clusters;
    }

    public void setClusters(final int clusters) {
        this.clusters = clusters;
    }

    public int getIter() {
        return iter;
    }

    public void setIter(final int iter) {
        this.iter = iter;
    }
}
