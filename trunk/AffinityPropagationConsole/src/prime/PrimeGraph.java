package prime;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeGraph {

    private Map<Integer, PrimeNode> nodes;

    public PrimeGraph() {
        this.nodes = new TreeMap<Integer, PrimeNode>();
    }

    public void addEdge(Integer from, Integer to, Double sim) {
        PrimeNode nodeFrom = nodes.get(from);
        nodeFrom.addEdge(to, sim);
    }

    public void addNode(Integer nodeName) {
        PrimeNode node = new PrimeNode(nodeName);
        this.nodes.put(nodeName, node);
    }

    public PrimeNode getNode(Integer nodeName) {
        return nodes.get(nodeName);
    }

    public Collection<PrimeNode> getNodes() {
        return nodes.values();
    }
}
