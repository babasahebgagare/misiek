package mcv.io.listeners;

import mcv.ui.dataloading.SpeciesTreeLoaderPanel;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class SpeciesLoadingErrorsListener implements ErrorListener {

    SpeciesTreeLoaderPanel panel;

    public SpeciesLoadingErrorsListener(SpeciesTreeLoaderPanel panel) {
        this.panel = panel;
    }

    public void errorOccured(ErrorEvent errorEvent) {
        System.out.println(errorEvent.toString());
        panel.logSpeciesLoadingError(errorEvent);
    }
}
