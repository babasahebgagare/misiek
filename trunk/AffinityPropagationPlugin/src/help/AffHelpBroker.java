package help;

import cytoscape.logger.CyLogger;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class AffHelpBroker {

    private static HelpBroker hb;
    private static HelpSet hs;
    private static final String HELP_RESOURCE = "/help/docs/jhelpset.hs";


    static {
        new AffHelpBroker();
    }

    /**
     * Creates a new CyHelpBroker object.
     */
    private AffHelpBroker() {
        hb = null;
        hs = null;

        URL hsURL = getClass().getResource(HELP_RESOURCE);

        try {
            hs = new HelpSet(null, hsURL);
            hb = hs.createHelpBroker();
        } catch (Exception e) {
            CyLogger.getLogger().info("HelpSet " + e.getMessage());
            CyLogger.getLogger().info("HelpSet " + hs + " not found.");
        }
    }

    /**
     * Returns the HelpBroker.
     *
     * @param currentNode 
     * @return the HelpBroker.
     */
    public static HelpBroker getHelpBroker(final String currentNode) {
        hb.setCurrentID(currentNode);
        return hb;
    }

    /**
     * Returns the HelpSet.
     *
     * @return the HelpSet.
     */
    public static HelpSet getHelpSet() {
        return hs;
    }
}
