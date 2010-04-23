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

package ppine.logicmodel.structs;

import java.util.Map;

public abstract class SpeciesTreeNode {

    private SpeciesTreeNodeContext context = null;
    private String ID;

    public SpeciesTreeNode(String NetworkID, SpeciesTreeNode ParentNetwork) {
        ID = NetworkID;
        context = new SpeciesTreeNodeContext(ParentNetwork, this);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public SpeciesTreeNodeContext getContext() {
        return context;
    }

    public void setContext(SpeciesTreeNodeContext context) {
        this.context = context;
    }

    public abstract void deleteAllInteractions();

    //public abstract void addInteraction(Interaction interaction);
    public abstract boolean containsProtein(String ProteinID);

    public abstract Protein addProtein(String ProteinID, Protein ParentProtein, Family Family);

    public abstract Protein addRootProtein(String ProteinID, Family Family);

    public abstract void deleteInteraction(String iD);

    public abstract Protein getProtein(String ProteinID);

    public abstract Map<String, Protein> getProteins();

    public abstract void setProteins(Map<String, Protein> proteins);

    public abstract int getInteractionsCount();

    //public abstract Map<String, Interaction> getInteractions();

    //public abstract void setInteractions(Map<String, Interaction> interactions);
}
