package mcv.io.listeners;

import org.jdesktop.swingx.error.ErrorEvent;
import org.jdesktop.swingx.error.ErrorListener;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class FamiliesLoadingErrorsListener implements ErrorListener {

    public FamiliesLoadingErrorsListener() {
    }

    public void errorOccured(ErrorEvent errorEvent) {
        System.out.println(errorEvent);
    }
}
