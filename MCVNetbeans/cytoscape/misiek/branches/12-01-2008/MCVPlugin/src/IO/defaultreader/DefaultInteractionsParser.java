/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.defaultreader;

import java.io.BufferedReader;
import java.io.IOException;
import main.DataHandle;
import structs.model.CytoAbstractPPINetwork;
import utils.Messenger;

/**
 *
 * @author misiek
 */
public class DefaultInteractionsParser {

    public static void EatWhiteSpace(BufferedReader br) {
        int ch;

        try {
            for (;;) {
                br.mark(1);          // set mark in buffer
                ch = br.read();      // read a char
                if (ch < 1) // if EOF, quit
                {
                    break;
                }
                if (!java.lang.Character.isWhitespace((char) ch)) {
                    br.reset();        // if non-WS, move back to mark & quit
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static String readWord(BufferedReader br) {
        int ch;                        // input variable
        String myValue = "";           // myValue is initially empty

        EatWhiteSpace(br);               // eat leading white space

        try {
            for (;;) {
                ch = br.read();      // read a char
                if (ch < 1 || java.lang.Character.isWhitespace((char) ch)) {
                    break;                   // break for eof or white space
                }
                myValue += (char) ch;      // append it to myValue
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return myValue;
    }

    public static void readInteractions(BufferedReader br, double treshold) throws IOException {
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            String[] intData = line.split("        ");

            if (intData.length == 3) {
                String SourceID = intData[0].trim();
                String TargetID = intData[1].trim();
                String EdgeID = SourceID + "_" + TargetID;


                Double Probability = Double.parseDouble(intData[2].trim());
                if (Probability.doubleValue() >= treshold) {
                    DataHandle.createInteraction(EdgeID, SourceID, TargetID, Probability);
                }
            }
            line = br.readLine();
        }
    }

    /*   public static void readInteractions(BufferedReader br, PPINetwork network, double treshold) throws IOException {

    while (br.ready()) {
    try {
    String SourceID = readWord(br);
    String TargetID = readWord(br);
    String EdgeID = SourceID + "_" + TargetID;

    Double Probability = Double.parseDouble(readWord(br));

    //c
    if (Probability.doubleValue() >= treshold && network.containsProtein(SourceID) && network.containsProtein(TargetID)) {
    DataHandle.createInteraction(EdgeID, SourceID, TargetID, Probability);
    }


    } catch (Exception ex) {
    Messenger.Error(ex);
    }
    }
    }
     */
    static void readInteractions(BufferedReader br, CytoAbstractPPINetwork cytoNetwork, double treshold) throws IOException {
        while (br.ready()) {
            try {

                String SourceID = readWord(br);
                String TargetID = readWord(br);
                String EdgeID = SourceID + "_" + TargetID;

                Double Probability = Double.parseDouble(readWord(br));

                if (Probability.doubleValue() >= treshold && cytoNetwork.containsCytoProtein(SourceID) && cytoNetwork.containsCytoProtein(TargetID)) {
                    DataHandle.createInteraction(EdgeID, SourceID, TargetID, Probability);
                }

            } catch (Exception ex) {
                Messenger.Error(ex);
            }
        }
    }
}
