package algorithm.abs;

public abstract class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm<String> {

    private double lambda = 0.5;
    private int iterations = 10;

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