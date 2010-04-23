/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.logicmodel.structs;

import java.util.Collection;
import java.util.HashSet;

public class NetworksHierarchy {

    private Collection<SpeciesTreeNode> networksAbove = new HashSet<SpeciesTreeNode>();
    private Collection<SpeciesTreeNode> networksBelow = new HashSet<SpeciesTreeNode>();
    private SpeciesTreeNode thisNetwork;

    public enum NetworkPosition {

        ABOVE, BELOW, NEIGHBOUR, THIS_NETWORK
    }

    public NetworksHierarchy(SpeciesTreeNode thisNetwork) {
        this.thisNetwork = thisNetwork;
    }

    public void addNetworkBelow(SpeciesTreeNode network) {
        networksBelow.add(network);


    }

    public void addNetworkAbove(SpeciesTreeNode network) {
        networksAbove.add(network);
    }

    public Collection<SpeciesTreeNode> getNetworksAbove() {
        return networksAbove;
    }

    public void setNetworksAbove(Collection<SpeciesTreeNode> networksAbove) {
        this.networksAbove = networksAbove;
    }

    public Collection<SpeciesTreeNode> getNetworksBelow() {
        return networksBelow;
    }

    public void setNetworksBelow(Collection<SpeciesTreeNode> networksBelow) {
        this.networksBelow = networksBelow;
    }

    public NetworkPosition getNetworkPosition(SpeciesTreeNode network) {
        if (networksAbove.contains(network)) {
            return NetworkPosition.ABOVE;
        } else if (networksBelow.contains(network)) {
            return NetworkPosition.BELOW;
        } else if (network == thisNetwork) {
            return NetworkPosition.THIS_NETWORK;
        } else {
            return NetworkPosition.NEIGHBOUR;
        }
    }
}
