package algorithm;

public abstract class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm {

    private double lambda = 0.5;

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
}