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
    private static double v[][] = {{-1, -2, -1, -10}, {-3, -1, -120, -22}, {-10, -50, -3, -1}, {-1, -30, -4, -1}};

    public static void main(String[] args) {

        AffinityPropagationAlgorithm af = new AffinityPropagationAlgorithm();
        af.setN(4);
        af.setLambda(0.5);
        af.setSimilarities(v);
        af.init();
        af.doCluster();
    }
}
