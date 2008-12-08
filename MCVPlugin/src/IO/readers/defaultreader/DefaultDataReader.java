package io.readers.defaultreader;

import io.AbstractDataReader;
import io.MCVBufferedReader;
import io.parsers.DataParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openide.util.Exceptions;
import structs.model.CytoAbstractPPINetwork;
import utils.Messenger;

public class DefaultDataReader extends AbstractDataReader {

    private void readSpacies(BufferedReader br) throws IOException {
        String treeString = br.readLine();
        DataParser.getInstance().readSpaciesString(treeString);
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
                DataParser.getInstance().readAllTreeString(family);
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
            DataParser.getInstance().readInteractions(br, cytoNetwork, treshold);
            mbr.close();
        } catch (FileNotFoundException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        } catch (IOException e) {
            Messenger.Error(e);
            Exceptions.printStackTrace(e);
        }
    }

    @Override
    public void readAllInteractions(double treshold) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
