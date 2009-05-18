package mcv.ui;

import cytoscape.CyNetwork;
import java.util.Collection;
import java.util.Map;
import mcv.io.listeners.SpeciesLoadingErrorsListener;
import mcv.viewmodel.structs.CytoProtein;
import mcv.logicmodel.structs.PPINetwork;

public abstract class UIController {

    private static UIController controller = new DefaultUIController();

    public static UIController getInstance() {
        return controller;
    }

    public abstract Collection<PPINetwork> getSelectedNetworks();

    public abstract Collection<CytoProtein> getSelectedProteins(CyNetwork cyNetwork);

    public abstract void selectConnectedNodes(CyNetwork cyNetwork);

    public abstract void selectUnconnectedNodes(CyNetwork cyNetwork);

    public abstract void unselectUnConnectedNodes(CyNetwork cyNetwork);

    //  public abstract void initButtonsState();
    public abstract void showSelectedNetworks();

    public abstract void showLoadedInteractions();

    public abstract void newData();

    public abstract void updateData();

    public abstract void deleteData();

    public abstract void setMCVActiveTab();

    public abstract void initDataView();
}
