/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm.smart;

import java.util.Collection;
import java.util.HashSet;
import utils.Messenger;

/**
 *
 * @param <T>
 * @author misiek
 */
public class Cluster<T> implements Comparable<Cluster<T>> {

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

    public int compareTo(Cluster<T> cluster) {
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

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            Cluster<T> cluster = (Cluster<T>) obj;
            return (this.compareTo(cluster) == 0);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
