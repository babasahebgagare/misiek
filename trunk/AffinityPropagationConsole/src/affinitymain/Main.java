/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package affinitymain;

/**
 *
 * @author misiek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            String filepath = String.valueOf(args[0]);
        } else {
            System.out.println("You have to specify filepath");
        }
    }
}
