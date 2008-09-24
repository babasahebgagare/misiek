package IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import main.DataHandle;
import utils.Messenger;

public class DataReader {

    private static String TREE = "TREE";
    private static String TREE_END = "ENDTREE";
    private static String FAMILIES = "FAMILIES";
    private static String FAMILIES_END = "ENDFAMILIES";
    private static String FAMILY = "FAMILY";
    private static String FAMILY_END = "ENDFAMILY";
    private static String INTERACTIONS = "INTERACTIONS";
    private static String INTERACTIONS_END = "ENDINTERACTIONS";

    public static void ReadDataFromFile(String filepath) {
        File file = new File(filepath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        BufferedReader br = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            br = new BufferedReader(new InputStreamReader(dis));

            ReadDataFromBuffer(br);

            br.close();
            dis.close();
            bis.close();
            fis.close();
        } catch (FileNotFoundException e) {
            Messenger.Error(e);
        } catch (IOException e) {
            Messenger.Error(e);
        }
    }

    private static void ReadDataFromBuffer(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals(TREE)) {
                ReadTreeFromBuffer(br);
            }
            if (line.equals(FAMILIES)) {
                ReadFamiliesFromBuffer(br);
            }
            if (line.equals(INTERACTIONS)) {
                ReadInteractionsFromBuffer(br);
            }
        }
    }

    private static void ReadFamiliesFromBuffer(BufferedReader br) throws IOException {
        String line;

        while (!(line = br.readLine()).equals(FAMILIES_END)) {
            if (line.equals("") || line == null) {
                throw new IOException("ReadFamiliesFromBuffer: Nieoczekiwany koniec pliku");
            }
            if (line.equals(FAMILY)) {
                ReadFamilyFromBuffer(br);
            }
        }
    }

    private static void ReadFamilyFromBuffer(BufferedReader br) throws IOException {
        String line;
        String famID;
        line = br.readLine();
        try {
            String[] words = line.split(":");
            famID = words[1];
            DataHandle.createFamily(famID);
        } catch (Exception e) {
            throw new IOException("ReadFamilyFromBuffer: Oczekiwano nazwy rodziny");
        }
        while (!(line = br.readLine()).equals(FAMILY_END)) {
            if (line.equals("") || line == null) {
                throw new IOException("ReadFamilyFromBuffer: Nieoczekiwany koniec pliku");
            }
            try {
                String[] words = line.split(":");
                if (words.length == 2) {
                    DataHandle.createRootProtein(words[0], words[1], famID);
                }
                if (words.length == 3) {
                    DataHandle.createProtein(words[0], words[1], words[2], famID);
                }
            } catch (Exception e) {
                throw new IOException("ReadFamilyFromBuffer: Bład podczas tworzenia białka");
            }
        }
    }

    private static void ReadInteractionsFromBuffer(BufferedReader br) throws IOException {
        String line;

        while (!(line = br.readLine()).equals(INTERACTIONS_END)) {
            if (line.equals("") || line == null) {
                throw new IOException("ReadInteractionsFromBuffer: Nieoczekiwany koniec pliku");
            }
            try {
                String[] words = line.split(":");
                DataHandle.createInteraction(words[0], words[1], words[2], words[3], Double.parseDouble(words[4]));
            } catch (Exception e) {
                throw new IOException("ReadInteractionsFromBuffer: Blad podczas tworzenia interakcji");
            }
        }
    }

    private static void ReadTreeFromBuffer(BufferedReader br) throws IOException {
        String line;

        while (!(line = br.readLine()).equals(TREE_END)) {
            if (line.equals("") || line == null) {
                throw new IOException("ReadTreeFromBuffer: Nieoczekiwany koniec pliku");
            }
            String[] data = line.split(":");
            if (data.length == 1) {
                DataHandle.createRootPPINetwork(data[0].trim());
            }
            if (data.length == 2) {
                DataHandle.createPPINetwork(data[0].trim(), data[1].trim());
            }
        }
    }
}
