package setscomparator;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author misiek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            String first = String.valueOf(args[0]);
            String second = String.valueOf(args[1]);
            Double jaccardIndex = Double.valueOf(0);
            SetsReader reader = new SetsReader();

            reader.setFilename(first);
            Collection<Integer> a = reader.loadSet();
            reader.setFilename(second);
            Collection<Integer> b = reader.loadSet();

            if (a.equals(b)) {
                System.out.println("IDENTICAL");
                jaccardIndex = Double.valueOf(1);
            } else {
                Collection<Integer> sum = new TreeSet<Integer>(a);
                Collection<Integer> inter = new TreeSet<Integer>(a);

                System.out.println("NOT IDENTICAL!!!");
                System.out.println("A SIZE: " + a.size());
                System.out.println("B SIZE: " + b.size());
                sum.addAll(b);
                inter.retainAll(b);
                jaccardIndex = Double.valueOf((double) inter.size() / (double) sum.size());
            }
            System.out.println("Jaccard index = " + jaccardIndex);
        } else {
            System.out.println("java -jar SetComparator.jar <first> <second>");
        }
    }
}
