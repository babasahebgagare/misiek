package IO.defaultreader;

import IO.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import main.DataHandle;
import org.openide.util.Exceptions;
import structs.model.CytoAbstractPPINetwork;
import utils.Messenger;

public class DefaultDataReader extends AbstractDataReader {

    private void readSpacies(BufferedReader br) throws IOException {
        String treeString = br.readLine();
        //  Messenger.Message(treeString);
        readSpaciesString(treeString, null);
    }

    private void readSpacies(String filepath) {
        try {
            MCVBufferedReader mbr = new MCVBufferedReader(filepath);
            BufferedReader br = mbr.getBufferedReader();
            readSpacies(br);
            mbr.close();
        } catch (FileNotFoundException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        }
    }

    private void readTrees(BufferedReader br) throws IOException {
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            String[] families = line.split(";");
            for (String family : families) {
                DefaultTreeParser.readAllTreeString(family);
            }
            line = br.readLine();
        }
    }

    private void readTrees(String filepath) {
        try {
            MCVBufferedReader mbr = new MCVBufferedReader(filepath);
            BufferedReader br = mbr.getBufferedReader();
            readTrees(br);
            mbr.close();
        } catch (FileNotFoundException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        }
    }

    private static void readSpaciesString(String treeString, String parent) {
        ParserStruct struct = extractNodeName(treeString);

        if (parent == null) {
            DataHandle.createRootPPINetwork(struct.getNodeName());
        } else {
            DataHandle.createPPINetwork(struct.getNodeName(), parent);
        }

        if (struct.getSubNodes() == null) {
        } else {
            for (String subNode : struct.getSubNodes()) {
                readSpaciesString(subNode, struct.getNodeName());
            }
        }

    }

    private static ParserStruct extractNodeName(String treeString) {
        ParserStruct struct = new ParserStruct();

        int lastBracket = treeString.lastIndexOf(")");
        if (lastBracket == -1) {
            struct.setNodeName(treeString);
            struct.setSubNodes(null);
        } else {
            struct.setNodeName(treeString.substring(lastBracket + 1));
            struct.setSubNodes(extractSubNodes(treeString.substring(1, lastBracket)));
        }

        return struct;
    }

    private static Collection<String> extractSubNodes(String substring) {
        Collection<String> ret = new HashSet<String>();

        int count = 0;
        int lastIndex = 0;

        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == '(') {
                count++;
            } else if (substring.charAt(i) == ')') {
                count--;
            } else if ((substring.charAt(i) == ',') && (count == 0)) {
                ret.add(substring.substring(lastIndex, i));
                lastIndex = i;
            }
        }

        ret.add(substring.substring(lastIndex + 1, substring.length()));

        return ret;
    }

    @Override
    public void readSpacies() {
        String spaciespath = getFilepath().concat("spy");
        readSpacies(spaciespath);
    }

    @Override
    public void readTrees() {
        String treepath = getFilepath().concat("trees");
        readTrees(treepath);
    }

    @Override
    public void readInteractions(CytoAbstractPPINetwork cytoNetwork, double treshold) {
        String intpath = getFilepath().concat("int");

        try {
            MCVBufferedReader mbr = new MCVBufferedReader(intpath);
            BufferedReader br = mbr.getBufferedReader();
            DefaultInteractionsParser.readInteractions(br, cytoNetwork, treshold);
            mbr.close();
        } catch (FileNotFoundException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        }
    }
}
