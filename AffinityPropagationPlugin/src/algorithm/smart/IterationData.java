package algorithm.smart;

public class IterationData {

    private int iter;
    private int clusters;

    public IterationData(int iter, int clusters) {
        this.iter = iter;
        this.clusters = clusters;
    }

    public int getClusters() {
        return clusters;
    }

    public void setClusters(int clusters) {
        this.clusters = clusters;
    }

    public int getIter() {
        return iter;
    }

    public void setIter(int iter) {
        this.iter = iter;
    }
}
