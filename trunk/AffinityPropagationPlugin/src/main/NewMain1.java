/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algorithm.AffinityPropagationAlgorithm;
import matrix.DoubleMatrix1D;
import matrix.DoubleMatrix2D;

/**
 *
 * @author misiek
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    private static double v[][] = {{-10, - 5, - 5, - 8}, {-4, - 6, - 1, - 22}, {-4, - 5, - 10, - 40}, {-1, - 3, - 5, - 31}};

    public static void main(String[] args) {

        AffinityPropagationAlgorithm af = new AffinityPropagationAlgorithm();
        af.setN(4);
        af.setLambda(0.5);
        af.setSimilarity(new DoubleMatrix2D(4, 4, v));
        af.init();
        af.doCluster();
    }
}
