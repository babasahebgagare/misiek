/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package setscomparator;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        BufferedReader br = null;
        try {

            File inputSim = new File(filename);
            fis = new FileInputStream(inputSim);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            while (br.ready()) {
                String line = br.readLine();
                Integer val = Integer.valueOf(line);
                set.add(val);
            }
        } catch (IOException ex) {
            System.out.println("ERROR");
        } finally {
            try {
                br.close();
                dis.close();
                bis.close();
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
