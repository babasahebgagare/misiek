/* ===========================================================
 * NetworkEvolutionPlugin : Cytoscape plugin for visualizing stages of
 * protein networks evolution.
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
 * NetworkEvolutionPlugin  Copyright (C) 2008-2009
 * Authors:  Michal Wozniak (code) (m.wozniak@mimuw.edu.pl)
 *           Janusz Dutkowski (idea, data) (j.dutkowski@mimuw.edu.pl)
 *           Jerzy Tiuryn (supervisor) (tiuryn@mimuw.edu.pl)
 */

package ppine.ui.familycolors;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

class JTableButtonMouseListener implements MouseListener {

    private JTable __table;

    private void __forwardEventToButton(MouseEvent e) {
        TableColumnModel columnModel = __table.getColumnModel();
        int column = columnModel.getColumnIndexAtX(e.getX());
        int row = e.getY() / __table.getRowHeight();
        Object value;
        JButton button;
        MouseEvent buttonEvent;

        if (row >= __table.getRowCount() || row < 0 ||
                column >= __table.getColumnCount() || column < 0) {
            return;
        }

        value = __table.getValueAt(row, column);

        if (!(value instanceof JButton)) {
            return;
        }

        button = (JButton) value;

        buttonEvent = SwingUtilities.convertMouseEvent(__table, e, button);
        button.dispatchEvent(buttonEvent);
        // This is necessary so that when a button is pressed and released
        // it gets rendered properly.  Otherwise, the button may still appear
        // pressed down when it has been released.
        __table.repaint();
    }

    public JTableButtonMouseListener(JTable table) {
        __table = table;
    }

    public void mouseClicked(MouseEvent e) {
        __forwardEventToButton(e);
    }

    public void mouseEntered(MouseEvent e) {
        __forwardEventToButton(e);
    }

    public void mouseExited(MouseEvent e) {
        __forwardEventToButton(e);
    }

    public void mousePressed(MouseEvent e) {
        __forwardEventToButton(e);
    }

    public void mouseReleased(MouseEvent e) {
        __forwardEventToButton(e);
    }
}