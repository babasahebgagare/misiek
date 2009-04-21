package affinitymain;

/**
 *
 * @author misiek
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 7) {
            String filepath = String.valueOf(args[0]);
            String outpath = String.valueOf(args[1]);
            Double lambda = Double.valueOf(args[2]);
            Integer iterations = Integer.valueOf(args[3]);
            Integer convits = Integer.valueOf(args[4]);
            Double preferences = Double.valueOf(args[5]);
            String kind = String.valueOf(args[6]);

            RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, convits, preferences, kind);

            alg.setParemeters();
            alg.run();

        } else {
            System.out.println("Bad args count: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <convits> <preferences> <centers or clusters>");
        }
    }
}
