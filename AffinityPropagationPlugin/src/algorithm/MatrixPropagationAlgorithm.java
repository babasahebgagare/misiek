package algorithm;

import matrix.DoubleMatrix1D;
import matrix.DoubleMatrix2D;
import matrix.IntegerMatrix1D;

public class MatrixPropagationAlgorithm extends AffinityPropagationAlgorithm {

    private int N;
    private IntegerMatrix1D idx;
    private IntegerMatrix1D C;
    private DoubleMatrix2D A;
    private DoubleMatrix2D E;
    private DoubleMatrix1D dA;
    private IntegerMatrix1D I;
    private DoubleMatrix2D AS;
    private DoubleMatrix2D R;
    private DoubleMatrix2D Rp;
    private DoubleMatrix2D Aold;
    private DoubleMatrix2D Rold;
    private DoubleMatrix2D YI;
    private DoubleMatrix2D YI2;
    private double inf = 1100000.0;

    @Override
    public void init() {
        A = new DoubleMatrix2D(N, N, 0);
        R = new DoubleMatrix2D(N, N, 0);
    }

    @Override
    public void halt() {
        // TODO
    }

    @Override
    public Integer[] doCluster() {

        double[] pom = new double[N];

        for (int iter = 0; iter < 10; iter++) {
            System.out.println("iteration: " + iter);
            System.out.println("Rold: " + R.toString());
            Rold = R.copy();

            AS = A.plus(getS());
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
            R = getS().minus(Rep);

            //          System.out.println("R" + R);

            for (int i = 0; i < N; i++) {
                R.set(i, (int) YI.getMatrix()[i][0], getS().get(i, (int) YI.getMatrix()[i][0]) - YI2.get(i, 1));
            }
            System.out.println("Rnew: " + R.toString());
            R = R.mul(1 - getLambda()).plus(Rold.mul(getLambda()));
            System.out.println("Ravg: " + R.toString());

            //          System.out.println("R" + R);

            Aold = A.clone();
            Rp = R.max(0);
            for (int i = 0; i < N; i++) {
                Rp.set(i, i, R.get(i, i));
            }
            A = (new DoubleMatrix2D(N, Rp.sum().getMatrix()[0])).transpose().minus(Rp);
            //        System.out.println("Rp" + Rp);
            //        System.out.println("sum" + (new DoubleMatrix2D(N, Rp.sum().getMatrix()[0])).transpose());

            //      System.out.println("A - " + A);

            dA = A.diag();

            //      System.out.println("dA - " + dA);

            A = A.min(0);
            for (int i = 0; i < N; i++) {
                A.getMatrix()[i][i] = dA.get(i);
            }
            A = A.mul((1 - getLambda())).plus(Aold.mul(getLambda()));
        //       System.out.println("A");
        //          System.out.println(A);
        }

        //    System.out.println("S: " + S);
        E = R.plus(A);
        I = E.diag().findG(0);
        int K = I.size();

        C = getS().getColumns(I).maxrIndexes();

        //    System.out.println("C: " + C);
        C = tmp(C, I);
        //    System.out.println("C: " + C);
        idx = idx(C, I);

        //    System.out.println("A: " + A);
        //   System.out.println("E: " + E);
        //   System.out.println("I: " + I);
        //  System.out.println("idx: " + idx);

        //     ArrayList res= new ArrayList<Integer>();


        return idx.getVector();
    }

    public DoubleMatrix2D getA() {
        return A;
    }

    public void setA(DoubleMatrix2D A) {
        this.A = A;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public DoubleMatrix2D getR() {
        return R;
    }

    public void setR(DoubleMatrix2D R) {
        this.R = R;
    }

    public IntegerMatrix1D idx(IntegerMatrix1D C, IntegerMatrix1D I) {

        IntegerMatrix1D res = new IntegerMatrix1D(C.size());

        for (int i = 0; i < C.size(); i++) {
            res.set(i, I.get(C.get(i).intValue()));
        }

        return res;
    }

    public IntegerMatrix1D tmp(IntegerMatrix1D C, IntegerMatrix1D I) {
        IntegerMatrix1D res = C.clone();
        for (int i = 0; i < I.size(); i++) {
            res.set(I.get(i), new Integer(i));
        }

        return res;
    }
}

