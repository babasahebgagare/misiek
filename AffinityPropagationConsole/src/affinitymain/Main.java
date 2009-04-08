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
        if (args.length == 5) {
            try {
                String filepath = String.valueOf(args[0]);
                String outpath = String.valueOf(args[1]);
                Double lambda = Double.valueOf(args[2]);
                Integer iterations = Integer.valueOf(args[3]);
                Double preferences = Double.valueOf(args[4]);

                RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, preferences);

                alg.setParemeters();
                alg.run();
            } catch (Exception e) {
                System.out.println(e.getStackTrace()[0]);
                System.out.println("Error during running: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <preferences>");
            }
        } else {
            System.out.println("Bad args count: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <preferences>");
        }
    }
}
