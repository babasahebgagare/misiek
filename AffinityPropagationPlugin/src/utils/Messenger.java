/* ===========================================================
 * APGraphClusteringPlugin : Java implementation of Affinity Propagation
 * algorithm as Cytoscape plugin.
 * ===========================================================
 *
 *
 * Project Info:  http://bioputer.mimuw.edu.pl/veppin/
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
 * APGraphClusteringPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */
package utils;

import cytoscape.Cytoscape;
import javax.swing.JOptionPane;

public class Messenger {

    public static void message(final Object message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void messageInfo(final Object message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void error(final Exception ex) {
        System.out.println(ex.getMessage());
        JOptionPane.showMessageDialog(Cytoscape.getDesktop(), ex.getMessage());
    }

    public static int confirmWarning(Object message) {
        System.out.println(message);
        return JOptionPane.showConfirmDialog(Cytoscape.getDesktop(), message, "Warning.", JOptionPane.WARNING_MESSAGE);
    }

    public static int confirmInfo(Object message) {
        System.out.println(message);
        return JOptionPane.showConfirmDialog(Cytoscape.getDesktop(), message, "Info.", JOptionPane.INFORMATION_MESSAGE);
    }
}
