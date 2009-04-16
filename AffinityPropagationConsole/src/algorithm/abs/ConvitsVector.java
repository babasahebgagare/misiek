package algorithm.abs;

import java.util.Vector;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ConvitsVector {

    private Vector<Boolean> convits;
    private int current;
    private int len;
    private boolean ready;

    public ConvitsVector(int len) {
        this.len = len;
        this.convits = new Vector<Boolean>(len);
        this.current = 0;
        this.ready = false;

    }

    public void addCovits(boolean b) {
        convits.set(current, b);
        if (current == len - 1) {
            this.ready = true;
        }
        current = (current + 1) % len;
    }

    public boolean checkConvits() {
        if (ready == false) {
            return false;
        } else {
            Boolean first = convits.firstElement();

            for (Boolean b : convits) {
                if (!b.equals(first)) {
                    return false;
                }
            }
            return true;
        }
    }

    public void init() {
        for (int i = 0; i < len; i++) {
            this.convits.add(Boolean.valueOf(true));
        }
    }
}
