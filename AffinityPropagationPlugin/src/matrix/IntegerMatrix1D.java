package matrix;

public class IntegerMatrix1D extends Matrix1D<Integer> {

    public IntegerMatrix1D(int N) {
        super(N);
        this.setVector(new Integer[N]);
        for (int i = 0; i < N; i++) {
            getVector()[i] = Integer.valueOf(0);
        }
    }

	public static IntegerMatrix1D range(int r) {
        IntegerMatrix1D res = new IntegerMatrix1D(r);
        res.setVector(new Integer[r]);
        for (int i = 0; i < r; i++) {
            res.getVector()[i] = Integer.valueOf(i);
        }
        return res;
    }

}
