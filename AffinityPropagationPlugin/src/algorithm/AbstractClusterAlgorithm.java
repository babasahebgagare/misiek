package algorithm;

public abstract class AbstractClusterAlgorithm {

    public abstract String getShortName();

    public abstract String getName();

    public abstract void init();

    public abstract void halt();

    public abstract void setN(int N);

    public abstract void setSimilarities(double[][] sim);

    public abstract Integer[] doCluster();
}
