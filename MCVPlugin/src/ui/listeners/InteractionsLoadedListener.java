package ui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.DataLoaderPanel;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class InteractionsLoadedListener implements ActionListener {

    private DataLoaderPanel panel;

    public InteractionsLoadedListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void actionPerformed(ActionEvent e) {
        panel.setParentFrameOnTop();
    }
}
