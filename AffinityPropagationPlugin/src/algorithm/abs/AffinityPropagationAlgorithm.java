package algorithm.abs;

import java.awt.event.ActionListener;

public abstract class AffinityPropagationAlgorithm<T> extends AbstractClusterAlgorithm<T> {

    private double lambda = 0.5;
    private int iterations = 10;
    protected ActionListener iteractionListener = null;

    public void addIterationListener(ActionListener listener) {
        this.iteractionListener = listener;
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

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}