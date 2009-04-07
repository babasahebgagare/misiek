package prime;

import algorithm.smart.Cluster;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeAlgorithm {

    private PrimeGraph graph;
    private PriorityQueue<PrimeEdge> edges;
    private Collection<String> sources;

    public PrimeAlgorithm(PrimeGraph graph, Collection<String> sources) {
        this.graph = graph;
        this.sources = sources;
        this.edges = new PriorityQueue<PrimeEdge>();
        init(sources);
    }

    public Map<String, Cluster<String>> run() {
        Map<String, Cluster<String>> ret = new HashMap<String, Cluster<String>>();

        while (edges.size() > 0) {
            PrimeEdge minEdge = edges.poll();
            //System.out.println(minEdge.getWeight());

            String nodeFromStr = minEdge.getFrom();
            String nodeToStr = minEdge.getTo();
            Double weight = minEdge.getWeight();

            PrimeNode nodeFrom = graph.getNode(nodeFromStr);
            PrimeNode nodeTo = graph.getNode(nodeToStr);

            if (nodeTo.getDistance() == null || nodeTo.getDistance() > nodeFrom.getDistance() + weight) {
                nodeTo.setDistance(nodeFrom.getDistance() + weight);
                nodeTo.setSourceName(nodeFrom.getSourceName());

                for (Entry<String, Double> entryEdge : nodeTo.getEdges().entrySet()) {
                    PrimeEdge edge = new PrimeEdge(nodeFromStr, entryEdge.getKey(), entryEdge.getValue());
                    edges.add(edge);
                }
            }
        }

        for (String source : sources) {
            Cluster<String> clust = new Cluster<String>(source);
            clust.add(source);
            ret.put(source, clust);
        //  current.addNode(node);
        }

        for (PrimeNode node : graph.getNodes()) {
            if (node.getSourceName() != null) {
                if (!sources.contains(node.getName())) {
                    Cluster<String> cluster = ret.get(node.getSourceName());
                    cluster.add(node.getName());
                }
            }
        }

        return ret;
    }

    private void init(Collection<String> sources) {
        System.out.println("CENTERS: " + sources.size());
        for (String source : sources) {
            PrimeNode node = graph.getNode(source);
            node.setDistance(Double.valueOf(0));
            node.setSourceName(source);
            //        current.addNode(node);
            for (Entry<String, Double> entryEdge : node.getEdges().entrySet()) {
                PrimeEdge edge = new PrimeEdge(source, entryEdge.getKey(), entryEdge.getValue());
                edges.add(edge);
            }
        }
    }
}
