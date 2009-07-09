/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ppine.utils;

import cytoscape.CyNetwork;
import cytoscape.Cytoscape;
import java.util.Set;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class CytoUtil {

    public static boolean cytoNetworkExist(String ID) {
        Set<CyNetwork> nets = Cytoscape.getNetworkSet();
        boolean res = false;
        for (CyNetwork cyNetwork : nets) {
            if (cyNetwork.getTitle().equals(ID)) {
                return true;
            }
        }

        return res;
    }
}
