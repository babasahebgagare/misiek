package affinitymain;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class InteractionData {

    private Integer from;
    private Integer to;
    private Double sim;

    public InteractionData(Integer from, Integer to, Double sim) {
        this.from = from;
        this.to = to;
        this.sim = sim;
    }

    public Integer getFrom() {
        return from;
    }

    public Double getSim() {
        return sim;
    }

    public Integer getTo() {
        return to;
    }
}
