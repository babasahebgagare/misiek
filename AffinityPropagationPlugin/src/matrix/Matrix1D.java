package matrix;

public abstract class Matrix1D<T> implements Matrix1DInterface<T> {

    T[] vector;
    int N;

    public Matrix1D(int N) {
        this.N = N;
    }

    public Matrix1D(T[] vector) {
        this.N = vector.length;
        this.vector = vector;
    }

    public int size() {
        return N;
    }

    public T get(int i) {
        return vector[i];
    }

    public void set(int i, T t) {
        vector[i] = t;
    }

    public T max() {
        return this.vector[this.maxIndex()];
    }

    public int maxIndex() {
        int maxi = 0;
        for (int i = 0; i < N; i++) {
            if(((Comparable)(this.vector[i])).compareTo(this.vector[maxi]) == 1) {
                maxi = i;
            }
        }

        return maxi;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < N; i++) {
            res += vector[i].toString() + " ";
        }
        res += "\n";
        return res;
    }

}
