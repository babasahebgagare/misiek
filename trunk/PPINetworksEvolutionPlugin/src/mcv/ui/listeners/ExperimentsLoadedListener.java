package mcv.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mcv.logicmodel.controllers.ProjectorInfoCalculator;
import mcv.ui.PluginMenusHandle;
import mcv.ui.UIController;
import mcv.ui.dataloading.DataLoaderPanel;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExperimentsLoadedListener implements ActionListener {

    private DataLoaderPanel panel;

    public ExperimentsLoadedListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        this.panel.enableTabs();
        panel.setParentFrameOnTop();
        PluginMenusHandle.refreshUIafterSpeciesLoading();
        PluginMenusHandle.refreshUIafterProteinsLoading();
        UIController.getInstance().initDataView();
        ProjectorInfoCalculator.calculateProjectorInfo();
    }
}
