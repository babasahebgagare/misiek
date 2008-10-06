package structs;

import java.util.Collection;
import java.util.HashSet;

public class GroupNodeContext {

    private Protein motherProtein;
    private Collection<ProteinProjection> insideProteins;

    public GroupNodeContext(Protein motherProtein) {
        this.motherProtein = motherProtein;
        this.insideProteins = new HashSet<ProteinProjection>();
    }

    public void addProteinInside(ProteinProjection proteinProjection) {
        insideProteins.add(proteinProjection);
    }

    public Protein getMotherProtein() {
        return motherProtein;
    }

    public void setMotherProtein(Protein motherProtein) {
        this.motherProtein = motherProtein;
    }

    public Collection<ProteinProjection> getInsideProteins() {
        return insideProteins;
    }

    public void setInsideProteins(Collection<ProteinProjection> insideProteins) {
        this.insideProteins = insideProteins;
    }
}
