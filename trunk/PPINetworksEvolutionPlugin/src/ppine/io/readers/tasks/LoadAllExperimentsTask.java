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

package ppine.io.readers.tasks;

import ppine.io.exceptions.ExperimentsFileFormatException;
import java.io.IOException;
import ppine.io.parsers.ExperimentParserStruct;
import ppine.io.parsers.rootparser.RootExperimentsParser;
import ppine.logicmodel.controllers.DataHandle;
import ppine.logicmodel.structs.PPINetworkExp;
import ppine.main.PluginDataHandle;
import ppine.utils.IDCreator;

public class LoadAllExperimentsTask extends PPINELoadTask {

    private long current;
    private int created = 0;
    private int all = 0;

    LoadAllExperimentsTask(String exppath) {
        super(exppath);
    }

    public void run() {
        myThread = Thread.currentThread();
        taskMonitor.setStatus("Experiments are loading...");
        taskMonitor.setPercentCompleted(-1);

        try {

            openStreams();
            taskMonitor.setPercentCompleted(0);
            reading();

            //MemoLogger.log("Loaded: " + Math.round((double) created * 100 / (double) all) + "% up than treshold");
            taskMonitor.setPercentCompleted(100);
            doneActionPerformed();
        } catch (ExperimentsFileFormatException ex) {
            sendErrorEvent(ex);
        } catch (IOException ex) {
            sendErrorEvent(ex);
        } finally {
            try {
                closeStreams();

            } catch (IOException ex) {
                sendErrorEvent(ex);
            }
        }
    }

    public String getTitle() {
        return "Experiments are loading...";
    }

    private void reading() throws IOException, ExperimentsFileFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        float percent = 0;
        float last_percent = 0;
        while (br.ready()) {
            all++;

            String line = br.readLine();

            ExperimentParserStruct interaction = RootExperimentsParser.readExperiment(line);
            /*if (interaction.getFrom().compareTo(interaction.getTo()) < 0) {
                continue;
            }*/

            String speciesName = interaction.getSpeciesName();

            String expNetworkName = IDCreator.createExpNetworkID(speciesName);
            PPINetworkExp netOrNull = dh.tryGetExpPPINetowrk(expNetworkName);

            if (netOrNull == null) {
                netOrNull = dh.createExpPPINetwork(speciesName, expNetworkName);
            }

            String edgeID = IDCreator.createExpInteractionID(interaction);

            dh.createInteractionExp(netOrNull, edgeID, interaction);
            created++;
            current = fis.getChannel().position();
            percent = current * 100 / (float) max;
            if (percent > last_percent + 1) {
                last_percent = percent;
                taskMonitor.setPercentCompleted(Math.round(percent));
            }
        }
    }
}
