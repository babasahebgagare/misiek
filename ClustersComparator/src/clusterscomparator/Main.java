package clusterscomparator;

import java.util.Map;
import readers.AffinityFormReader;
import readers.ClusteringReader;
import readers.MCLFormReader;
import readers.StandardReader;

/**
 *
 * @author misiek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 4) {
            String firstKind = String.valueOf(args[0]);
            String firstName = String.valueOf(args[1]);
            String secondKind = String.valueOf(args[2]);
            String secondName = String.valueOf(args[3]);

            ClusteringReader firstReader = createClusteringReader(firstKind);
            ClusteringReader secondReader = createClusteringReader(secondKind);
            firstReader.setFilename(firstName);
            Map<Integer, Integer> first = firstReader.readClustering();
            secondReader.setFilename(secondName);
            Map<Integer, Integer> second = secondReader.readClustering();
            JaccardComparator comparator = new JaccardComparator(first, second);
            comparator.setRange(first.keySet());
            comparator.calculateJaccards();
            System.out.println("JACCARD: " + comparator.getJaccard() + " LEFT JACCARD: " + comparator.getLeftJaccard() + " RIGHT JACCARD: " + comparator.getRightJaccard());
        } else {
            System.out.println("java -jar ClustersComparator.jar <kind: a s m> <first> <kind: a s m> <second>");
        }
    }

    private static ClusteringReader createClusteringReader(String kind) {
        if (kind.equals("s")) {
            return new StandardReader();
        } else if (kind.equals("a")) {
            return new AffinityFormReader();
        } else {
            return new MCLFormReader();
        }
    }
}
