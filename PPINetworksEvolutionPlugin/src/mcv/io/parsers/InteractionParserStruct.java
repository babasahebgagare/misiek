package mcv.io.parsers;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class InteractionParserStruct {

    private String from;
    private String to;
    private Double sim;

    public InteractionParserStruct(String from, String to, Double sim) {
        this.from = from;
        this.to = to;
        this.sim = sim;
    }

    public String getFrom() {
        return from;
    }

    public Double getSim() {
        return sim;
    }

    public String getTo() {
        return to;
    }
}
