package ppine.io.listeners;

import ppine.ui.dataloading.DataLoaderPanel;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class SpeciesLoadingErrorsListener implements ErrorListener {

    DataLoaderPanel panel;

    public SpeciesLoadingErrorsListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void errorOccured(ErrorEvent errorEvent) {
        System.out.println(errorEvent.toString());
        panel.showErrorOccuredLabel(errorEvent);
    }
}
