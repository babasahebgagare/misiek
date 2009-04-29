package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.DataLoaderPanel;
import ui.InteractionsLoaderPanel;
import ui.UIController;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ProteinsLoadedListener implements ActionListener {

    private DataLoaderPanel panel;
    private InteractionsLoaderPanel intPanel;

    public ProteinsLoadedListener(DataLoaderPanel panel, InteractionsLoaderPanel intPanel) {
        this.panel = panel;
        this.intPanel = intPanel;
    }

    public void actionPerformed(ActionEvent e) {
        this.panel.enableTabs();
        this.intPanel.initSpeciesList();
        UIController.getInstance().refreshUIafterProteinsLoading();
    }
}
