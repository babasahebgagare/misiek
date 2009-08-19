package ppine.ui.mips;

import java.util.Collection;
import java.util.Map;
import java.util.TreeSet;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ComplexesComparator {

    private Map<String, Complex> complexes;
    private Complex myComplex;

    public ComplexesComparator(Map<String, Complex> complexes, Complex myComplex) {
        this.complexes = complexes;
        this.myComplex = myComplex;
    }

    public void compare() {
        for (Complex mipsComplex : complexes.values()) {
            Double jaccard = compareComplexes(myComplex, mipsComplex);

        }
    }

    private Double compareComplexes(Complex myComplex, Complex mipsComplex) {
        Collection<String> sum = getSum(myComplex, mipsComplex);
        Collection<String> inter = getInter(myComplex, mipsComplex);
        Double jaccard = Double.valueOf((double) inter.size() / (double) sum.size());
        if (jaccard > 0.1) {
            String msg = mipsComplex.getName() + " & " + mipsComplex.getProteins().size() + " & " + myComplex.getProteins().size() + " & " + round(jaccard, 4) + " & " + mipsComplex.getName() + " & " + mipsComplex.getDesc() + " \\\\";
            // String msg = "COMPLEX: " + mipsComplex.getName() + " jaccard: " + jaccard + "MIPS SIZE: " + mipsComplex.getProteins().size() + " COMP SIZE: " + myComplex.getProteins().size();
            ComplexesLogger.log(msg);

        //    Collection<String> diff1 = getDiff(myComplex, mipsComplex);
        //    Collection<String> diff2 = getDiff(mipsComplex, myComplex);
        //    ComplexesLogger.log("MY COMPLEX - MIPS: ");
        //    printCollection(diff1);
        //    ComplexesLogger.log("MIPS - MY COMPLEX: ");
        //    printCollection(diff2);
        }

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
