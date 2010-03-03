/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
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
package ppine.ui.mips;

import cytoscape.CyNetwork;
import java.io.IOException;
import cytoscape.CyEdge;
import cytoscape.CyNode;
import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import cytoscape.view.CyNetworkView;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ComplexesCytoReader {

    Complex proteinsComplex = new Complex("my_complex");

    public Collection<Complex> readAllComplexes(String attribute) {
        Map<String, Complex> complexes = new TreeMap<String, Complex>();

        final CyAttributes nodesAttributes = Cytoscape.getNodeAttributes();
        Cytoscape.getCurrentNetworkView().redrawGraph(false, true);
        @SuppressWarnings(value = "unchecked")
        List<CyNode> nodes = Cytoscape.getCurrentNetwork().nodesList();

        Cytoscape.getCurrentNetwork().unselectAllNodes();
        for (CyNode node : nodes) {

            String cluster_id = nodesAttributes.getStringAttribute(node.getIdentifier(), attribute);

            if (cluster_id != null && !cluster_id.equals("")) {
                if (!complexes.containsKey(cluster_id)) {
                    Complex new_comp = new Complex(cluster_id);
                    complexes.put(cluster_id, new_comp);
                }
                Complex comp = complexes.get(cluster_id);
                comp.addProtein(node.getIdentifier());
            } else {
                System.out.println("jest: " + node.getIdentifier());
            }
        }
        return complexes.values();
    }

    public Complex readProteins() throws IOException {


        CyNetwork network = Cytoscape.getCurrentNetwork();
        @SuppressWarnings("unchecked")
        Set<CyNode> nodes = network.getSelectedNodes();
        for (CyNode node : nodes) {
            String proteinID = node.getIdentifier();
            proteinsComplex.addProtein(proteinID.toUpperCase());
        }

        return proteinsComplex;
    }

    public Complex getNamesMapping() {
        return proteinsComplex;
    }
}

