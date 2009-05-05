package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.DataLoaderPanel;
import ui.UIController;

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
