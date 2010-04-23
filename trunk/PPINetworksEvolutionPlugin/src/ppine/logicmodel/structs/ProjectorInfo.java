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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProjectorInfo {

    private Map<String, Collection<Protein>> projectorMapUp = new HashMap<String, Collection<Protein>>();
    private Map<String, Collection<Protein>> projectorMapDown = new HashMap<String, Collection<Protein>>();

    public ProjectorInfo() {
    }

    public Map<String, Collection<Protein>> getProjectorMapDown() {
        return projectorMapDown;
    }

    public void setProjectorMapDown(Map<String, Collection<Protein>> projectorMapDown) {
        this.projectorMapDown = projectorMapDown;
    }

    public Map<String, Collection<Protein>> getProjectorMapUp() {
        return projectorMapUp;
    }

    public void setProjectorMapUp(Map<String, Collection<Protein>> projectorMapUp) {
        this.projectorMapUp = projectorMapUp;
    }
}
