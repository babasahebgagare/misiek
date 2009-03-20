package visual.menus;

import ding.view.NodeContextMenuListener;
import giny.view.NodeView;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class NodePopupMenuListener implements NodeContextMenuListener {

    public void addNodeContextMenuItems(NodeView node, JPopupMenu menu) {
        System.out.println("aaa");
        if (menu == null) {
            menu = new JPopupMenu("aaaa");
        }
        menu.add(new JMenuItem("aaass"));
    }
}
