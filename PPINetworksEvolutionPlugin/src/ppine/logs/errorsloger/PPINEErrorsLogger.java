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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.logs.errorsloger;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PPINEErrorsLogger {

    private static Integer errorID = Integer.valueOf(0);
    private static Map<Integer, PPINELoggedError> errors = new HashMap<Integer, PPINELoggedError>();

    public static void logPPINEError(Exception e, String message, String source) {
        Date date = new Date();
        PPINELoggedError loggedError = new PPINELoggedError(errorID, e, date, message, source);
        errors.put(errorID, loggedError);
        errorID++;
    }

    public static void deletePPINEErrorLogged(Integer id) {
        errors.remove(id);
    }

    public static void deleteAll() {
        errors = new HashMap<Integer, PPINELoggedError>();
        errorID = 0;
    }

    public static Map<Integer, PPINELoggedError> getErrors() {
        return errors;
    }

    public static Collection<PPINELoggedError> getErrorsCollection() {
        return errors.values();
    }
}
