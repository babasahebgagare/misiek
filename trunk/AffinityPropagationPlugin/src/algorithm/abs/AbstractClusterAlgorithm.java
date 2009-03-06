package algorithm.abs;

import algorithm.smart.Cluster;
import java.util.Map;

public abstract class AbstractClusterAlgorithm<T> {

    public abstract String getShortName();

    public abstract String getName();

    public abstract void init();

    public abstract void halt();

    public abstract void setN(int N);

    public abstract void setSimilarities(T x, T y, Double sim);

    public abstract Map<T, T> doCluster();

    public abstract Map<T, Cluster<T>> doClusterAssoc();
}
