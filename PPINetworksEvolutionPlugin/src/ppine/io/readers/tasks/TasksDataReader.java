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

package ppine.io.readers.tasks;

import ppine.io.AbstractDataReader;
import cytoscape.Cytoscape;
import cytoscape.task.ui.JTaskConfig;
import cytoscape.task.util.TaskManager;
import java.util.Map;
import ppine.io.listeners.ExperimentsLoadingErrorsListener;
import ppine.io.listeners.FamiliesLoadingErrorsListener;
import ppine.io.listeners.InteractionsLoadingErrorsListener;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.logicmodel.structs.PPINetwork;

public class TasksDataReader extends AbstractDataReader {

    @Override
    public void readSpeciesInteractions(PPINetwork network, String filepath, Double treshold, InteractionsLoadingErrorsListener errorListener) {

        LoadSpeciesInteractionsTask task = new LoadSpeciesInteractionsTask(filepath, network, treshold);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readFamiliesTree(String filepath, FamiliesLoadingErrorsListener errorListener) {
        LoadTreesTask task = new LoadTreesTask(filepath);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readSpeciesTree(String filepath, SpeciesLoadingErrorsListener errorListeners) {

        LoadSpaciesTask task = new LoadSpaciesTask(filepath);
        task.setErrorListener(errorListeners);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(false);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }

    @Override
    public void readAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener) {
        LoadAllInteractionsTask task = new LoadAllInteractionsTask(filepath, tresholds);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);

    }

    @Override
    public void readAllExperiments(String filepath, ExperimentsLoadingErrorsListener errorListener) {
        LoadAllExperimentsTask task = new LoadAllExperimentsTask(filepath);
        task.setErrorListener(errorListener);
        JTaskConfig jTaskConfig = new JTaskConfig();

        jTaskConfig.displayCancelButton(true);
        jTaskConfig.setOwner(Cytoscape.getDesktop());
        jTaskConfig.displayCloseButton(false);
        jTaskConfig.displayStatus(true);
        jTaskConfig.setAutoDispose(true);

        TaskManager.executeTask(task, jTaskConfig);
    }
}
