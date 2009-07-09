package ppine.utils;

import cytoscape.dialogs.plugins.TreeNode;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import ppine.logicmodel.controllers.DataHandle;
import ppine.logicmodel.structs.SpeciesTreeNode;
import ppine.main.PluginDataHandle;
import ppine.ui.PluginResources;

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

    private static TreeNode createRecTreeModel(SpeciesTreeNode rootNetwork) {
        if (rootNetwork == null) {
            return null;
        } else {
            TreeNode ret = new TreeNode(rootNetwork.getID());

            for (SpeciesTreeNode child : rootNetwork.getContext().getChildrenNetworks()) {
                TreeNode childNode = createRecTreeModel(child);
                if (childNode != null) {
                    ret.addChild(childNode);
                }
            }
            return ret;
        }
    }
}
