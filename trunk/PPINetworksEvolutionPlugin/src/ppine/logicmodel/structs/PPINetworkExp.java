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

package ppine.logicmodel.structs;

import java.util.HashMap;
import java.util.Map;

public class PPINetworkExp extends SpeciesTreeNode {

    private Map<String, Protein> proteins = new HashMap<String, Protein>();
    private Map<String, ExpInteraction> interactions = new HashMap<String, ExpInteraction>();

    public PPINetworkExp(String NetworkID, SpeciesTreeNode ParentNetwork) {
        super(NetworkID, ParentNetwork);
    }

    @Override
    public void deleteAllInteractions() {
        interactions = new HashMap<String, ExpInteraction>();
    }

    public void addInteraction(ExpInteraction expInteraction) {
        interactions.put(expInteraction.getID(), expInteraction);
    }

    @Override
    public boolean containsProtein(String proteinID) {
        return proteins.containsKey(proteinID);
    }

    @Override
    public Protein addProtein(String proteinID, Protein parentProtein, Family family) {
        Protein protein = new Protein(proteinID, parentProtein, this, family);
        proteins.put(proteinID, protein);
        return protein;
    }

    @Override
    public Protein addRootProtein(String proteinID, Family family) {
        Protein protein = new Protein(proteinID, this, family);
        proteins.put(proteinID, protein);
        return protein;
    }

    @Override
    public void deleteInteraction(String iD) {
        interactions.remove(iD);
    }

    @Override
    public Protein getProtein(String proteinID) {
        return proteins.get(proteinID);
    }

    @Override
    public Map<String, Protein> getProteins() {
        return proteins;
    }

    @Override
    public void setProteins(Map<String, Protein> proteins) {
        this.proteins = proteins;
    }

    public Map<String, ExpInteraction> getInteractions() {
        return interactions;
    }

    public void setInteractions(Map<String, ExpInteraction> interactions) {
        this.interactions = interactions;
    }

    @Override
    public int getInteractionsCount() {
        return interactions.size();
    }
}
