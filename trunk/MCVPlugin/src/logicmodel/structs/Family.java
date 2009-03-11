package logicmodel.structs;

import java.awt.Color;

public class Family {

    private String familyID;
    private Color color;

    public Family(String FamilyID, Color color) {
        this.familyID = FamilyID;
        this.color = color;
    }

    public Family(String FamilyID) {
        this.familyID = FamilyID;
    }

    public String getFamilyID() {
        return familyID;
    }

    public void setFamilyID(String FamilyID) {
        this.familyID = FamilyID;
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
