package matrix;

public class IntegerMatrix1D extends Matrix1D<Integer> {

    public IntegerMatrix1D(int N) {
        super(N);
        vector = new Integer[N];
        for (int i = 0; i < N; i++) {
            vector[i] = new Integer(0);
        }
    }

    public static IntegerMatrix1D range(int r) {
        IntegerMatrix1D res = new IntegerMatrix1D(r);
        res.vector = new Integer[r];
        for (int i = 0; i < r; i++) {
            res.vector[i] = new Integer(i);
        }
        return res;
    }

    @Override
    public IntegerMatrix1D clone() {
        IntegerMatrix1D res = new IntegerMatrix1D(this.N);
        for(int i=0; i<this.N; i++) {
            res.set(i, this.get(i));
        }

        return res;
    }
}
