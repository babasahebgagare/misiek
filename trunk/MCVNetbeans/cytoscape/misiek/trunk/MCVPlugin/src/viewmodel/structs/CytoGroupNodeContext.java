package viewmodel.structs;

import logicmodel.structs.CytoProtein;
import java.util.Collection;
import java.util.HashSet;

public class CytoGroupNodeContext {

    private CytoProtein motherProtein;
    private Collection<CytoProtein> insideProteins;

    public CytoGroupNodeContext(CytoProtein motherProtein) {
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
