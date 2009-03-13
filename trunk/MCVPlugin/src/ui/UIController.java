package ui;

import cytoscape.CyNetwork;
import java.util.Collection;
import logicmodel.structs.CytoProtein;
import logicmodel.structs.PPINetwork;

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

    public abstract void initButtonsState();

    public abstract void loadData();

    public abstract void showSelectedNetworks();

    public abstract void loadAllInteractions(double treshold);

    public abstract void showLoadedInteractions(double treshold);

    public abstract void loadInteractionsForNetwork(double treshold);

    public abstract void deleteAllData();
}
