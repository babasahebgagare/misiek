package matrix;

public class Matrix1D<T> implements Matrix1DInterface<T>, Cloneable {

    private T[] vector;
    private int N;

    public T[] getVector() {
        return vector;
    }
    
    @SuppressWarnings("unchecked")
	public void setVector(T[] v) {
        this.N = v.length;
        this.vector = (T[]) new Object[this.N];
        for(int i=0; i<this.size(); i++) {
        	this.vector[i] = v[i];        	
        }
	}


	public Matrix1D(int N) {
        this.N = N;
    }

    @SuppressWarnings("unchecked")
	public Matrix1D(T[] v) {
        this.N = v.length;
        this.vector = (T[]) new Object[this.N];
        for(int i=0; i<this.size(); i++) {
        	this.vector[i] = v[i];        	
        }
        
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
            int compareTo = ((Comparable) (this.getVector()[i])).compareTo(this.getVector()[maxi]);
			if (compareTo == 1) {
                maxi = i;
            }
        }

        return maxi;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < N; i++) {
            res.append(vector[i].toString());
            res.append(" ");
        }
        res.append("\n");
        return res.toString();
    }

    @Override
    public Matrix1D<T> clone() {
        Matrix1D<T> res = new Matrix1D<T>(this.size());
        for(int i=0; i<this.size(); i++) {
            res.set(i, this.get(i));
        }

        return res;
    }
}
