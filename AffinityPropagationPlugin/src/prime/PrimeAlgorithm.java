package prime;

import algorithm.abs.Cluster;
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
    private Collection<Integer> sources;

    public PrimeAlgorithm(PrimeGraph graph, Collection<Integer> sources) {
        this.graph = graph;
        this.sources = sources;
        this.edges = new PriorityQueue<PrimeEdge>();
        init(sources);
    }

    public Map<Integer, Cluster<Integer>> run() {
        Map<Integer, Cluster<Integer>> ret = new HashMap<Integer, Cluster<Integer>>();

        while (edges.size() > 0) {
            PrimeEdge minEdge = edges.poll();
            //System.out.println(minEdge.getWeight());

            Integer nodeFromStr = minEdge.getFrom();
            Integer nodeToStr = minEdge.getTo();
            Double weight = minEdge.getWeight();

            PrimeNode nodeFrom = graph.getNode(nodeFromStr);
            PrimeNode nodeTo = graph.getNode(nodeToStr);

            if (nodeTo.getDistance() == null || nodeTo.getDistance() > nodeFrom.getDistance() + weight) {
                nodeTo.setDistance(nodeFrom.getDistance() + weight);
                nodeTo.setSourceName(nodeFrom.getSourceName());

                for (Entry<Integer, Double> entryEdge : nodeTo.getEdges().entrySet()) {
                    PrimeEdge edge = new PrimeEdge(nodeFromStr, entryEdge.getKey(), entryEdge.getValue());
                    edges.add(edge);
                }
            }
        }

        for (Integer source : sources) {
            Cluster<Integer> clust = new Cluster<Integer>(source);
            clust.add(source);
            ret.put(source, clust);
        //  current.addNode(node);
        }

        for (PrimeNode node : graph.getNodes()) {
            if (node.getSourceName() != null) {
                if (!sources.contains(node.getName())) {
                    Cluster<Integer> cluster = ret.get(node.getSourceName());
                    cluster.add(node.getName());
                }
            }
        }

        return ret;
    }

    private void init(Collection<Integer> sources) {
        System.out.println("CENTERS: " + sources.size());
        for (Integer source : sources) {
            PrimeNode node = graph.getNode(source);
            node.setDistance(Double.valueOf(0));
            node.setSourceName(source);
            //        current.addNode(node);
            for (Entry<Integer, Double> entryEdge : node.getEdges().entrySet()) {
                PrimeEdge edge = new PrimeEdge(source, entryEdge.getKey(), entryEdge.getValue());
                edges.add(edge);
            }
        }
    }
}
