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
            RunAlgorithm alg = new RunAlgorithm(filepath, 0.5, 10, -300);

            alg.setParemeters();
            alg.run();
        } else {
            System.out.println("You have to specify filepath");
        }
    }
}
