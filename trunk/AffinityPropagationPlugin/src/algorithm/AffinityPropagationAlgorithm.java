package algorithm;

import matrix.DoubleMatrix1D;
import matrix.DoubleMatrix2D;
import matrix.IntegerMatrix1D;

public class AffinityPropagationAlgorithm extends AbstractClusterAlgorithm {

    private int N;
    private DoubleMatrix2D S;
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
    private double lambda = 0.5;
    private double inf = 1100000.0;

    @Override
    public String getShortName() {
        return "AP";
    }

    @Override
    public String getName() {
        return "Affinity Propagation";
    }

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
    public void doCluster() {

        double[] pom = new double[N];

        for (int iter = 0; iter < 100; iter++) {
            Rold = R.copy();

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
                R.set(i, (int) YI.getMatrix()[i][0], S.get(i, (int) YI.getMatrix()[i][0]) - YI2.get(i, 1));
            }
            R = R.mul(1 - lambda).plus(Rold.mul(lambda));

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
            A = A.mul((1 - lambda)).plus(Aold.mul(lambda));
        //       System.out.println("A");
        //          System.out.println(A);
        }

        E = R.plus(A);
        I = E.diag().findG(0);
        int K = I.size();


        System.out.println("A: " + A);
        System.out.println("E: " + E);
        System.out.println("I: " + I);
        DoubleMatrix2D CI = S.maxr();
        


        double[] c = new double[I.max()+1];
        for (int i = 0; i < I.size(); i++) {
            c[I.get(i)] = i;
        }

    }

    public DoubleMatrix2D getSimilarity() {
        return S;
    }

    public void setSimilarity(DoubleMatrix2D similarity) {
        this.S = similarity;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
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
}
