package algorithm.abs;

import java.awt.event.ActionListener;

public abstract class AffinityPropagationAlgorithm<T> extends AbstractClusterAlgorithm<T> {

    private double lambda;
    private int iterations;
    protected Integer convits = null;
    protected ActionListener iteractionListenerOrNull = null;

    public void addIterationListener(final ActionListener listener) {
        this.iteractionListenerOrNull = listener;
    }

    @Override
    public String getShortName() {
        return "AP";
    }

    @Override
    public String getName() {
        return "Affinity Propagation";
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(final double lambda) {
        this.lambda = lambda;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(final int iterations) {
        this.iterations = iterations;
    }

    public Integer getConvits() {
        return convits;
    }

    public void setConvits(final Integer convits) {
        this.convits = convits;
    }

    public abstract void setSimilarities(double[][] sim);
}