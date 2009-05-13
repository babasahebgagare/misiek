package affinitymain;

import algorithm.abs.AffinityPropagationAlgorithm.AffinityConnectingMethod;
import algorithm.abs.AffinityPropagationAlgorithm.AffinityGraphMode;
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

        if (args.length < 2) {
            showHelp();
        }

        Map<String, String> map = CommandLineParser.parseTokens(args);

        String filepath = getFilepath(map);
        String outpath = getFoutput(map);
        Double lambda = getLambda(map);
        if (lambda == null) {
            showHelp();
            return;
        }
        Integer iterations = getIterations(map);
        if (iterations == null) {
            showHelp();
            return;
        }
        Integer convits = getConvits(map);
        Double preferences = getPreferences(map);
        String kind = getOutputKind(map);
        AffinityGraphMode graphMode = getGraphMode(map);
        AffinityConnectingMethod connMode = getConnMode(map);
        Integer steps = getSteps(map);

        RunAlgorithm alg = new RunAlgorithm(filepath, outpath, lambda, iterations, convits, preferences, kind, false);
        alg.setConnMode(connMode);
        alg.setGraphMode(graphMode);
        alg.setSteps(steps);

        alg.setParemeters();
        alg.run();

    }

    private static AffinityConnectingMethod getConnMode(Map<String, String> map) {
        String modeStr = map.get("conn");
        if (modeStr == null) {
            return AffinityConnectingMethod.ORIGINAL;
        } else {
            if (modeStr.equals("org")) {
                return AffinityConnectingMethod.ORIGINAL;
            } else {
                return AffinityConnectingMethod.PRIME_ALG;
            }
        }
    }

    private static String getOutputKind(Map<String, String> map) {
        String kind = map.get("kind");
        if (kind == null) {
            return "clusters";
        } else {
            if (kind.equals("centers")) {
                return kind;
            } else {
                return "clusters";
            }
        }
    }

    private static Double getPreferences(Map<String, String> map) {
        String lamStr = map.get("p");
        if (lamStr == null) {
            System.out.println("You have to set preferences (p)!");
            return null;
        } else {
            try {
                return Double.valueOf(lamStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    private static Double getLambda(Map<String, String> map) {
        String lamStr = map.get("lam");
        if (lamStr == null) {
            System.out.println("You have to set lambda (lam)!");
            return null;
        } else {
            try {
                return Double.valueOf(lamStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    private static Integer getIterations(Map<String, String> map) {
        try {
            return Integer.valueOf(map.get("it"));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Integer getConvits(Map<String, String> map) {
        try {
            return Integer.valueOf(map.get("con"));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static String getFilepath(Map<String, String> map) {
        return map.get("in");
    }

    private static String getFoutput(Map<String, String> map) {
        return map.get("out");
    }

    private static AffinityGraphMode getGraphMode(Map<String, String> map) {
        String mode = map.get("dir");
        if (mode == null || mode.equals("true")) {
            System.out.println("dir");
            return AffinityGraphMode.DIRECTED;
        } else {
            System.out.println("undir");
            return AffinityGraphMode.UNDIRECTED;
        }
    }

    private static Integer getSteps(Map<String, String> map) {
        String depthStr = map.get("dep");
        if (depthStr == null) {
            return null;
        } else {
            try {
                return Integer.valueOf(depthStr);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    private static void showHelp() {
        System.out.println("Parameters: [parameterName=parameterValue]*");
        System.out.println("in: input similarities filepath");
        System.out.println("lam: double lambda");
        System.out.println("p: double preferences");
        System.out.println("it: integer max iterations");
        System.out.println("con*: integer convits");
        System.out.println("kind*: kind of output: (clusters / centers)");
        System.out.println("conn*: connecting mode II faze (org / prime)");
        System.out.println("dir*: graph directed edges (true / false)");
        System.out.println("* parameters are optional");
    }
}
