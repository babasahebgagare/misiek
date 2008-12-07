package controllers.interactions;

import structs.model.CytoAbstractPPINetwork;

public abstract class InteractionsManager {

    private static InteractionsManager manager = new DefaultInteractionsManager();

    public static InteractionsManager getInstance() {
        return manager;
    }

    public abstract void loadInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork, double treshold);

    public abstract void showInteractions(CytoAbstractPPINetwork cytoNetwork);

    public abstract void deleteViewInteracions(CytoAbstractPPINetwork cytoNetwork);

    public abstract void loadAndShowInteractionsFromModel(CytoAbstractPPINetwork cytoNetwork, double treshold);
}
