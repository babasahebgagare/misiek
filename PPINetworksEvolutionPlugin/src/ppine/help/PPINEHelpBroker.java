/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/modevo/
 * Sources: http://code.google.com/p/misiek/
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
 * in the United States and other countries.]
 *
 * NetworkEvolutionPlugin  Copyright (C) 2008-2010
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.help;

import cytoscape.logger.CyLogger;
import java.net.URL;
import javax.help.HelpBroker;
import javax.help.HelpSet;

public class PPINEHelpBroker {

    private static HelpBroker hb;
    private static HelpSet hs;
    private static final String HELP_RESOURCE = "/ppine/help/docs/jhelpset.hs";


    static {
        new PPINEHelpBroker();
    }

    /**
     * Creates a new CyHelpBroker object.
     */
    private PPINEHelpBroker() {
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
