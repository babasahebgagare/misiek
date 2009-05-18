package mcv.io.listeners;

import mcv.ui.dataloading.DataLoaderPanel;
import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class FamiliesLoadingErrorsListener implements ErrorListener {

    DataLoaderPanel panel;

    public FamiliesLoadingErrorsListener(DataLoaderPanel panel) {
        this.panel = panel;
    }

    public void errorOccured(ErrorEvent errorEvent) {
        System.out.println(errorEvent);
        panel.showErrorOccuredLabel(errorEvent);
    }
}
