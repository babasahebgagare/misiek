/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author misiek
 */
public class Cluster<T> implements Comparable<T> {

    private String name;
    private Collection<T> elements = new HashSet<T>();

    public Cluster(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int size() {
        return elements.size();
    }

    public void add(T element) {
        elements.add(element);
    }

    public Collection<T> getElements() {
        return elements;
    }

    @SuppressWarnings("unchecked")
    public int compareTo(final Object c) {
        Cluster<T> cluster = (Cluster<T>) c;
        if (cluster == null) {
            return -1;
        }
        if (this.size() > cluster.size()) {
            return -1;
        } else if (this.size() < cluster.size()) {
            return 1;
        } else {
            return 0;
        }
    }
}
