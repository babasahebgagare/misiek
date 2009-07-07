package mcv.main;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class LoadingDataHandle {

    private String speciesFilename;
    private String genesFilename;
    private String expFilename;
    private Map<String, String> interactionsFilenames = new TreeMap<String, String>();
    private Map<String, Double> interactionsTresholds = new TreeMap<String, Double>();

    public String getSpeciesFilename(String speciesName) {
        return interactionsFilenames.get(speciesName);
    }

    public Double getSpeciesTreshold(String speciesName) {
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

    public LoadingDataHandle() {
        deleteAll();
    }

    public void deleteAll() {
        speciesFilename = null;
        genesFilename = null;
        interactionsFilenames = new TreeMap<String, String>();
        interactionsTresholds = new TreeMap<String, Double>();
    }

    public String getGenesFilename() {
        return genesFilename;
    }

    public String getExpFilename() {
        return expFilename;
    }

    public void setExpFilename(String expFilename) {
        this.expFilename = expFilename;
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
