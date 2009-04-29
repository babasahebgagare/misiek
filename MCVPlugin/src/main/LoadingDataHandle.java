package main;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class LoadingDataHandle {

    private String speciesFilename;
    private String genesFilename;
    private Map<String, String> interactionsFilenames = new TreeMap<String, String>();

    public void addInteractionFilename(String speciesName, String filepath) {
        System.out.println(speciesName);
        System.out.println(filepath);
        interactionsFilenames.put(speciesName, filepath);
    }

    public void deleteInteractionFilename(String speciesName, String filepath) {
        if (interactionsFilenames.containsKey(speciesName)) {
            interactionsFilenames.put(speciesName, filepath);
        }
    }

    public LoadingDataHandle() {
        deleteAll();
    }

    public void deleteAll() {
        speciesFilename = null;
        genesFilename = null;
        interactionsFilenames = new TreeMap<String, String>();
    }

    public String getGenesFilename() {
        return genesFilename;
    }

    public void setGenesFilename(String genesFilename) {
        this.genesFilename = genesFilename;
    }

    public Map<String, String> getInteractionsFilenames() {
        return interactionsFilenames;
    }

    public void setInteractionsFilenames(Map<String, String> interactionsFilenames) {
        this.interactionsFilenames = interactionsFilenames;
    }

    public String getSpeciesFilename() {
        return speciesFilename;
    }

    public void setSpeciesFilename(String speciesFilename) {
        this.speciesFilename = speciesFilename;
    }
}
