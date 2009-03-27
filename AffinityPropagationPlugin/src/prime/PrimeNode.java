package prime;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeNode {

    private Double distance;
    private String sourceName;
    private String name;
    private Map<String, Double> edges;

    public PrimeNode(String nodename) {
        this.name = nodename;
        this.edges = new TreeMap<String, Double>();
    }

    public int size() {
        return edges.size();
    }

    public String getName() {
        return name;
    }

    public void addEdge(String edgeName, Double sim) {
        this.edges.put(edgeName, sim);
    }

    public Double getEdgeWeight(String edgeName) {
        return edges.get(edgeName);
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Map<String, Double> getEdges() {
        return edges;
    }
}
