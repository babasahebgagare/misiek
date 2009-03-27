package algorithm.matrix;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.smart.Examplar;
import algorithm.smart.SiblingData;
import java.util.Collection;
import java.util.HashSet;
import matrix.DoubleMatrix1D;
import matrix.DoubleMatrix2D;
import matrix.IntegerMatrix1D;
import prime.PrimeAlgorithm;
import prime.PrimeGraph;

public class MatrixPropagationAlgorithm extends AffinityPropagationAlgorithm<String> {

    private int N;
    private IntegerMatrix1D idx;
    private IntegerMatrix1D C;
    private DoubleMatrix2D A;
    private DoubleMatrix2D E;
    private DoubleMatrix1D dA;
    private IntegerMatrix1D I;
    private DoubleMatrix2D AS;
    private DoubleMatrix2D R;
    private DoubleMatrix2D rp;
    private DoubleMatrix2D aold;
    private DoubleMatrix2D rold;
    private DoubleMatrix2D YI;
    private DoubleMatrix2D YI2;
    private DoubleMatrix2D S;
    private double inf = 1100000.0;

    @Override
    public void init() {
        A = new DoubleMatrix2D(N, N, 0);
        R = new DoubleMatrix2D(N, N, 0);
        S = new DoubleMatrix2D(N, N, -inf);
    }

    @Override
    public void halt() {
        // TODO
    }
    /*
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
    }*/

    public int getN() {
        return N;
    }

    public void setN(final int N) {
        this.N = N;
    }

    public IntegerMatrix1D idx(final IntegerMatrix1D C, final IntegerMatrix1D I) {

        IntegerMatrix1D res = new IntegerMatrix1D(C.size());

        for (int i = 0; i < C.size(); i++) {
            res.set(i, I.get(C.get(i).intValue()));
        }

        return res;
    }

    public IntegerMatrix1D tmp(final IntegerMatrix1D C, final IntegerMatrix1D I) {
        IntegerMatrix1D res = C.copy();
        for (int i = 0; i < I.size(); i++) {
            res.set(I.get(i), Integer.valueOf(i));
        }

        return res;
    }

    public void setSimilarities(final double[][] similarities) {
        N = similarities.length;
        this.S = new DoubleMatrix2D(N, N, similarities);
    }

    @Override
    public void setSimilarities(final String x, final String y, final Double sim) {

        int i = Integer.valueOf(x);
        int j = Integer.valueOf(y);
        S.set(i, j, sim.doubleValue());
    }

    @Override
    protected void copyResponsibilies() {
        rold = R.copy();
    }

    @Override
    protected void computeResponsibilities() {
        double[] pom = new double[N];
        AS = A.plus(S);
        YI = AS.maxr();

        for (int i = 0; i < N; i++) {
            int y = (int) YI.get(i, 0);
            AS.set(i, y, -inf);
        }
        YI2 = AS.maxr();

        for (int i = 0; i < N; i++) {
            pom[i] = YI.get(i, 1);
        }
        DoubleMatrix2D Rep = new DoubleMatrix2D(N, pom);
        R = S.minus(Rep);

        for (int i = 0; i < N; i++) {
            R.set(i, (int) YI.get(i, 0), S.get(i, (int) YI.get(i, 0)) - YI2.get(i, 1));
        }

    }

    @Override
    protected void avgResponsibilies() {
        R = R.mul(1 - getLambda()).plus(rold.mul(getLambda()));
    }

    @Override
    protected void copyAvailabilities() {
        aold = A.copy();
    }

    @Override
    protected void computeAvailabilities() {
        rp = R.max(0);
        for (int i = 0; i < N; i++) {
            rp.set(i, i, R.get(i, i));
        }
        A = (new DoubleMatrix2D(N, rp.sum().getVector(0))).transpose().minus(rp);
        dA = A.diag();

        A = A.min(0);
        for (int i = 0; i < N; i++) {
            A.set(i, i, dA.get(i));
        }
    }

    @Override
    protected void avgAvailabilities() {
        A = A.mul((1 - getLambda())).plus(aold.mul(getLambda()));
    }

    @Override
    protected void computeCenters() {
        //   centers = new HashSet<String>();
        E = R.plus(A);
        I = E.diag().findG(0);

    //   for (int i = 0; i < I.size(); i++) {
    //       Integer center = I.get(i);
    //       centers.add(String.valueOf(center));
    //   }
    }
    /*
    @Override
    protected void computeAssigments() {
    for (int i = 0; i < idx.size(); i++) {
    Integer examplar = Integer.valueOf(i);
    Integer center = idx.getValue(i);
    if (S.get(examplar.intValue(), center.intValue()) > -inf) {
    res.put(String.valueOf(i), String.valueOf(idx.getValue(i)));
    }
    }

    }
     */

    @Override
    protected void computeAssigments() {
        if (I.size() == 0) {
            return;
        }
        Collection<String> centers = new HashSet<String>();
        PrimeGraph graph = new PrimeGraph();

        for (int i = 0; i < I.size(); i++) {
            String center = String.valueOf(I.get(i));
            centers.add(center);
        }

        for (int i = 0; i < N; i++) {
            String ex = String.valueOf(i);
            graph.addNode(ex);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j && S.get(i, j) > -inf) {
                    String from = String.valueOf(i);
                    String to = String.valueOf(j);
                    graph.addEdge(from, to, Double.valueOf(S.get(i, j)));
                }
            }
        }

        PrimeAlgorithm prime = new PrimeAlgorithm(graph, centers);

        assigments = prime.run();

    /*
    if (I.size() == 0) {
    return;
    }
    C = S.getColumns(I).maxrIndexes();
    C = tmp(C, I);
    idx = idx(C, I);

    Map<String, Cluster<String>> res = new HashMap<String, Cluster<String>>();

    for (int i = 0; i < I.size(); i++) {
    String clusterName = String.valueOf(I.get(i));
    Cluster<String> clust = new Cluster<String>(clusterName);
    clust.add(clusterName);
    res.put(clusterName, clust);
    }

    for (int i = 0; i < idx.size(); i++) {

    String examplar = String.valueOf(i);
    String center = String.valueOf(idx.getValue(i));
    if (!res.containsKey(examplar)) {
    if (S.get(i, idx.get(i).intValue()) > -inf) {
    Cluster<String> cluster = res.get(center);
    cluster.add(examplar);
    }
    }
    }
    assigments = res;*/

    }

    @Override
    protected boolean checkConvergence() {
        return false;
    }

    @Override
    protected int getClustersNumber() {
        return I.size();
    }
    /*
    @Override
    protected void initObjectsNames() {
    for (int i = 0; i < N; i++) {
    String ex = String.valueOf(i);
    objects.add(ex);
    }
    }

    @Override
    protected Double trygetSimilarity(String from, String to) {
    Integer fromint = Integer.valueOf(from);
    Integer toint = Integer.valueOf(to);
    return S.get(fromint.intValue(), toint.intValue());
    }*/
}
