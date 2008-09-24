package IO;

import main.DataHandle;

public class TreeParser {

    public static void parseTree(String tree) {
        tree = tree.trim();
        int last = tree.lastIndexOf(")");

        String subTree = tree.substring(1, last);
        String RootNetworkID = tree.substring(last + 1, tree.length());

        DataHandle.createRootPPINetwork(RootNetworkID);
        parseSubTree(subTree, RootNetworkID);
    }

    private static void parseSubTree(String tree, String parent) {
        tree = tree.trim();
        if (tree.charAt(0) == '(') {
            int last = tree.lastIndexOf(")");
            String subTree = tree.substring(1, last);
            String NetworkID = tree.substring(last + 1, tree.length());

            DataHandle.createPPINetwork(NetworkID, parent);
            parseSubTree(subTree, NetworkID);
        } else {
            DataHandle.createPPINetwork(tree, parent);
        }
    }
}
