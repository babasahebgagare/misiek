package utils;

import cytoscape.dialogs.plugins.TreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import logicmodel.controllers.DataHandle;
import logicmodel.structs.PPINetwork;
import main.PluginDataHandle;
import ui.PluginMenusHandle;

/**
 *
 * @author misiek (mw219725@gmail.com)
 */
public class JTreeModelSpeciesGenerator {

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
