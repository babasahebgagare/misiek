package mcv.utils;

import cytoscape.dialogs.plugins.TreeNode;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import mcv.logicmodel.controllers.DataHandle;
import mcv.logicmodel.structs.PPINetwork;
import mcv.main.PluginDataHandle;
import mcv.ui.PluginResources;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class JTreeModelSpeciesGenerator {

    public static void decorateJTree(JTree speciesTree) {
        Icon icon = PluginResources.getIcon("spec.jpg");
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) speciesTree.getCellRenderer();

        renderer.setOpenIcon(icon);
        renderer.setClosedIcon(icon);
        renderer.setLeafIcon(icon);
        speciesTree.setCellRenderer(renderer);
    }

    public static TreeModel generateModel() {

        DataHandle dh = PluginDataHandle.getDataHandle();
        TreeNode root = createRecTreeModel(dh.getRootNetwork());
        TreeModel newModel = new DefaultTreeModel(root);
        return newModel;
    }

    private static TreeNode createRecTreeModel(PPINetwork rootNetwork) {
        if (rootNetwork == null) {
            return null;
        } else {
            TreeNode ret = new TreeNode(rootNetwork.getID());

            for (PPINetwork child : rootNetwork.getContext().getChildrenNetworks()) {
                TreeNode childNode = createRecTreeModel(child);
                if (childNode != null) {
                    ret.addChild(childNode);
                }
            }
            return ret;
        }
    }
}
