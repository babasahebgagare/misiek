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

package ppine.io.readers.tasks;

import ppine.io.exceptions.FamiliesTreeFormatException;
import ppine.io.parsers.DataParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import ppine.main.PluginDataHandle;

public class LoadTreesTask extends PPINELoadTask {

    private long current;

    public LoadTreesTask(String treespath) {
        super(treespath);
    }

    public void run() {
        myThread = Thread.currentThread();
        taskMonitor.setStatus("Loading proteins data...");
        taskMonitor.setPercentCompleted(-1);
        try {

            openStreams();
            taskMonitor.setPercentCompleted(0);

            reading();

            PluginDataHandle.getLoadedDataHandle().setProteinsLoaded(true);
            taskMonitor.setPercentCompleted(100);
            doneActionPerformed();

        } catch (FamiliesTreeFormatException ex) {
            sendErrorEvent(ex, "FamiliesTreeFormatException", "LoadTreesTask.run()");
        } catch (FileNotFoundException ex) {
            sendErrorEvent(ex, "FileNotFoundException", "LoadTreesTask.run()");
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
        return "Loading proteins data...";
    }

    private void reading() throws IOException, FamiliesTreeFormatException {
        float last_percent = 0;
        float percent = 0;

        while (br.ready()) {
            String line;
            line = br.readLine();
            if (line != null && !line.equals("")) {
                String[] families = line.split(";");
                for (String family : families) {
                    DataParser.getInstance().readFamiliesTreeString(family);
                }
                current = fis.getChannel().position();
                percent = current * 100 / (float) max;
                if (percent > last_percent + 1) {
                    last_percent = percent;
                    taskMonitor.setPercentCompleted(Math.round(percent));
                }
            }
        }
    }
}
