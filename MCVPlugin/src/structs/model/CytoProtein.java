package structs.model;

public class CytoProtein extends CytoObject {

    private Protein protein;

    public CytoProtein(String CytoID, Protein protein) {
        this.setCytoID(CytoID);
        this.protein = protein;
    }

    public Protein getProtein() {
        return protein;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }
}
