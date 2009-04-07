package prime;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PrimeEdge implements Comparable<PrimeEdge> {

    private String from;
    private String to;
    private Double weight;

    public PrimeEdge(String from, String to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int compareTo(PrimeEdge e) {
        if (e == null) {
            return -1;
        }
        if (this.getWeight() < e.getWeight()) {
            return -1;
        } else if (this.getWeight() > e.getWeight()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(final Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        } else {
            PrimeEdge e = (PrimeEdge) obj;
            return (this.compareTo(e) == 0);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
