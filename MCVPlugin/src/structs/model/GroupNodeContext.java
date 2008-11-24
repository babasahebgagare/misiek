package structs.model;

import java.util.Collection;
import java.util.HashSet;

public class GroupNodeContext {

    private CytoProtein motherProtein;
    private Collection<CytoProtein> insideProteins;

    public GroupNodeContext(CytoProtein motherProtein) {
        this.motherProtein = motherProtein;
        this.insideProteins = new HashSet<CytoProtein>();
    }

    public void addProteinInside(CytoProtein proteinProjection) {
        insideProteins.add(proteinProjection);
    }

    public Collection<CytoProtein> getInsideProteins() {
        return insideProteins;
    }

    public void setInsideProteins(Collection<CytoProtein> insideProteins) {
        this.insideProteins = insideProteins;
    }

    public CytoProtein getMotherProtein() {
        return motherProtein;
    }

    public void setMotherProtein(CytoProtein motherProtein) {
        this.motherProtein = motherProtein;
    }
}
