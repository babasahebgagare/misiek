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

package ppine.io;

import ppine.io.listeners.ExperimentsLoadingErrorsListener;
import ppine.io.readers.tasks.TasksDataReader;
import java.util.Map;
import ppine.io.listeners.FamiliesLoadingErrorsListener;
import ppine.io.listeners.InteractionsLoadingErrorsListener;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.logicmodel.structs.PPINetwork;

public abstract class AbstractDataReader {

    private static AbstractDataReader reader = new TasksDataReader();

    public static AbstractDataReader getInstance() {
        return reader;
    }

    public abstract void readAllExperiments(String filepath, ExperimentsLoadingErrorsListener errorListener);

    public abstract void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold, InteractionsLoadingErrorsListener errorListener);

    public abstract void readAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener);

    public abstract void readSpeciesTree(String filepath, SpeciesLoadingErrorsListener errorListener);

    public abstract void readFamiliesTree(String filepath, FamiliesLoadingErrorsListener errorListener);
}
