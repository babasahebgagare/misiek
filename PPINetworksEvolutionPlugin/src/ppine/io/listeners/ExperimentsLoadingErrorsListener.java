package mcv.io.listeners;

import mcv.ui.dataloading.DataLoaderPanel;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class ExperimentsLoadingErrorsListener implements ErrorListener {

    DataLoaderPanel panel;

    public ExperimentsLoadingErrorsListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void errorOccured(ErrorEvent errorEvent) {
        System.out.println(errorEvent.getSource());
        panel.showErrorOccuredLabel(errorEvent);
    }
}
