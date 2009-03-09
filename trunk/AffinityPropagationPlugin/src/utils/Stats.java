package utils;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class Stats {

    public static Double median(Vector<Double> vector) {
        // Clone the input vector
        Double[] vectorCopy = new Double[vector.size()];
        int i = 0;
        for (Double el : vector) {
            vectorCopy[i] = new Double(el.doubleValue());
            i++;
        }

        // sort it
        Arrays.sort(vectorCopy);

        // Get the median
        int mid = vectorCopy.length / 2;
        if (vectorCopy.length % 2 == 1) {
            return (vectorCopy[mid].doubleValue());
        }
        return ((vectorCopy[mid - 1].doubleValue() + vectorCopy[mid].doubleValue()) / 2);

    }
}
