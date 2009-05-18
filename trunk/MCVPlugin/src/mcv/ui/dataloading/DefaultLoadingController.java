package mcv.ui.dataloading;

import cytoscape.Cytoscape;
import java.util.Map;
import javax.swing.JFrame;
import mcv.io.AbstractDataReader;
import mcv.io.listeners.FamiliesLoadingErrorsListener;
import mcv.io.listeners.InteractionsLoadingErrorsListener;
import mcv.io.listeners.SpeciesLoadingErrorsListener;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.controllers.ProjectorInfoCalculator;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.LoadedDataHandle;
import mcv.main.PluginDataHandle;
import mcv.ui.PluginMenusHandle;
import mcv.ui.UIController;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class DefaultLoadingController {

    public static void deleteAllInteractions() {
        DataHandle dh = PluginDataHandle.getDataHandle();
        LoadedDataHandle ldh = PluginDataHandle.getLoadedDataHandle();
        ldh.deleteAll();
        for (PPINetwork network : dh.getNetworks().values()) {
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

    public static void showMCVLogsPanel() {
        DataLoadingErrorsJPanel errorsPanel = new DataLoadingErrorsJPanel();
        JFrame frame = new JFrame("MCV plugin errors");

        frame.add(errorsPanel);
        frame.pack();
        frame.setLocationRelativeTo(Cytoscape.getDesktop());
        frame.setVisible(true);
    }
}
