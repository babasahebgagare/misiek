/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package ppine.ui.mips;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

public class ComplexesComparator {

    private Map<String, Complex> complexes;
    private Complex myComplex;

    public ComplexesComparator(Map<String, Complex> complexes, Complex myComplex) {
        this.complexes = complexes;
        this.myComplex = myComplex;
    }

    public void compare() {
        double maxJaccard = 0;
        Complex bestMipsComplex = null;
        for (Complex mipsComplex : complexes.values()) {
            if (myComplex.size() > 1) {
                Double jaccard = compareComplexes(myComplex, mipsComplex);
                if (jaccard > maxJaccard) {
                    maxJaccard = jaccard;
                    bestMipsComplex = mipsComplex;
                }
            }
            //  System.out.println("Jaccard: " + jaccard + " " + mipsComplex.getName() + " " + mipsComplex.getProteins().size() + " " + myComplex.getProteins().size());
        }
        if (bestMipsComplex != null) {
            Collection<String> inter = getInter(bestMipsComplex, myComplex);
            String msg = bestMipsComplex.getName() + "\t" + bestMipsComplex.getProteins().size() + "\t" + myComplex.getProteins().size() + "\t" + inter.size() + "\t" + round(maxJaccard, 4);
            // String msg = "COMPLEX: " + mipsComplex.getName() + " jaccard: " + jaccard + "MIPS SIZE: " + mipsComplex.getProteins().size() + " COMP SIZE: " + myComplex.getProteins().size();
            ComplexesLogger.log(msg);
            System.out.println(msg);
        }

    }

    /* public void compare() {
    for (Complex mipsComplex : complexes.values()) {
    Double jaccard = compareComplexes(myComplex, mipsComplex);
    System.out.println("Jaccard: " + jaccard + " " + mipsComplex.getName() + " " + mipsComplex.getProteins().size() + " " + myComplex.getProteins().size());
    }
    }*/
    private Double compareComplexes(Complex myComplex, Complex mipsComplex) {
        Collection<String> sum = getSum(myComplex, mipsComplex);
        Collection<String> inter = getInter(myComplex, mipsComplex);
        Double jaccard = Double.valueOf((double) inter.size() / (double) sum.size());
        //  if (jaccard > 0.1) {
        //     String msg = mipsComplex.getName() + " & " + mipsComplex.getProteins().size() + " & " + myComplex.getProteins().size() + " & " + round(jaccard, 4) + " & " + mipsComplex.getName() + " & " + mipsComplex.getDesc() + " \\\\";
        // String msg = "COMPLEX: " + mipsComplex.getName() + " jaccard: " + jaccard + "MIPS SIZE: " + mipsComplex.getProteins().size() + " COMP SIZE: " + myComplex.getProteins().size();
        //     ComplexesLogger.log(msg);

        //    Collection<String> diff1 = getDiff(myComplex, mipsComplex);
        //    Collection<String> diff2 = getDiff(mipsComplex, myComplex);
        //    ComplexesLogger.log("MY COMPLEX - MIPS: ");
        //    printCollection(diff1);
        //    ComplexesLogger.log("MIPS - MY COMPLEX: ");
        //    printCollection(diff2);
//        }

        return jaccard;
    }

    public static double round(double val, int places) {
        long factor = (long) Math.pow(10, places);
        val = val * factor;
        long tmp = Math.round(val);
        return (double) tmp / factor;
    }

    private Collection<String> getDiff(Complex myComplex, Complex mipsComplex) {
        Collection<String> ret = new TreeSet<String>(myComplex.getProteins());
        ret.removeAll(mipsComplex.getProteins());
        return ret;
    }

    private Collection<String> getInter(Complex myComplex, Complex mipsComplex) {
        Collection<String> myComplexCollection = myComplex.getProteins();
        Collection<String> mipsComplexCollection = mipsComplex.getProteins();
        Collection<String> ret = new TreeSet<String>(myComplexCollection);
        ret.retainAll(mipsComplexCollection);
        return ret;
    }

    private Collection<String> getSum(Complex myComplex, Complex mipsComplex) {
        Collection<String> myComplexCollection = myComplex.getProteins();
        Collection<String> mipsComplexCollection = mipsComplex.getProteins();
        Collection<String> ret = new TreeSet<String>(myComplexCollection);
        ret.addAll(mipsComplexCollection);
        return ret;
    }

    private void printCollection(Collection<String> collection) {
        for (String name : collection) {
            ComplexesLogger.log(name);
        }
    }
}
