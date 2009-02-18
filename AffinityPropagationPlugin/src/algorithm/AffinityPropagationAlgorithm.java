package algorithm;

import matrix.DoubleMatrix2D;

public abstract class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm {

    private double lambda = 0.5;
    private DoubleMatrix2D S;

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

    public double[][] getSimilarities() {
        return S.getMatrix();
    }

    public void setSimilarities(double[][] similarities) {
        int Nn = similarities.length;
        this.S = new DoubleMatrix2D(Nn, Nn, similarities);
    }

    public DoubleMatrix2D getS() {
        return S;
    }

    public void setS(DoubleMatrix2D S) {
        this.S = S;
    }
}

