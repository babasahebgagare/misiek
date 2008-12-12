/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.parsers.defaultparser;

import java.io.BufferedReader;
import java.io.IOException;
import viewmodel.controllers.CytoDataHandle;
import viewmodel.structs.CytoAbstractPPINetwork;
import utils.IDCreator;
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

    public static void readInteractions(BufferedReader br, CytoAbstractPPINetwork cytoNetwork, double treshold) throws IOException {
        while (br.ready()) {
            try {

                String SourceID = readWord(br);
                String TargetID = readWord(br);
                String EdgeID = IDCreator.createInteractionID(SourceID, TargetID);

                Double Probability = Double.parseDouble(readWord(br));

                if (Probability.doubleValue() >= treshold && cytoNetwork.containsCytoProtein(SourceID) && cytoNetwork.containsCytoProtein(TargetID)) {
                    CytoDataHandle.createCytoInteraction(EdgeID, SourceID, TargetID, Probability, cytoNetwork);
                }

            } catch (Exception ex) {
                Messenger.Error(ex);
            }
        }
    }
}
