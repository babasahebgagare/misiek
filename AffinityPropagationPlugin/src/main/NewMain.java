package main;

import matrix.DoubleMatrix1D;

public class NewMain {

    private static double m[][] = {{16, 3, 2, 13}, {5, 10, 11, 8}, {9, 6, 7, 12}, {4, 15, 14, 1}};
    private static Double v[] = {new Double(16), new Double(3), new Double(2), new Double(13)};

    public static void main(String[] args) {
     //   DoubleMatrix2D A = new DoubleMatrix2D(4, 4, m);
    //    DoubleMatrix2D B = new DoubleMatrix2D(4, v);
        DoubleMatrix1D C = new DoubleMatrix1D(v);
        System.out.println(C.max().doubleValue());
      //  System.out.println(B);
    }
}
