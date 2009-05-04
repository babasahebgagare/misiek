package main;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class LoadedDataHandle {

    private Map<String, String> interactionsFilenames = new TreeMap<String, String>();
    private Map<String, Double> interactionsTresholds = new TreeMap<String, Double>();

    public String getSpeciesInteractionsFilename(String speciesName) {
        return interactionsFilenames.get(speciesName);
    }

    public Double getSpeciesInteractionsTreshold(String speciesName) {
        return interactionsTresholds.get(speciesName);
    }

    public void addInteractionData(String speciesName, String filepath, Double treshold) {
        interactionsFilenames.put(speciesName, filepath);
        interactionsTresholds.put(speciesName, treshold);
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
        deleteAll();
    }

    public void deleteAll() {
        interactionsFilenames = new TreeMap<String, String>();
        interactionsTresholds = new TreeMap<String, Double>();
    }


    public Map<String, String> getInteractionsFilenames() {
        return interactionsFilenames;
    }

    public void setInteractionsFilenames(Map<String, String> interactionsFilenames) {
        this.interactionsFilenames = interactionsFilenames;
    }
}
