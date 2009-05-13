package affinitymain;

import java.util.Map;

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
            RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, convits, preferences, kind, false);

            alg.setParemeters();
            alg.run();
        } else {
            System.out.println("Bad args count: java -jar AffinityPropagation.jar <input> <ouput> <lambda> <iterations> <convits> <preferences> <centers or clusters>");
        }
        Map<String, String> map = CommandLineParser.parseTokens(args);

        String filepath = map.get("in");
        String outpath = map.get("out");
        Double lambda = Double.valueOf(map.get("lam"));
        Integer iterations = Integer.valueOf(map.get("it"));
        Integer convits = Integer.valueOf(map.get("con"));
        Double preferences = Double.valueOf(map.get("p"));
        String kind = String.valueOf(map.get("kind"));

        RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, convits, preferences, kind, false);

        alg.setParemeters();
        alg.run();

    }
}
