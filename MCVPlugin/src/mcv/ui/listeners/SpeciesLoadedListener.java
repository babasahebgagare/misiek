package mcv.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mcv.ui.dataloading.DataLoaderPanel;
import mcv.ui.UIController;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class SpeciesLoadedListener implements ActionListener {

    private DataLoaderPanel panel;

    public SpeciesLoadedListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        this.panel.enableTabs();
        UIController.getInstance().refreshUIafterSpeciesLoading();
        panel.refreshStats();
    }
}
