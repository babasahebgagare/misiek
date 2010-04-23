/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of affinity propagation
 * algorithm as Cytoscape plugin.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * APGraphClusteringPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */


package utils;

import java.util.Arrays;
import java.util.Vector;

public class MathStats {

    public static Double median(final Vector<Double> vector) {
        // Clone the input vector
        Double[] vectorCopy = new Double[vector.size()];
        int i = 0;
        for (Double el : vector) {
            vectorCopy[i] = Double.valueOf(el.doubleValue());
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
