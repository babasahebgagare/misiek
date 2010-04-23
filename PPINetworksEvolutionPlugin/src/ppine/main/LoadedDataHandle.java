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

package ppine.main;

import java.util.Map;
import java.util.TreeMap;
import ppine.logicmodel.controllers.DataHandle;

public class LoadedDataHandle {

    private Map<String, String> interactionsFilenames = new TreeMap<String, String>();
    private Map<String, Double> interactionsTresholds = new TreeMap<String, Double>();
    private String oneInteractionFilename;
    private String experimentsFilename;
    private boolean manyFilesLoaded;
    private boolean proteinsLoaded;

    public boolean interactionsLoaded() {
        if (oneInteractionFilename != null) {
            return true;
        } else if (interactionsFilenames.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExpLoaded() {
        if (experimentsFilename == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean speciesTreeLoaded() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        if (dh.getRootNetwork() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addInteractionOneFileData(String speciesName, Double tresholdOrNull) {
        interactionsTresholds.put(speciesName, tresholdOrNull);
    }

    public boolean fromManyFilesLoaded() {
        return manyFilesLoaded;
    }

    public boolean fromOneFileLoaded() {
        if (oneInteractionFilename == null) {
            return false;
        } else {
            return true;
        }
    }

    public void deleteExperimentsFilename() {
        experimentsFilename = null;
    }

    public String getExperimentsFilename() {
        return experimentsFilename;
    }

    public void setExperimentsFilename(String experimentsFilename) {
        this.experimentsFilename = experimentsFilename;
    }

    public void deleteOneInteractionsFilename() {
        oneInteractionFilename = null;
    }

    public String getOneInteractionFilename() {
        return oneInteractionFilename;
    }

    public void setOneInteractionFilename(String oneInteractionFilename) {
        this.oneInteractionFilename = oneInteractionFilename;
    }

    public String getSpeciesInteractionsFilename(String speciesName) {
        return interactionsFilenames.get(speciesName);
    }

    public Double getSpeciesInteractionsTreshold(String speciesName) {
        return interactionsTresholds.get(speciesName);
    }

    public void addInteractionData(String speciesName, String filepath, Double treshold) {
        interactionsFilenames.put(speciesName, filepath);
        interactionsTresholds.put(speciesName, treshold);
        manyFilesLoaded = true;
    }

    public void deleteInteractionData(String speciesName) {
        if (interactionsFilenames.containsKey(speciesName)) {
            interactionsFilenames.remove(speciesName);
        }
        if (interactionsTresholds.containsKey(speciesName)) {
            interactionsTresholds.remove(speciesName);
        }
    }

    public LoadedDataHandle() {
        this.proteinsLoaded = false;
        deleteAll();
    }

    public boolean isProteinsLoaded() {
        return this.proteinsLoaded;
    }

    public void setProteinsLoaded(boolean proteinsLoaded) {
        this.proteinsLoaded = proteinsLoaded;
    }

    public void deleteAll() {
        interactionsFilenames = new TreeMap<String, String>();
        interactionsTresholds = new TreeMap<String, Double>();
        manyFilesLoaded = false;
        experimentsFilename = null;
        oneInteractionFilename = null;
    }

    public Map<String, String> getInteractionsFilenames() {
        return interactionsFilenames;
    }

    public void setInteractionsFilenames(Map<String, String> interactionsFilenames) {
        this.interactionsFilenames = interactionsFilenames;
    }

    public boolean loadedInteractions(String speciesName) {
        if (this.interactionsFilenames.get(speciesName) == null) {
            return false;
        } else {
            return true;
        }
    }
}
