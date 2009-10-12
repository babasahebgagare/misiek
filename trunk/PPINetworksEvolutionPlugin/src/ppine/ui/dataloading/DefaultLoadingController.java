package ppine.ui.dataloading;

import cytoscape.Cytoscape;
import java.util.Map;
import javax.swing.JFrame;
import ppine.io.AbstractDataReader;
import ppine.io.listeners.ExperimentsLoadingErrorsListener;
import ppine.io.listeners.FamiliesLoadingErrorsListener;
import ppine.io.listeners.InteractionsLoadingErrorsListener;
import ppine.io.listeners.SpeciesLoadingErrorsListener;
import ppine.logicmodel.controllers.DataHandle;
import ppine.logicmodel.controllers.ProjectorInfoCalculator;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.LoadedDataHandle;
import ppine.main.PluginDataHandle;
import ppine.ui.PluginMenusHandle;
import ppine.ui.UIController;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class DefaultLoadingController {

    public static void deleteAllInteractions() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        LoadedDataHandle ldh = PluginDataHandle.getLoadedDataHandle();
        ldh.deleteAll();
        for (SpeciesTreeNode network : dh.getNetworks().values()) {
            network.deleteAllInteractions();
        }

    }

    public static void deleteSpeciesTree() {
        UIController.getInstance().deleteData();
    }

    public static void loadFamiliesTreeData(String filepath, FamiliesLoadingErrorsListener errorListener) {
        AbstractDataReader.getInstance().readFamiliesTree(filepath, errorListener);

        ProjectorInfoCalculator.calculateProjectorInfo();
        UIController.getInstance().initDataView();
    }

    public static void loadAllInteractions(String filepath, Map<String, Double> tresholds, InteractionsLoadingErrorsListener errorListener) {
        AbstractDataReader.getInstance().readAllInteractions(filepath, tresholds, errorListener);
        PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }

    public static void loadSpeciesTreeData(String filepath, SpeciesLoadingErrorsListener errorListener) {

        AbstractDataReader.getInstance().readSpeciesTree(filepath, errorListener);

        UIController.getInstance().initDataView();
    }

    public static void showPPINELogsPanel() {
        DataLoadingErrorsJPanel errorsPanel = new DataLoadingErrorsJPanel();
        JFrame frame = new JFrame("NetworkEvolutionPlugin errors");

        frame.add(errorsPanel);
        frame.pack();
        frame.setLocationRelativeTo(Cytoscape.getDesktop());
        frame.setVisible(true);
    }

    static void loadExperimentsData(String filepath, ExperimentsLoadingErrorsListener errorListener) {
        AbstractDataReader.getInstance().readAllExperiments(filepath, errorListener);
        //PluginMenusHandle.getShowLoadedInteractionsButton().setEnabled(true);
    }
}
