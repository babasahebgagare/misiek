package prime;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeGraph {

    private Map<String, PrimeNode> nodes;

    public PrimeGraph() {
        this.nodes = new TreeMap<String, PrimeNode>();
    }

    public void addEdge(String from, String to, Double sim) {
        PrimeNode nodeFrom = nodes.get(from);
        nodeFrom.addEdge(to, -sim);
    }

    public void addNode(String nodeName) {
        PrimeNode node = new PrimeNode(nodeName);
        this.nodes.put(nodeName, node);
    }

    public PrimeNode getNode(String nodeName) {
        return nodes.get(nodeName);
    }

    public Collection<PrimeNode> getNodes() {
        return nodes.values();
    }
}
