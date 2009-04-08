package algorithm.abs;

import algorithm.smart.Cluster;
import algorithm.smart.IterationData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm<Integer> {

    private double lambda;
    private int iterations;
    protected int connectingMode;
    protected boolean convergence;
    protected Integer convits = null;
    protected ActionListener iteractionListenerOrNull = null;
    protected Map<Integer, Cluster<Integer>> assigments;
    public final static int WEIGHET_MODE = 0;
    public final static int UNWEIGHET_MODE = 1;

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

    public void setConnectingMode(int connectingMode) {
        this.connectingMode = connectingMode;
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
    public Map<Integer, Integer> doCluster() {
        final Map<Integer, Cluster<Integer>> help = doClusterAssoc();
        final Map<Integer, Integer> res = new HashMap<Integer, Integer>();

        for (Entry<Integer, Cluster<Integer>> entry : help.entrySet()) {
            for (Integer obj : entry.getValue().getElements()) {
                res.put(obj, entry.getKey());
            }
        }

        return res;
    }

    public Map<Integer, Cluster<Integer>> doClusterAssoc() {
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
    public abstract void setConstPreferences(Double preferences);

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

    protected Double computeWeight(double sim, int connecingMode) {
        if (connecingMode == AffinityPropagationAlgorithm.WEIGHET_MODE) {
            return Math.exp(-Math.exp(sim));
        } else {
            return Double.valueOf(1);
        }
    }
}