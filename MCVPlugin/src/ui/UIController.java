package ui;

import cytoscape.CyNetwork;
import java.util.Collection;
import structs.model.CytoProtein;
import structs.model.PPINetwork;

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
}
