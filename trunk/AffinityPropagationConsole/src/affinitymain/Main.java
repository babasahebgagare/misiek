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
        if (args.length == 6) {
            try {
                String filepath = String.valueOf(args[0]);
                String outpath = String.valueOf(args[1]);
                Double lambda = Double.valueOf(args[2]);
                Integer iterations = Integer.valueOf(args[3]);
                Double preferences = Double.valueOf(args[4]);
                String kind = String.valueOf(args[5]);

                RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, preferences, kind);

                alg.setParemeters();
                alg.run();
            } catch (Exception e) {
                System.out.println(e.getStackTrace()[0]);
                System.out.println("Error during running: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <preferences> <centers or clusters>");
            }
        } else {
            System.out.println("Bad args count: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <preferences> <centers or clusters>");
        }
    }
}
