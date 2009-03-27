package algorithm.abs;

import algorithm.smart.Cluster;
import algorithm.smart.IterationData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AffinityPropagationAlgorithm<String> extends AbstractClusterAlgorithm<String> {

    private double lambda;
    private int iterations;
    protected boolean convergence;
    protected Integer convits = null;
    protected ActionListener iteractionListenerOrNull = null;
    protected Map<String, Cluster<String>> assigments;
    //   protected Collection<String> centers;
//    protected Collection<String> objects;

    public void addIterationListener(final ActionListener listener) {
        this.iteractionListenerOrNull = listener;
    }

    @Override
    public java.lang.String getShortName() {
        return "AP";
    }

    @Override
    public java.lang.String getName() {
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

    @Override
    public Map<String, String> doCluster() {
        final Map<String, Cluster<String>> help = doClusterAssoc();
        final Map<String, String> res = new HashMap<String, String>();

        for (Entry<String, Cluster<String>> entry : help.entrySet()) {
            for (String obj : entry.getValue().getElements()) {
                res.put(obj, entry.getKey());
            }
        }

        return res;
    }

    public Map<String, Cluster<String>> doClusterAssoc() {
        int iters = getIterations();
        if (iteractionListenerOrNull != null) {
            iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(1, 0), 0, "ITERATION"));
        }

        for (int iteration = 0; iteration < iters; iteration++) {

            copyResponsibilies();
            computeResponsibilities();
            avgResponsibilies();

            copyAvailabilities();
            computeAvailabilities();
            avgAvailabilities();

            if (iteration + 1 != iterations && iteractionListenerOrNull != null) {
                computeCenters();
                iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(iteration + 2, getClustersNumber()), 0, "ITERATION")); //TODO
            }
            convergence = checkConvergence();
            if (convergence) {
                break;
            }
        }


        computeCenters();
        computeAssigments();

        return assigments;
    }

//    protected abstract Double getSimilarity(String from, String to);
    public abstract void setSimilarities(double[][] sim);

    protected abstract void copyResponsibilies();

    protected abstract void computeResponsibilities();

    protected abstract void avgResponsibilies();

    protected abstract void copyAvailabilities();

    protected abstract void computeAvailabilities();

    protected abstract void avgAvailabilities();

    protected abstract void computeCenters();

    protected abstract void computeAssigments();

    protected abstract boolean checkConvergence();

    protected abstract int getClustersNumber();
    //   protected abstract void initObjectsNames();
}