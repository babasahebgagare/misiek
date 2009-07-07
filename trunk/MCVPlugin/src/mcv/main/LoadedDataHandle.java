package mcv.main;

import java.util.Map;
import java.util.TreeMap;
import mcv.logicmodel.controllers.DataHandle;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
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
