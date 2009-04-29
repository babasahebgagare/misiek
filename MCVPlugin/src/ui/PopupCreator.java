package ui;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class PopupCreator {

    public static JPopupMenu createPopup(JComponent cmp) {
        JPopupMenu pmnu;
        int x = cmp.getX();
        int y = cmp.getY();
        pmnu = new JPopupMenu("sasa");
        pmnu.show(cmp, x, y);
        return pmnu;
    }
}
