package ppine.logicmodel.structs;

import java.awt.Color;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class Experiment {

    private String expID;
    private Color color;

    public Experiment(String expID, Color color) {
        this.expID = expID;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getExpID() {
        return expID;
    }

    public void setExpID(String expID) {
        this.expID = expID;
    }
}
