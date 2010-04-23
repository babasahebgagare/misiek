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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.viewmodel.structs;

import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.logicmodel.structs.Protein;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import ppine.utils.IDCreator;

public abstract class CytoAbstractPPINetwork extends CytoObject {

    private Map<String, CytoProtein> proteins = new HashMap<String, CytoProtein>();
    private Map<String, CytoInteraction> interactions = new HashMap<String, CytoInteraction>();
    private Map<String, CytoExpInteraction> expInteractions = new HashMap<String, CytoExpInteraction>();
    private SpeciesTreeNode network;
    private String ID;

    public boolean containsCytoProtein(String SourceID) {
        Protein protein = this.network.getProtein(SourceID);

        if (protein == null) {
            return false;
        }

        String cytoProteinID = IDCreator.createProteinProjectionID(protein, this);

        return getCytoProtein(cytoProteinID) != null;
    }

    public void deleteCytoExpInteraction(String ID) {
        expInteractions.remove(ID);
    }

    public void deleteCytoExpInteractions() {
        expInteractions = new HashMap<String, CytoExpInteraction>();
    }

    public void addCytoExpInteraction(CytoExpInteraction cytoExpInteraction) {
        expInteractions.put(cytoExpInteraction.getCytoID(), cytoExpInteraction);
    }

    public Collection<CytoExpInteraction> getCytoExpInteractions() {
        return expInteractions.values();
    }

    public CytoExpInteraction getCytoExpInteraction(String ID) {
        return expInteractions.get(ID);
    }

    public void deleteCytoInteraction(String ID) {
        interactions.remove(ID);
    }

    public void deleteCytoInteractions() {
        interactions = new HashMap<String, CytoInteraction>();
    }

    public void addCytoInteraction(CytoInteraction cytoInteraction) {
        interactions.put(cytoInteraction.getCytoID(), cytoInteraction);
    }

    public Collection<CytoInteraction> getCytoInteractions() {
        return interactions.values();
    }

    public CytoInteraction getCytoInteraction(String ID) {
        return interactions.get(ID);
    }

    public void addCytoProtein(CytoProtein cytoProtein) {
        proteins.put(cytoProtein.getCytoID(), cytoProtein);
    }

    public Collection<CytoProtein> getCytoProteins() {
        return proteins.values();
    }

    public CytoProtein getCytoProtein(String ID) {
        return proteins.get(ID);
    }

    public void deleteCytoProtein(String ID) {
        proteins.remove(ID);
    }

    public abstract CytoAbstractPPINetwork tryGetMother();

    public CytoAbstractPPINetwork(SpeciesTreeNode network, String ID) {
        this.network = network;
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode network) {
        this.network = network;
    }
}
