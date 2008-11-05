package structs.model;

import java.awt.Color;

public class Family {

    private String FamilyID;
    private Color color;

    public Family(String FamilyID, Color color) {
        this.FamilyID = FamilyID;
        this.color = color;
    }

    public Family(String FamilyID) {
        this.FamilyID = FamilyID;
    }

    public String getFamilyID() {
        return FamilyID;
    }

    public void setFamilyID(String FamilyID) {
        this.FamilyID = FamilyID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setColor(Float r, Float g, Float b) {
        this.color = new Color(r, g, b);
    }
}
