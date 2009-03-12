package algorithm.matrix;

import algorithm.abs.AffinityPropagationAlgorithm;
import algorithm.smart.Cluster;
import algorithm.smart.IterationData;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.DoubleMatrix1D;
import matrix.DoubleMatrix2D;
import matrix.IntegerMatrix1D;

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

    @Override
    public Map<String, String> doCluster() {
        int iterations = getIterations();
        double[] pom = new double[N];
        Map<String, String> res = new HashMap<String, String>();

        for (int iter = 0; iter < iterations; iter++) {
            rold = R.copy();

            AS = A.plus(S);
            //    System.out.println("AS" + AS);
            YI = AS.maxr();
            //     System.out.println("YI" + YI);

            for (int i = 0; i < N; i++) {
                int y = (int) YI.get(i, 0);
                AS.set(i, y, -inf);
            }
            YI2 = AS.maxr();
            //       System.out.println("YI2" + YI2);

            for (int i = 0; i < N; i++) {
                pom[i] = YI.get(i, 1);
            }
            DoubleMatrix2D Rep = new DoubleMatrix2D(N, pom);
            //         System.out.println("Rep" + Rep);
            R = S.minus(Rep);
            //          System.out.println("R" + R);

            for (int i = 0; i < N; i++) {
                R.set(i, (int) YI.get(i,0), S.get(i, (int) YI.get(i,0)) - YI2.get(i, 1));
            }

            R = R.mul(1 - getLambda()).plus(rold.mul(getLambda()));


            //          System.out.println("R" + R);

            aold = A.copy();
            rp = R.max(0);
            for (int i = 0; i < N; i++) {
                rp.set(i, i, R.get(i, i));
            }
            A = (new DoubleMatrix2D(N, rp.sum().getVector(0))).transpose().minus(rp);
            //        System.out.println("Rp" + Rp);
            //        System.out.println("sum" + (new DoubleMatrix2D(N, Rp.sum().getMatrix()[0])).transpose());

            //      System.out.println("A - " + A);

            dA = A.diag();

            //      System.out.println("dA - " + dA);

            A = A.min(0);
            for (int i = 0; i < N; i++) {
                A.set(i, i, dA.get(i));
            }

            A = A.mul((1 - getLambda())).plus(aold.mul(getLambda()));
            //       System.out.println("A");
            //          System.out.println(A);
            iteractionListener.actionPerformed(new ActionEvent(new IterationData(iter + 1, 0), 0, "ITERATION"));
        }

        //    System.out.println("S: " + S);
        E = R.plus(A);
        I = E.diag().findG(0);

        C = S.getColumns(I).maxrIndexes();
        try {
            //    System.out.println("C: " + C);
            C = tmp(C, I);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(MatrixPropagationAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
        }
        //    System.out.println("C: " + C);
        idx = idx(C, I);

        //    System.out.println("A: " + A);
        //   System.out.println("E: " + E);
        //   System.out.println("I: " + I);
        //  System.out.println("idx: " + idx);

        //     ArrayList res= new ArrayList<Integer>();
        for (int i = 0; i < idx.size(); i++) {
            res.put(String.valueOf(i), String.valueOf(idx.getValue(i)));
        }


        return res;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public IntegerMatrix1D idx(IntegerMatrix1D C, IntegerMatrix1D I) {

        IntegerMatrix1D res = new IntegerMatrix1D(C.size());

        for (int i = 0; i < C.size(); i++) {
            res.set(i, I.get(C.get(i).intValue()));
        }

        return res;
    }

    public IntegerMatrix1D tmp(IntegerMatrix1D C, IntegerMatrix1D I) throws CloneNotSupportedException {
        IntegerMatrix1D res = C.copy();
        for (int i = 0; i < I.size(); i++) {
            res.set(I.get(i), Integer.valueOf(i));
        }

        return res;
    }

    public void setSimilarities(double[][] similarities) {
        N = similarities.length;
        this.S = new DoubleMatrix2D(N, N, similarities);
    }

    @Override
    public Map<String, Cluster<String>> doClusterAssoc() {
        Map<String, String> map = doCluster();
        Map<String, Cluster<String>> res = new HashMap<String, Cluster<String>>();

        for (String examplar : map.values()) {
            Cluster<String> clust = new Cluster<String>(examplar);
            clust.add(examplar);
            res.put(examplar, clust);
        }

        for (String obj : map.keySet()) {
            String ex = map.get(obj);
            Cluster<String> clust = res.get(ex);
            clust.add(obj);
        }

        return res;
    }

    @Override
    public void setSimilarities(String x, String y, Double sim) {

        int i = Integer.valueOf(x);
        int j = Integer.valueOf(y);
        S.set(i, j, sim.doubleValue());
    }
}
