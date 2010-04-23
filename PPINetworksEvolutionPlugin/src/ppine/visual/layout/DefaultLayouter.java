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

package ppine.visual.layout;

import cytoscape.Cytoscape;
import cytoscape.view.CyNetworkView;
import giny.model.Node;
import giny.view.NodeView;
import java.util.Collection;
import ppine.viewmodel.structs.CytoGroupNode;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.viewmodel.structs.CytoProteinProjection;

public class DefaultLayouter extends Layouter {

    private static double R = 30.0;  //TODO bad constant

    private static void groupNodeLayout(CytoGroupNode cytoGroupNode, CyNetworkView cyNetworkView) {
        Collection<CytoProtein> cytoProteins = cytoGroupNode.getContext().getInsideProteins();

        CytoProtein parentProtein = cytoGroupNode.getContext().getMotherProtein();
        String parentNetworkCytoID = parentProtein.getCytoNetwork().getCytoID();
        CyNetworkView parentNetworkView = Cytoscape.getNetworkView(parentNetworkCytoID);
        Node parentNode = Cytoscape.getRootGraph().getNode(parentProtein.getIndex());
        NodeView parentNodeView = parentNetworkView.getNodeView(parentNode);


        int i = 0;
        int count = cytoProteins.size();

        for (CytoProtein cytoProtein : cytoProteins) {

            Node node = Cytoscape.getRootGraph().getNode(cytoProtein.getIndex());
            NodeView proteinView = cyNetworkView.getNodeView(node);

            setProteinViewPosition(proteinView, parentNodeView, i, count);
            i++;
        }
    }

    @Override
    public void projectionToDownLayout(CytoPPINetworkProjectionToDown projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        
        projectionToDownLayout(projection, cyNetworkView);
    }

    private void projectionToDownLayout(CytoPPINetworkProjectionToDown projection, CyNetworkView cyNetworkView) {
        Collection<CytoGroupNode> cytoGroupNodes = projection.getCytoGroupNodes();

        for (CytoGroupNode cytoGroupNode : cytoGroupNodes) {
            groupNodeLayout(cytoGroupNode, cyNetworkView);
        }
    }

    @Override
    public void projectionToUpLayout(CytoPPINetworkProjectionToUp projection) {
        CyNetworkView cyNetworkView = Cytoscape.getNetworkView(projection.getCytoID());
        projectionToUpLayout(projection, cyNetworkView);
    }

    private void projectionToUpLayout(CytoPPINetworkProjectionToUp projection, CyNetworkView cyNetworkView) {


        for (CytoProtein cytoProteinProjection : projection.getCytoProteins()) {
            CytoProtein cytoMotherProteinOrNull = ((CytoProteinProjection) cytoProteinProjection).tryGetCytoMotherProtein();

            if (cytoMotherProteinOrNull != null) {
                String motherNetworkCytoID = cytoMotherProteinOrNull.getCytoNetwork().getCytoID();
                CyNetworkView parentNetworkView = Cytoscape.getNetworkView(motherNetworkCytoID);
                Node parentNode = Cytoscape.getRootGraph().getNode(cytoMotherProteinOrNull.getIndex());
                NodeView nodeView = parentNetworkView.getNodeView(parentNode);

                Node node = Cytoscape.getRootGraph().getNode(cytoProteinProjection.getIndex());
                NodeView proteinView = cyNetworkView.getNodeView(node);
                proteinView.setXPosition(nodeView.getXPosition());
                proteinView.setYPosition(nodeView.getYPosition());
            }
        }

    }

    private static void setProteinViewPosition(NodeView proteinView, NodeView parentNodeView, int i, int count) {
        if (count == 1) {
            proteinView.setXPosition(parentNodeView.getXPosition());
            proteinView.setYPosition(parentNodeView.getYPosition());
        } else {
            double arg = 2 * Math.PI * i / count;
            double x_offset = R * Math.cos(arg);
            double y_offset = R * Math.sin(arg);
            proteinView.setXPosition(parentNodeView.getXPosition() + x_offset);
            proteinView.setYPosition(parentNodeView.getYPosition() + y_offset);
        }
    }
}
