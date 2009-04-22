package algorithm.abs;

import algorithm.smart.IterationData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import prime.PrimeAlgorithm;
import prime.PrimeGraph;

public abstract class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm<Integer> {

    private double lambda;
    private int iterations;
    protected AffinityConnectingMethod connectingMode;
    protected int notConverged;
    protected Integer convits = null;
    protected ActionListener iteractionListenerOrNull = null;
    protected Map<Integer, Cluster<Integer>> assigments;
    protected Map<Integer, ConvitsVector> convitsVectors = new TreeMap<Integer, ConvitsVector>();
    private Collection<Integer> refined = null;

    private void refineCenters() {
        Collection<Integer> refinedCenters = new TreeSet<Integer>();

        for (Cluster<Integer> cluster : assigments.values()) {
            Integer maxid = cluster.getName();
            Integer maxlevel = Integer.valueOf(0);
            Double maxsum = null;
            for (Integer curr : cluster.getElements()) {
                Double sum = Double.valueOf(0);
                Integer level = Integer.valueOf(0);
                for (Integer other : cluster.getElements()) {
                    Double simOrNull = tryGetSimilarity(other, curr);
                    if (simOrNull != null) {
                        sum += simOrNull;
                        level++;
                    }
                }
                if (maxsum == null || level > maxlevel) {
                    maxsum = sum;
                    maxid = curr;
                    maxlevel = level;
                } else if (level.equals(maxlevel) && sum >= maxsum) {
                    if (sum.equals(maxsum)) {
                        maxid = Math.min(maxid.intValue(), curr.intValue());
                    } else {
                        maxid = curr;
                    }
                    maxlevel = level;
                    maxsum = sum;
                }
            }
            refinedCenters.add(maxid);
        }
        refined = refinedCenters;
    }

    public enum AffinityConnectingMethod {

        PRIME_ALG, FLOYD_ALG, ORIGINAL
    }

    private Map<Integer, Cluster<Integer>> computeFloydAssigments(Collection<Integer> examplars, Collection<Integer> centers) {
        return null;
    }

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

    public void setConnectingMode(AffinityConnectingMethod connectingMode) {
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
        if (help != null) {
            final Map<Integer, Integer> res = new HashMap<Integer, Integer>();

            for (Entry<Integer, Cluster<Integer>> entry : help.entrySet()) {
                for (Integer obj : entry.getValue().getElements()) {
                    res.put(obj, entry.getKey());
                }
            }

            return res;
        } else {
            return null;
        }
    }

    public Map<Integer, Cluster<Integer>> doClusterAssoc() {
        int iters = getIterations();
        if (iteractionListenerOrNull != null) {
            iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(1, 0), 0, "ITERATION"));
        }
        initConvergence();

        for (int iteration = 0; iteration < iters; iteration++) {

            copyResponsibilies();
            computeResponsibilities();
            avgResponsibilies();

            copyAvailabilities();
            computeAvailabilities();
            avgAvailabilities();

            if (iteration + 1 != iterations && iteractionListenerOrNull != null) {
                computeCenters();
                calculateCovergence();
                notConverged = checkConvergence();
                iteractionListenerOrNull.actionPerformed(new ActionEvent(new IterationData(iteration + 2, getClustersNumber()), 0, "ITERATION")); //TODO
            }

            if (notConverged == 0) {
                break;
            }
        }

        computeCenters();
        if (getClustersNumber() != 0) {
            computeAssigments();
            refineCenters();
            computeAssigments();
            return assigments;
        } else {
            return null;
        }
    }

    protected void computeAssigments() {
        Collection<Integer> examplars = getAllExamplars();
        Collection<Integer> centers;
        if (refined == null) {
            centers = getCenters();
        } else {
            centers = refined;
        }
        if (centers.size() == 0) {
            assigments = null;
            return;
        }

        if (connectingMode == AffinityConnectingMethod.PRIME_ALG) {

            assigments = computePrimeAssigments(examplars, centers);
        } else if (connectingMode == AffinityConnectingMethod.ORIGINAL) {
            assigments = computeOriginalAssigments(examplars, centers);
        } else if (connectingMode == AffinityConnectingMethod.FLOYD_ALG) {
            assigments = computeFloydAssigments(examplars, centers);
        } else {
            assigments = null;
        }
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

    protected abstract void calculateCovergence();

    protected abstract void initConvergence();

    protected int checkConvergence() {
        int not = 0;
        if (convits == null) {
            not = -1;
        } else {

            for (ConvitsVector vec : convitsVectors.values()) {
                if (vec.checkConvits() == false) {
                    not++;
                }
            }
        }
        return not;
    }

    protected abstract int getClustersNumber();
    //   protected abstract void initObjectsNames();

    protected Double computeWeight(double sim) {

        return -sim;

    }

    protected abstract Collection<Integer> getCenters();

    protected abstract Collection<Integer> getAllExamplars();

    private Map<Integer, Cluster<Integer>> computeOriginalAssigments(Collection<Integer> examplars, Collection<Integer> centers) {
        Map<Integer, Cluster<Integer>> ret = new TreeMap<Integer, Cluster<Integer>>();
        Map<Integer, Integer> clustered = new TreeMap<Integer, Integer>();
        Collection<Integer> unclustered = new TreeSet<Integer>(examplars);
        Collection<Integer> unclusteredHelp = new TreeSet<Integer>(examplars);

        for (Integer center : centers) {
            //   System.out.println("CENTER: " + center);
            Cluster<Integer> clust = new Cluster<Integer>(center);
            clust.add(center);
            ret.put(center, clust);
            clustered.put(center, center);
            unclustered.remove(center);
        }

        while (unclustered.size() != unclusteredHelp.size()) {
            unclusteredHelp = new TreeSet<Integer>(unclustered);
            Map<Integer, Integer> clusteredHelp = new TreeMap<Integer, Integer>(clustered);
            //  System.out.println("CLUSTERED: " + clustered.size());
            for (Integer examplar : unclusteredHelp) {

                Integer maxidOrNull = null;
                Double maxOrNull = null;

                for (final Entry<Integer, Integer> clusteredEx : clusteredHelp.entrySet()) {

                    Double simOrNull = tryGetSimilarity(examplar, clusteredEx.getKey());
                    if (simOrNull != null) {
                        if (maxOrNull != null) {
                            if (simOrNull > maxOrNull) {
                                maxOrNull = simOrNull;
                                maxidOrNull = clusteredEx.getValue();
                            }
                        } else {
                            maxOrNull = simOrNull;
                            maxidOrNull =
                                    clusteredEx.getValue();
                        }
                    }
                }
                if (maxidOrNull != null) {
                    Cluster<Integer> cluster = ret.get(maxidOrNull);
                    cluster.add(examplar);
                    clustered.put(examplar, maxidOrNull);
                    unclustered.remove(examplar);
                }
            }
        }

        return ret;
    }

    private Map<Integer, Cluster<Integer>> computePrimeAssigments(Collection<Integer> examplars, Collection<Integer> centers) {
        PrimeGraph graph = new PrimeGraph();

        for (Integer examplar : examplars) {
            graph.addNode(examplar);
        }

        for (Integer exFrom : examplars) {
            for (Integer exTo : examplars) {
                if (!exFrom.equals(exTo)) {
                    Double simOrNull = tryGetSimilarity(exFrom, exTo);
                    if (simOrNull != null) {
                        Double weight = computeWeight(simOrNull);
                        graph.addEdge(exFrom, exTo, weight);
                    }
                }
            }
        }

        PrimeAlgorithm prime = new PrimeAlgorithm(graph, centers);

        return prime.run();

    }
}