package main;

import java.util.Map;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class LoadingDataHandle {

    private String speciesFilename;
    private String genesFilename;
    private Map<String, String> interactionsFilenames;

    public LoadingDataHandle() {
        deleteAll();
    }

    public void deleteAll() {
        speciesFilename = null;
        genesFilename = null;
        interactionsFilenames = null;
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
