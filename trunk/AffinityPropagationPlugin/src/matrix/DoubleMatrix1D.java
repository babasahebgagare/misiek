package matrix;

public class DoubleMatrix1D extends Matrix1D<Double> implements DoubleMatrix1DInterface {

    public DoubleMatrix1D(Double[] vector) {
        super(vector.length);
        this.vector = new Double[N];
        for (int i = 0; i < N; i++) {
            this.vector[i] = vector[i];
        }
    }

    public DoubleMatrix1D(int N) {
        super(N);
    }

    public DoubleMatrix1D(int N, double t) {
        super(N);
        this.vector = new Double[N];
        for (int i = 0; i < N; i++) {
            this.vector[i] = new Double(t);
        }
    }

    public void set(int i, double t) {
        super.set(i, new Double(t));
    }

    public IntegerMatrix1D findG(double x) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (vector[i].doubleValue() > x) {
                count++;
            }
        }
        IntegerMatrix1D res = new IntegerMatrix1D(count);
        count = 0;
        for (int i = 0; i < N; i++) {
            if (vector[i].doubleValue() > x) {
                res.set(count, new Integer(i));
                count++;
            }
        }
        return res;
    }
}
