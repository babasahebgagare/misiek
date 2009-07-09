package ppine.ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ppine.ui.PluginMenusHandle;
import ppine.ui.dataloading.DataLoaderPanel;

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
        PluginMenusHandle.refreshUIafterSpeciesLoading();
        panel.refreshStats();
    }
}
