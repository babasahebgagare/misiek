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
        }
    }

    private static void ReadFamiliesFromBuffer(BufferedReader br) {
        
    }

    private static void ReadTreeFromBuffer(BufferedReader br) throws IOException {
        String line;

        while (!(line = br.readLine()).equals(TREE_END)) {
            if(line == null) break;
            String[] data = line.split(":");
            if (data.length == 1) {
                DataHandle.getInstance().createRootPPINetwork(data[0].trim());
            }
            if (data.length == 2) {
                DataHandle.getInstance().createPPINetwork(data[0].trim(), data[1].trim());
            }
        }
    }
}
