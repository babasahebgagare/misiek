package mcv.io.parsers;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExperimentParserStruct {

    private String from;
    private String to;
    private String expID;
    private String speciesName;

    public ExperimentParserStruct(String from, String to, String expID, String speciesName) {
        this.from = from;
        this.to = to;
        this.expID = expID;
        this.speciesName = speciesName;
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
