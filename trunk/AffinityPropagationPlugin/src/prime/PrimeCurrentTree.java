/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.util.Collection;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeCurrentTree {

    private Collection<PrimeNode> nodes;

    public void addNode(PrimeNode node) {
        nodes.add(node);
    }

    public Collection<PrimeNode> getNodes() {
        return nodes;
    }
}
