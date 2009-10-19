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

import ppine.io.parsers.rootparser.RootInteractionsParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import ppine.io.exceptions.InteractionsFileFormatException;
import ppine.io.parsers.InteractionParserStruct;
import ppine.logicmodel.controllers.DataHandle;
import ppine.logicmodel.structs.PPINetwork;
import ppine.main.PluginDataHandle;
import ppine.utils.IDCreator;

public class LoadSpeciesInteractionsTask extends PPINELoadTask {

    private Double treshold;
    private PPINetwork network;
    private int lineNumber = 0;

    LoadSpeciesInteractionsTask(String filepath, PPINetwork network, Double treshold) {
        super(filepath);
        this.network = network;
        this.treshold = treshold;
    }

    @Override
    public void run() {
        myThread = Thread.currentThread();
        taskMonitor.setStatus("Interactions loading for: " + network.getID());
        taskMonitor.setPercentCompleted(-1);

        try {
            openStreams();
            taskMonitor.setPercentCompleted(0);
            reading();
            taskMonitor.setPercentCompleted(100);
            doneActionPerformed();
        } catch (InteractionsFileFormatException ex) {
            sendErrorEvent(ex, "Line number: " + String.valueOf(lineNumber), "LoadSpeciesInteractionsTask.run()");
        } catch (FileNotFoundException ex) {
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
        return "Loading interactions with treshold: " + String.valueOf(treshold);
    }

    private void reading() throws IOException, InteractionsFileFormatException {
        DataHandle dh = PluginDataHandle.getDataHandle();
        int count = 0;
        float percent = 0;
        float last_percent = 0;
        long current;

        while (br.ready()) {
            lineNumber++;
            InteractionParserStruct interaction = null;

            String line = br.readLine();
            interaction = RootInteractionsParser.readInteraction(line);

            String SourceID = interaction.getFrom();
            String TargetID = interaction.getTo();
            Double probability = interaction.getSim();

            if (treshold == null || probability > treshold) {
                if (network.containsProtein(TargetID) && network.containsProtein(SourceID)) {
                    String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);
                    dh.createInteraction(EdgeID, SourceID, TargetID, probability, network);
                    count++;
                }
            }

            current = fis.getChannel().position();
            percent = current * 100 / (float) max;
            if (percent > last_percent + 1) {
                last_percent = percent;
                taskMonitor.setPercentCompleted(Math.round(percent));
                taskMonitor.setStatus("Interactions loading for: " + network.getID() + "  " + count);
            }
        }
    }
}
