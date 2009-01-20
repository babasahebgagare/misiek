package matrix;

public interface Matrix1DInterface<T> {

    //  public Matrix1D<T> Matrix1D(int N);

    // public Matrix1D<T> Matrix1D(T[] vector);
    public int size();

    public T get(int i);

    public T[] getVector();

    public T max();

    public int maxIndex();

    public void set(int i, T t);
}
