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
package ppine.logicmodel.controllers;

import cytoscape.Cytoscape;
import cytoscape.data.CyAttributes;
import java.util.Collection;
import ppine.viewmodel.controllers.CytoDataHandle;
import ppine.viewmodel.structs.CytoAbstractPPINetwork;
import ppine.viewmodel.structs.CytoGroupNode;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToDown;
import ppine.viewmodel.structs.CytoPPINetworkProjectionToUp;
import ppine.viewmodel.structs.CytoProtein;
import ppine.viewmodel.structs.CytoProteinProjection;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import ppine.main.PluginDataHandle;
import ppine.utils.IDCreator;

public class ProjectorNetwork {

    public static CytoPPINetworkProjectionToDown projectProteinsToDownOnNetwork(Collection<CytoProtein> selectedProteins, SpeciesTreeNode networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);

        CytoPPINetworkProjectionToDown projection = cdh.createCytoProjectionToDown(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProtein : selectedProteins) {
            projectCytoProtein(cytoProtein, projection);

        }
        return projection;
    }

    public static CytoPPINetworkProjectionToUp projectProteinsToUpOnNetwork(Collection<CytoProtein> selectedProteins, SpeciesTreeNode networkTarget, CytoAbstractPPINetwork cytoNetworkSource) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        String projectionID = IDCreator.createNetworkProjectionID(networkTarget, cytoNetworkSource);
        CytoPPINetworkProjectionToUp projection = cdh.createCytoProjectionToUp(projectionID, cytoNetworkSource, networkTarget);

        for (CytoProtein cytoProteinBelow : selectedProteins) {
            Protein protein = cytoProteinBelow.getProtein();
            Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapUp().get(networkTarget.getID());

            if (proteinProjections != null) {

                for (Protein proteinAbove : proteinProjections) {
                    String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinAbove, projection);
                    if (!projection.containsCytoProtein(ProteinProjectionID)) {
                        cdh.createCytoProteinProjection(ProteinProjectionID, proteinAbove, projection, cytoProteinBelow);
                    }
                }
            }
        }

        return projection;
    }

    private static void projectCytoProtein(CytoProtein cytoProtein, CytoPPINetworkProjectionToDown projection) {
        CytoDataHandle cdh = PluginDataHandle.getCytoDataHandle();

        Protein protein = cytoProtein.getProtein();

        String groupNodeID = IDCreator.createGroupNodeID(cytoProtein);
        CytoGroupNode node = cdh.createCytoGroupNode(groupNodeID, cytoProtein);
        projection.addCytoGroupNode(node);

        Collection<Protein> proteinProjections = protein.getProjects().getProjectorMapDown().get(projection.getNetwork().getID());
        if (proteinProjections != null) {

            for (Protein proteinProject : proteinProjections) {
                String ProteinProjectionID = IDCreator.createProteinProjectionID(proteinProject, projection);
                CytoProteinProjection proteinProjection = cdh.createCytoProteinProjection(ProteinProjectionID, proteinProject, projection, cytoProtein);
                node.addCytoProteinInside(proteinProjection);
                projectAttributes(cytoProtein.getCytoID(), proteinProjection.getCytoID());  // to remove
            }

        }
    }

    private static void projectAttributes(String proteinID, String newProteinID) {

        CyAttributes nodeAttributes = Cytoscape.getNodeAttributes();

        String[] attributes = nodeAttributes.getAttributeNames();
        for (String attribute : attributes) {
            if (!attribute.equals("ID") && !attribute.equals("hiddenLabel") && !attribute.equals("canonicalName")) {
                System.out.println("map: " + attribute);
            }
            if (nodeAttributes.getType(attribute) == nodeAttributes.TYPE_STRING) {
                String attr = nodeAttributes.getStringAttribute(proteinID, attribute);
                if (attr != null) {
                    nodeAttributes.setAttribute(newProteinID, attribute, attr);
                }
            } else if (nodeAttributes.getType(attribute) == nodeAttributes.TYPE_FLOATING) {
                Double attr = nodeAttributes.getDoubleAttribute(proteinID, attribute);
                if (attr != null) {
                    nodeAttributes.setAttribute(newProteinID, attribute, attr);
                }
            } else if (nodeAttributes.getType(attribute) == nodeAttributes.TYPE_INTEGER) {
                Integer attr = nodeAttributes.getIntegerAttribute(proteinID, attribute);
                if (attr != null) {
                    nodeAttributes.setAttribute(newProteinID, attribute, attr);
                }
            }
        }


    }
}
