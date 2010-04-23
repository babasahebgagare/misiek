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

public class ExpInteraction {

    private Experiment exp;
    private Protein source;
    private Protein target;
    private String ID;
    private SpeciesTreeNode network;

    public ExpInteraction(Experiment exp, Protein source, Protein target, String ID, SpeciesTreeNode network) {
        this.exp = exp;
        this.source = source;
        this.target = target;
        this.ID = ID;
        this.network = network;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Experiment getExperiment() {
        return exp;
    }

    public void setExperiment(Experiment exp) {
        this.exp = exp;
    }

    public SpeciesTreeNode getNetwork() {
        return network;
    }

    public void setNetwork(SpeciesTreeNode network) {
        this.network = network;
    }

    public Protein getSource() {
        return source;
    }

    public void setSource(Protein source) {
        this.source = source;
    }

    public Protein getTarget() {
        return target;
    }

    public void setTarget(Protein target) {
        this.target = target;
    }
}
