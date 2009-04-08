/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

            Collection<Integer> a = new TreeSet<Integer>();
            Collection<Integer> b = new TreeSet<Integer>();

            loadSet(first, a);
            loadSet(second, b);

            if (a.equals(b)) {
                System.out.println("IDENTICAL");
            } else {
                Collection<Integer> atmp = new TreeSet<Integer>(a);
                Collection<Integer> btmp = new TreeSet<Integer>(b);

                System.out.println("NOT IDENTICAL!!!");
                System.out.println("A SIZE: " + a.size());
                System.out.println("B SIZE: " + b.size());
                atmp.removeAll(btmp);
                System.out.println("A - B SIZE: " + atmp.size());
                b.removeAll(a);
                System.out.println("B - A SIZE: " + b.size());
            }

        } else {
            System.out.println("java -jar SetComparator.jar <first> <second>");
        }
    }

    private static void loadSet(String filename, Collection<Integer> set) {
        Scanner scanner = null;
        try {

            File inputSim = new File(filename);
            scanner = new Scanner(inputSim);

            while (scanner.hasNextInt()) {
                Integer val = Integer.valueOf(scanner.nextInt());
                set.add(val);
            }
        } catch (IOException ex) {
            System.out.println("ERROR");
        } finally {
            scanner.close();
        }
    }
}
